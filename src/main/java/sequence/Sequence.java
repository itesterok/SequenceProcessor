package sequence;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class Sequence {

  private static final String SEQUENCE_DELIMETER = ";";
  private Operation operation;
  private List<String> values;

  public Sequence(String stringOperation, String stringValues) {
    this.operation = assureValidOperation(stringOperation);
    this.values = splitValuesFromString(stringValues);
  }

  public static List<String> splitValuesFromString(String stringValues) {
    Preconditions.checkArgument(stringValues != null, "Values can't be null!");
    return Arrays.asList(stringValues.replaceAll("//S", "").split(SEQUENCE_DELIMETER));
  }

  public static String joinValuesFromList(List<String> list) {
    Preconditions.checkArgument(list != null, "List can't be null!");
    return Joiner.on(SEQUENCE_DELIMETER).join(list);
  }

  public static boolean isValidOperation(String stringOperation) {
    try {
      assureValidOperation(stringOperation);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  public static Operation assureValidOperation(String stringOperation) {
    Preconditions.checkArgument(stringOperation != null, "Operation can't be null!");
    return Operation.valueOf(stringOperation.toUpperCase(Locale.ENGLISH));
  }

  public Operation getOperation() {
    return operation;
  }

  public List<String> getValues() {
    return values;
  }

  @Override
  public int hashCode() {
    return Objects.hash(operation, values);
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public String toString() {
    return String.format("Operation: [%s]; values: [%s]", operation, joinValuesFromList(values));
  }

  public enum Operation {
    SORT("sort"),
    INVERSE("inverse"),
    REMOVE_DUPLICATES("remove_duplicates");

    private String operation;

    Operation(String value) {
      this.operation = value;
    }

    public String getOperationName() {
      return operation;
    };
  }
}

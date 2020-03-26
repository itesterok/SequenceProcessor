package operations;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class SortingProcessor implements OperationProcessor {

  List<String> sequence;

  @Override
  public void setSequence(List<String> sequence) {
    this.sequence = sequence;
  }

  @Override
  public List<String> processSequence() {
    Preconditions.checkArgument(sequence != null, "Parameter is not initialized!");
    Collections.sort(sequence, String::compareTo);
    return sequence;
  }
}

package operations;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.ToString.Exclude;

@ToString
@EqualsAndHashCode
public class InversingProcessor implements OperationProcessor {
  List<String> sequence;
  @Exclude List<String> inverted = new ArrayList<>();

  public void setSequence(List<String> sequence) {
    Preconditions.checkArgument(sequence != null, "Parameter can't be null!");
    this.sequence = sequence;
  }

  @Override
  public List<String> processSequence() {
    Preconditions.checkArgument(sequence != null, "Parameter is not initialized!");
    for (int i = sequence.size() - 1; i >= 0; i--) {
      inverted.add(sequence.get(i));
    }
    return inverted;
  }
}

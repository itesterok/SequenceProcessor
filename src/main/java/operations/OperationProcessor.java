package operations;

import java.util.List;

public interface OperationProcessor {

  void setSequence(List<String> sequence);

  List<String> processSequence();
}

package operations;

import filereader.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import sequence.Sequence.Operation;

public class OperationsProcessorFactory {

  FileReader fileReader;

  public OperationsProcessorFactory(FileReader fileReader) {
    this.fileReader = fileReader;
  }

  public List<OperationProcessor> getProcessorsList() {
    return fileReader.getSequences().stream()
        .map(
            sequence -> {
              OperationProcessor processor = getOperationProcessor(sequence.getOperation());
              processor.setSequence(sequence.getValues());
              return processor;
            })
        .collect(Collectors.toList());
  }

  private OperationProcessor getOperationProcessor(Operation operation) {
    switch (operation) {
      case SORT:
        return new SortingProcessor();
      case INVERSE:
        return new InversingProcessor();
      default:
        throw new IllegalArgumentException("This type of processor is not implemented yet!");
    }
  }
}

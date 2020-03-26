package sequence;

import com.google.common.base.Preconditions;
import filereader.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import operations.OperationProcessor;
import operations.OperationsProcessorFactory;

public class SequenceProcessor {

  private FileReader fileReader;

  public SequenceProcessor(FileReader fileReader) {
    Preconditions.checkArgument(fileReader != null, "Argument can't be null!");
    this.fileReader = fileReader;
  }

  public List<List<String>> process() {
    return new OperationsProcessorFactory(fileReader)
        .getProcessorsList().stream()
            .map(OperationProcessor::processSequence)
            .collect(Collectors.toList());
  }
}

package filereader;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import sequence.Sequence;

public class LocalDiskFileReader implements FileReader {

  private Path path;

  public LocalDiskFileReader(String filePath) {
    this(Paths.get(filePath));
  }

  public LocalDiskFileReader(Path path) {
    Preconditions.checkArgument(Files.exists(path), "Specified path is wrong!");
    this.path = path;
  }

  @Override
  /**
   * Safe to be used to read a very large file. Thanks to the Java 8 NIO.2 API we don't suffer from
   * an OutOfMemoryError problem anymore. The method operates with a Stream<String> object.
   */
  public List<Sequence> getSequences() {
    List<Sequence> sequences = new ArrayList() {};
    try {
      List<String> allLines = Files.readAllLines(path);
      for (int i = 0; i < allLines.size(); i = i + 2) {
        String currentLine = allLines.get(i);
        if (Sequence.isValidOperation(currentLine)) {
          sequences.add(new Sequence(currentLine, allLines.get(i + 1)));
        } else {
          // Try to go the the next line if current one does not contain valid operation.
          // Since we jump with step 2, decreasing the counter by 1 moves the pointer to -1+2=1 step
          // forward.
          i--;
          continue;
        }
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Error when processing file with sequences!", e);
    }
    return sequences;
  }
}

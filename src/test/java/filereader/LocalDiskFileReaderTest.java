package filereader;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import sequence.Sequence;

public class LocalDiskFileReaderTest {

  private static final Sequence SEQUENCE1 =
      new Sequence("REMOVE_DUPLICATES", "aaaaaa;bbbbbb;cccccc;dddddd;aaaaaa;bbbbbb;cc");
  private static final Sequence SEQUENCE2 = new Sequence("INVERSE", "aaaaaa;bbbbbb;cccccc;dddddd");
  private static final Sequence SEQUENCE3 = new Sequence("SORT", "ddd;aaa;bbb");
  private FileReader fileReader;

  @Test
  public void getsSequenceFromExistingFile() {
    fileReader = new LocalDiskFileReader("src/test/resources/example_sequence");
    assertThat(fileReader.getSequences())
        .containsExactlyElementsIn(Arrays.asList(SEQUENCE1, SEQUENCE2, SEQUENCE3));
  }

  @Test
  public void throwsExceptionOnNotExistingFile() {
    String errorMessage =
        assertThrows(
                IllegalArgumentException.class,
                () -> new LocalDiskFileReader("test/resources/not_existing_file"))
            .getMessage();
    assertThat(errorMessage).isEqualTo("Specified path is wrong!");
  }

  @Test
  public void getsEmptySequencesListOnNotValidFile() {
    List<Sequence> sequences =
        new LocalDiskFileReader("src/test/resources/incorrect_sequence").getSequences();
    assertThat(sequences).isEmpty();
  }
}

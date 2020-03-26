import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.doReturn;

import filereader.FileReader;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import sequence.Sequence;
import sequence.SequenceProcessor;

public class SequenceProcessorTest {
  private static final Sequence SEQUENCE1 = new Sequence("SORT", "cc;aa;bb");
  private static final Sequence SEQUENCE2 = new Sequence("INVERSE", "ddd;eee;ggg");
  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private FileReader fileRetriever;
  private SequenceProcessor processor;

  @Before
  public void setUp() {
    this.processor = new SequenceProcessor(fileRetriever);
  }

  @Test
  public void inverseOperationOnCorrectInput() {
    List<String> expectedValuesFromSequence1 = Arrays.asList("aa", "bb", "cc");
    List<String> expectedValuesFromSequence2 = Arrays.asList("ggg", "eee", "ddd");
    doReturn(Arrays.asList(SEQUENCE1, SEQUENCE2)).when(fileRetriever).getSequences();

    assertThat(processor.process())
        .containsExactly(expectedValuesFromSequence1, expectedValuesFromSequence2)
        .inOrder();
  }
}

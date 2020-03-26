package operations;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.doReturn;

import filereader.FileReader;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import sequence.Sequence;

public class OperationsProcessorFactoryTest {
  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
  @Mock private FileReader fileReader;

  private OperationsProcessorFactory factory;

  @Before
  public void setUp() {
    factory = new OperationsProcessorFactory(fileReader);
  }

  @Test
  public void getsCorrectProcessorsListFromTheFile() {
    Sequence sequence1 = new Sequence("SORT", "aa;bb;cc");
    Sequence sequence2 = new Sequence("INVERSE", "ddd;eee;ggg");
    OperationProcessor sortingProcessor = new SortingProcessor();
    sortingProcessor.setSequence(Arrays.asList("aa", "bb", "cc"));
    OperationProcessor inversingProcessor = new InversingProcessor();
    inversingProcessor.setSequence(Arrays.asList("ddd", "eee", "ggg"));

    doReturn(Arrays.asList(sequence1, sequence2)).when(fileReader).getSequences();

    assertThat(factory.getProcessorsList())
        .containsExactly(sortingProcessor, inversingProcessor)
        .inOrder();
  }
}

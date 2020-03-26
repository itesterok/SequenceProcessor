package operations;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class SortingProcessorTest {

  @Test
  public void processSequence() {
    OperationProcessor processor = new SortingProcessor();
    processor.setSequence(Arrays.asList("aa", "cc", "ee", "bb"));
    assertThat(processor.processSequence()).containsExactly("aa", "bb", "cc", "ee").inOrder();
  }
}

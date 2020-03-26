package operations;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InversingProcessorTest {

  OperationProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new InversingProcessor();
  }

  @Test
  public void inversesOnCorrectInput() {
    processor.setSequence(Arrays.asList("aa", "bb", "cc"));
    assertThat(processor.processSequence()).containsExactly("cc", "bb", "aa").inOrder();
  }

  @Test
  public void throwsErrorOnNullValue() {
    String errorMessage =
        assertThrows(IllegalArgumentException.class, () -> processor.setSequence(null))
            .getMessage();
    assertThat(errorMessage).isEqualTo("Parameter can't be null!");
  }

  @Test
  public void returnEmptyListOnEmptyInput() {
    processor.setSequence(Arrays.asList());
    assertThat(processor.processSequence()).isEmpty();
  }

  @Test
  public void throwsErrorOnNotInitializedSequence() {
    String errorMessage =
        assertThrows(IllegalArgumentException.class, () -> processor.processSequence())
            .getMessage();
    assertThat(errorMessage).isEqualTo("Parameter is not initialized!");
  }
}

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import org.junit.Test;

public class BeautyFormatterTest {

  @Test
  public void formatsNotEmptySequence() {
    assertThat(BeautyFormatter.format(Arrays.asList("a", "b", "c", "d"))).isEqualTo("a; b; c; d");
  }
}

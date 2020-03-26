package sequence;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import sequence.Sequence.Operation;

class SequenceTest {

  @Test
  public void splitValuesFromString() {
    assertThat(Sequence.splitValuesFromString("aa;bb;cc")).containsExactly("aa", "bb", "cc");
  }

  @Test
  public void splitValuesFromStringOnInvalidValue() {
    String errorMessage =
        assertThrows(IllegalArgumentException.class, () -> Sequence.splitValuesFromString(null))
            .getMessage();
    assertThat(errorMessage).isEqualTo("Values can't be null!");
  }

  @Test
  public void joinValuesFromList() {
    assertThat(Sequence.joinValuesFromList(Arrays.asList("aa", "bb", "cc"))).contains("aa;bb;cc");
  }

  @Test
  public void joinValuesFromListOnInvalidValue() {
    String errorMessage =
        assertThrows(IllegalArgumentException.class, () -> Sequence.joinValuesFromList(null))
            .getMessage();
    assertThat(errorMessage).isEqualTo("List can't be null!");
  }

  @Test
  public void isValidOperationOnSort() {
    assertThat(Sequence.isValidOperation("SORT")).isTrue();
  }

  @Test
  public void isValidOperationOnInverse() {
    assertThat(Sequence.isValidOperation("iNvErsE")).isTrue();
  }

  @Test
  public void isValidOperationOnRemoveDuplicates() {
    assertThat(Sequence.isValidOperation("remove_DUPLICAtes")).isTrue();
  }

  @Test
  public void isValidOperationOnInvalidValue() {
    assertThat(Sequence.isValidOperation("delete_me")).isFalse();
  }

  @Test
  public void assureValidOperation() {
    assertThat(Sequence.assureValidOperation("sort")).isEqualTo(Operation.SORT);
  }

  @Test
  public void assureValidOperationThrowsExceptionOnIvalidValue() {
    String errorMessage =
        assertThrows(IllegalArgumentException.class, () -> Sequence.assureValidOperation("blabla"))
            .getMessage();
    assertThat(errorMessage).isEqualTo("No enum constant sequence.Sequence.Operation.BLABLA");
  }

  @Test
  public void toStringTest() {
    Sequence sequence = new Sequence("SORT", "aa;bb;cc;");
    assertThat(sequence.toString()).isEqualTo("Operation: [SORT]; values: [aa;bb;cc]");
  }

  @Test
  public void equalsTest() {
    Sequence sequence1 = new Sequence("SORT", "aa;bb;cc;");
    Sequence sequence2 = new Sequence("SORT", "aa;bb;cc;");
    assertThat(sequence1.equals(sequence2)).isTrue();
  }

  @Test
  public void equalsTestOnUnequalObjects() {
    Sequence sequence1 = new Sequence("SORT", "aa;bb;cc;");
    Sequence sequence2 = new Sequence("INVERSE", "aa;bb;cc;");
    assertThat(sequence1.equals(sequence2)).isFalse();
  }

  @Test
  public void hashCodeTest() {
    Sequence sequence1 = new Sequence("SORT", "aa;bb;cc;");
    Sequence sequence2 = new Sequence("SORT", "aa;bb;cc;");
    assertThat(sequence1.hashCode() == sequence2.hashCode()).isTrue();
  }
}

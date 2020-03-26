import java.util.List;

public class BeautyFormatter {

  public static String format(List<String> sequence) {
    StringBuilder sb = new StringBuilder();
    sequence.forEach(s -> sb.append(s).append("; "));
    return sb.substring(0, sb.length() - 2);
  }
}

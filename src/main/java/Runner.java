import filereader.LocalDiskFileReader;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import sequence.SequenceProcessor;

public class Runner {

  public static void main(String[] args) {
    String pathToTheFile = null;
    if (args.length == 0) {
      System.out.println("Please specify path to the file with sequences.........................");
      Scanner s = new Scanner(System.in);
      pathToTheFile = s.nextLine();
    } else {
      pathToTheFile = args[0];
    }

    List<List<String>> processedSequences =
        new SequenceProcessor(new LocalDiskFileReader(pathToTheFile)).process();
    List<String> formattedSequences =
        processedSequences.stream().map(BeautyFormatter::format).collect(Collectors.toList());

    System.out.println("=========================================================================");
    System.out.println("File " + pathToTheFile + " was processed correctly!");
    System.out.println("List of processed sequences below:");
    formattedSequences.forEach(System.out::println);
    System.out.println("=========================================================================");
  }
}

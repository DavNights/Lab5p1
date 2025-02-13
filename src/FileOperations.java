import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOperations {

    public static List<EquivalenceRow> ReadEQFile(String filename) throws FileNotFoundException, IOException {
        List<EquivalenceRow> equivalenceRows = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\crone\\Desktop\\IST140\\JavaProjects\\Lab5p1\\Eq.txt"));
        //Scanner scanner = new Scanner(bufferedReader);

        // Extract equivalence classes
        String nextLine = bufferedReader.readLine();
        while (nextLine != null) {
            // Create list to store read equivalence classes
            List<EquivalenceClass> classesForRow = new ArrayList<EquivalenceClass>();
            String[] pairs = nextLine.split(";");
            try {
                for (String pair : pairs) {
                    StringReader pairReader = new StringReader(pair);
                    Scanner pairScanner = new Scanner(pairReader);
                    int pairMin = pairScanner.nextInt();
                    int pairMax = pairScanner.nextInt();
                    // Release string scanner
                    pairScanner.close();
                    // Release string reader
                    pairReader.close();

                    // Record next equivalence class
                    classesForRow.add(new EquivalenceClass(pairMin, pairMax));
                }
            }
            catch (Exception e) {}

            // Record next row of equivalence classes
            equivalenceRows.add(new EquivalenceRow(classesForRow));

            // Move on to next row
            nextLine = bufferedReader.readLine();
        }
        // Release file reader
        bufferedReader.close();

        return equivalenceRows;
    }

    public void writeTestFile(List<List<EquivalenceClass>> equivalenceClassLines){

    }
}

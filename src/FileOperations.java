import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
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
            // TODO remove debug
            //System.out.println("Number of pairs for line: "+pairs.length);
            try {
                for (String pair : pairs) {
                    // TODO remove debug
                    //System.out.println("Pair: \""+pair+"\"");
                    StringReader pairReader = new StringReader(pair);
                    Scanner pairScanner = new Scanner(pairReader);
                    pairScanner.useDelimiter("[\\s,]+");
                    int pairMin = pairScanner.nextInt();
                    int pairMax = pairScanner.nextInt();
                    // Release string scanner
                    pairScanner.close();
                    // Release string reader
                    pairReader.close();

                    // Record next equivalence class
                    classesForRow.add(new EquivalenceClass(pairMin, pairMax));
                    //
                }
            }
            catch (Exception e) {
                // TODO remove debug
                //System.out.println("Got error: "+e);
            }

            // Record next row of equivalence classes
            equivalenceRows.add(new EquivalenceRow(classesForRow));

            // Move on to next row
            nextLine = bufferedReader.readLine();
        }
        // Release file reader
        bufferedReader.close();

        // TODO remove debug
        //System.out.println("Number of EQ rows: "+equivalenceRows.size());

        return equivalenceRows;
    }

    public int RandomCase(int max, int min)
    {
        Random rand = new Random();
        return rand.nextInt(max-min+1)+min;
    }


    public void writeTestFile(List<EquivalenceRow> equivalenceClassLines, Main testingInstance) throws IOException {
        Random rand = new Random();
        List<List<Integer>> testCases = new ArrayList<>();
        int numberOfTestCases = 10;
        for (int i = 0; i < numberOfTestCases; i++) {
            // Record inputs and output for test cases
            List<Integer> testCase = new ArrayList<>();
            int numberOfInputs = rand.nextInt(5)+1;
            // Array equivalent of testCase for use with foo()'s varargs
            Integer[] testCaseArray = new Integer[numberOfInputs];
            for (int inputIndex = 0; inputIndex < numberOfInputs; inputIndex++) {
                List<EquivalenceClass> eqClasses = equivalenceClassLines.get(inputIndex).equivalenceClasses;
                EquivalenceClass eqClass = eqClasses.get(rand.nextInt(eqClasses.size()));
                // Calculate random valid input within range
                int randomInput = RandomCase(eqClass.getMax(), eqClass.getMin());
                testCase.add(randomInput);
                testCaseArray[inputIndex] = randomInput;
            }
            // Add result of foo() to end of test case line
            testCase.add(testingInstance.foo(testCaseArray));
            // Add test case to list of test cases
            testCases.add(testCase);
        }

        // TODO Professor: replace with path to test.txt
        FileWriter filewriter = new FileWriter("C:\\Users\\crone\\Desktop\\IST140\\JavaProjects\\Lab5p1\\test.txt");
        try {
            for (int testCaseIndex = 0; testCaseIndex < testCases.size(); testCaseIndex++) {
                List<Integer> testCase = testCases.get(testCaseIndex);
                for (int paramIndex = 0; paramIndex < testCase.size(); paramIndex++) {
                    // Write input/output to test case
                    filewriter.write(Integer.toString(testCase.get(paramIndex)));
                    // Write comma if not at last parameter of test case
                    if (paramIndex + 1 < testCase.size())
                        filewriter.write(',');
                }
                // Write newline if not at last line
                if (testCaseIndex + 1 < testCases.size())
                    filewriter.write('\n');
            }
        }
        finally {
            filewriter.close();
        }
    }
}

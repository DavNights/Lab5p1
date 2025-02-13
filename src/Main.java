import java.io.IOException;
import java.util.List;

public class Main {
    private int row;
    private List<EquivalenceRow> equivalenceMap;

    public Main(List<EquivalenceRow> equivalenceClasses) {
        this.equivalenceMap = equivalenceClasses;
    }

    public static void main(String[] args) throws IOException {

        // Get equivalence classes
        List<EquivalenceRow> equivalenceClasses;
        equivalenceClasses = FileOperations.ReadEQFile("Eq.txt");

        Main testingInstance = new Main(equivalenceClasses);

        // Object for file operation, generate randomized test file
        FileOperations fileop = new FileOperations();
        fileop.writeTestFile(equivalenceClasses);

    }

    public int foo(Integer... n)
    {
        // Cancel early if no params provided
        if (n.length == 0)
            return 0;

        // Start summing
        int result = 0;
        // Check only as many numbers as provided (max 5)
        for (int i = 0; i < n.length && i < 5; i++) {
            result += equivalenceMap.get(i).checkWrapper(n[i]);
        }

        return result;
    }
}

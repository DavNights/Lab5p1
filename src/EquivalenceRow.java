import java.util.ArrayList;
import java.util.List;

public class EquivalenceRow {
    List<EquivalenceClass> equivalenceClasses;

    public EquivalenceRow(List<EquivalenceClass> equivalenceClasses) {
        this.equivalenceClasses = equivalenceClasses;
    }

    private int check(int val) {
        // Check val against all equivalence classes
        for (int classIndex = 0; classIndex < this.equivalenceClasses.size(); classIndex++) {
            EquivalenceClass equivalenceClass = this.equivalenceClasses.get(classIndex);

            // If val is in the range of the equivalence class, return the index of the equivalence class
            if (val >= equivalenceClass.getMin() && val <= equivalenceClass.getMax())
                return classIndex;
        }

        // Val is not in any of the equivalence classes
        return -1;
    }

    // Public wrapper for check() method
    public int checkWrapper(int val) {
        return this.check(val);
    }
}

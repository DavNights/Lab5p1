// Class to represent the equivalences with min and max values
public class EquivalenceClass {
    public int min;
    public int max;

    public EquivalenceClass(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin(){
        return min;
    }
    public int getMax(){
        return max;
    }
    public void setMin(){
        this.min = min;
    }
    public void setMax(){
        this.max = max;
    }
}

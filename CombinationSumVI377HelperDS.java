package DynamicProgramming;

/**
 * Created by li on 9/15/2016.
 */
public class CombinationSumVI377HelperDS {
    public int candidate;
    public int number;

    public CombinationSumVI377HelperDS(int _candidate) {
        candidate = _candidate;
        number = 0;
    }

    public String toString() {
        return "Candidate: " + candidate + "; Number: " + number;
    }
}

package DynamicProgramming;

/**
 * Created by li on 7/14/2016.
 */
public class RangeSumQueryImmutable303 {
    /* Memory exceeding
    int[][] dp;

    public RangeSumQueryImmutable303(int[] nums) {
        int lens = nums.length;
        dp = new int [lens][lens];
        for (int i = 0; i < lens; i++) {
            for (int j = i; j < lens; j++) {
                if (j == i) dp[i][j] = nums[j];
                else dp[i][j] = dp[i][j-1] + nums[j];
            }
        }
    }

    public int sumRange(int i, int j) {
        return dp[i][j];
    }*/

    int [] dp;
    boolean nullArrayFlag = false;

    public RangeSumQueryImmutable303(int[] nums) {
        if (nums.length != 0) {
            int lens = nums.length;
            dp = new int[lens];
            dp [0] = nums[0];
            for (int i = 1; i < lens; i++) {
                dp[i] = dp[i-1] + nums[i];
            }
        } else {
            nullArrayFlag = true;
        }

    }

    public int sumRange(int i, int j) {
        if (nullArrayFlag) {
            return 0;
        } else if (i == 0) {
            return dp[j];
        } else {
            return dp[j] - dp[i-1];
        }

    }
}

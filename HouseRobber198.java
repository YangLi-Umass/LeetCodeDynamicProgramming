package DynamicProgramming;

/**
 * Created by li on 7/14/2016.
 */
public class HouseRobber198 {

    public int robSmallSpace(int[] nums) {
        int lens = nums.length;

        if (lens == 0) return 0;
        if (lens == 1) return nums[0];
        if (lens == 2) return Math.max(nums[0], nums[1]);
        int minus1 = Math.max(nums[0], nums[1]);
        int minus2 = nums[0];
        int max = minus1;
        for (int i = 2; i < lens; i++) {
            max = Math.max(Math.max(minus2 + nums[i], minus1), max);
            minus2 = minus1;
            minus1 = max;
        }
        return max;
    }

    public int rob(int[] nums) {
        int lens = nums.length;

        if (lens == 0) return 0;
        if (lens == 1) return nums[0];
        if (lens == 2) return Math.max(nums[0], nums[1]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < lens; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[lens-1];
    }


    public int robBad(int[] nums) {
        int lens = nums.length;
        int[] dp = new int[nums.length];
        if (lens == 0) {
            return 0;
        } else if (lens == 1) {
            return nums[0];
        } else if (lens == 2) {
            return Math.max(nums[0], nums[1]);
        } else if (lens == 3) {
            return Math.max(nums[0] + nums[2], nums[1]);
        }
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = nums[2];

        int max  = Math.max(nums[0] + nums[2], nums[1]);
        for (int i = 3; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-3] + nums[i]);
            if (dp[i] > max) max = dp[i];
        }
        return max;
    }
}

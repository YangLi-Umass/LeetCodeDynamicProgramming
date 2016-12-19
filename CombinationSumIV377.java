package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by li on 9/15/2016.
 */
public class CombinationSumIV377 {

    public int combinationSum4DP(int[] nums, int target) {
        Arrays.sort(nums);
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int i = 1; i <= target; i++) {
            for(int j = 0; j < nums.length; j++) {
                if(i >= nums[j]) {
                    dp[i] = dp[i] + dp[i-nums[j]];
                } else break;
            }
        }
        return dp[target];
    }

    //public int combinationSum4Best(int[] nums, int target) {
    //    int[] dp = new int[target+1];

    //}

    /**
     * Backtracking
     *
     * */
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> l = new ArrayList<>();
        int[] r = new int[1];
        helper(nums, target, l, r);
        return r[0];
    }

    public void helper(int[] nums, int remain, List<Integer> l, int[] r) {
        if(remain == 0) {
            r[0]++;
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] <= remain) {
                l.add(nums[i]);
                helper(nums, remain-nums[i], l, r);
                l.remove(l.size()-1);
            } else break;
        }
    }








}

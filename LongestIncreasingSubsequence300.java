package DynamicProgramming;

import java.util.*;

/**
 * Created by li on 7/11/2016.
 */
public class LongestIncreasingSubsequence300 {


    public static void main(String[] args) {
        int[] array = {1,7,9,13,17};
        int[] myArray = {10,9,2,5,3,7,101,18};
        int[] leetArray = {3,5,6,2,5,4,19,5,6};
        int i = Arrays.binarySearch(array,0,4,16);
        //System.out.println(-i-1);
        System.out.println(lengthOfLIS3(myArray));
    }

    public static int lengthOfLIS1(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) i = -(i + 1);
            dp[i] = x;
            if(i == len) len++;
        }

        for (int i = 0; i < len; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();
        return len;
    }

    /**
     * DP O(n*n)
     *
     * */
    public int lengthOfLIS2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int max = 1;
        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j]+1,dp[i]);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }

    /**
     * Binary search O(nlogn)
     *
     * */
    public static int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        List<Integer> l = new ArrayList<>();
        l.add(nums[0]);
        Comparator<Integer> c = (i1, i2) -> {
            return i1.compareTo(i2);
        };
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > l.get(l.size()-1)) {
                l.add(nums[i]);
            } else {
                int index = Collections.binarySearch(l, nums[i], c);
                if(index<0) {
                    l.set(-index-1,nums[i]);
                }
            }
        }
        return l.size();
    }

    public static int lengthOfLIS3(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int len = 0;
        for(int temp:nums) {
            int i = Arrays.binarySearch(dp, 0, len, temp);
            if(i < 0) i = -i-1;
            dp[i] = temp;
            if(i ==  len) len++;
        }
        return len;

    }

}

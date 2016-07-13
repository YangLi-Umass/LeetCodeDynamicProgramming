package DynamicProgramming;

import java.util.Arrays;

/**
 * Created by li on 7/11/2016.
 */
public class LongestIncreasingSubsequence300 {


    public static void main(String[] args) {
        int[] array = {1,7,9,13,17};
        int[] myArray = {1,3,7,5,2,9,10,11};
        int[] leetArray = {3,5,6,2,5,4,19,5,6};
        int i = Arrays.binarySearch(array,0,4,16);
        //System.out.println(-i-1);
        System.out.println(lengthOfLIS(leetArray));
        System.out.println(myLengthOfLIS(leetArray));
    }

    public static int lengthOfLIS(int[] nums) {
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

    public static int myLengthOfLIS(int[] nums) {
        //这个方法不对  有bug
        int[] dp = new int[nums.length];
        int lens = 0;

        for (int num:nums) {
            int i = Arrays.binarySearch(dp, 0, lens, num);
            if (i < 0) i = -i-1;

            if (i == lens || i == lens-1) dp[i] = num;

            if (i == lens) {
                lens++;
            }

        }

        for (int i = 0; i < lens; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();
        return lens;
    }
}

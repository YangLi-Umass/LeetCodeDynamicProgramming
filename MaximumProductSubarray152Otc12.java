package DynamicProgramming;

/**
 * Created by li on 10/12/2016.
 */
public class MaximumProductSubarray152Otc12 {
    public int maxProduct(int[] nums) {
        int[] dp = new int[nums.length+1]; dp[0] = 0;
        int[] dpHelper = new int[nums.length+1]; dpHelper[0] = 1;
        int firstNegInBlock = -1;
        int maxProduct = dp[0];

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                //0是block的分隔符
                dp[i+1] = 0;
                dpHelper[i+1] = 1;
                firstNegInBlock = -1;
            } else if(nums[i] > 0) {
                dp[i+1] = Math.max(dp[i]*nums[i], nums[i]);
                dpHelper[i+1] = dpHelper[i] * nums[i];
            } else {
                //nums[i] < 0
                dpHelper[i+1] = dpHelper[i] * nums[i];
                if(firstNegInBlock == -1) {
                    //本block第一个负数
                    dp[i+1] = nums[i];
                    firstNegInBlock = i;
                } else {
                    if(dpHelper[i+1] > 0) dp[i+1] = dpHelper[i+1];
                    else dp[i+1] = dpHelper[i+1]/dpHelper[firstNegInBlock+1];
                }
            }
            maxProduct = Math.max(dp[i+1], maxProduct);
        }
        return maxProduct;
    }

    public int maxProductBetter(int[] nums) {
        int arraySize = nums.length;
        int[] dp = new int[arraySize+1];   dp[0] = 0;
        int[] dpHelper = new int[arraySize+1];  dpHelper[0] = 1;
        int firstNegInBlock = -1;
        int maxProduct = nums[0];
        for(int i = 1; i <= arraySize; i++) {
            if(nums[i-1] == 0) {
                dp[i] = 0;
                dpHelper[i] = 1;
                firstNegInBlock = -1;
            } else {
                dpHelper[i] = dpHelper[i-1]*nums[i-1];
                if(dpHelper[i] > 0) {
                    dp[i] = dpHelper[i];
                } else {
                    if(firstNegInBlock == -1) {
                        dp[i] = nums[i-1];
                        firstNegInBlock = i;
                    } else {
                        dp[i] = dpHelper[i]/dpHelper[firstNegInBlock];
                    }
                }
            }
            maxProduct = Math.max(dp[i], maxProduct);
        }
        return maxProduct;
    }
}

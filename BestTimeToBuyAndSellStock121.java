package DynamicProgramming;

/**
 * Created by li on 7/14/2016.
 */
public class BestTimeToBuyAndSellStock121 {
    public int maxProfit(int[] prices) {
        int minValueBeforeThisDay = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int temp:prices) {
            if (temp > minValueBeforeThisDay) {
                int currentProfit = temp - minValueBeforeThisDay;
                if (currentProfit > maxProfit) maxProfit = currentProfit;
            } else {
                minValueBeforeThisDay = temp;
            }
        }
        return maxProfit;
    }
}

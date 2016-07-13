package DynamicProgramming;

import java.util.Stack;

/**
 * Created by li on 7/11/2016.
 */
public class LongestValidParentheses32 {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses("()()(()))"));
    }
    //10ms
    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.size() > 0) {
                    dp[i] = 1;
                    dp[stack.pop()] = 1;
                } else dp[i] = 0;

            }
        }
        int current = 0;
        int max = 0;
        for (int temp:dp) {
            if (temp ==1) current++;
            else {
                if (current>max) max = current;
                current=0;
            }
        }
        return max;
    }

    //11s
    public static int longestValidParentheses2(String s) {
        Stack<Integer> stack = new Stack<>();
        int[] dp = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
                stack.push(i);
            } else {
                if (stack.size() > 0) {
                    int j = stack.pop();
                    if (i == j+1) {
                        dp[i] = 2;
                        if (i-2 >= 0) {
                            dp[i] += dp[i-2];
                        }
                    } else {
                        dp[i] = dp[i-1] + 2;
                        if (j-1 >= 0) {
                            dp[i] = dp[i] + dp[j-1];
                        }
                    }

                } else {
                    dp[i] = 0;
                }
            }
        }
        int max = 0;
        for (int temp:dp) {
            if (temp > max) max = temp;
        }
        return max;
    }

    //exceeding the time limit
    public static int longestValidParentheses1(String s) {

        Stack<Integer> stack = new Stack<>();
        int[] dp = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                dp[i] = -1;
                stack.push(i);
            }
            else {
                if (stack.size() > 0) {
                    int j = stack.pop();
                    dp[j] = 0;
                    dp[i] = 1;
                } else {
                    dp[i] = -1;
                }
            }
        }
        System.out.println(dp[0] + "," + dp[1]);

        int currentLength = 0;
        int maxLength = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == 1) {
                currentLength = currentLength + 2;
            } else if (dp[i] == -1) {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                }
                currentLength = 0;
            }
        }
        if (currentLength > maxLength) {
            maxLength = currentLength;
        }

        return maxLength;
    }
}

package DynamicProgramming;

import java.util.Stack;

/**
 * Created by li on 7/11/2016.
 */
public class LongestValidParentheses32Dec11 {

    /**
     * 第一种方法
     * 还是匹配的原理，记录下那些不能匹配char的index，然后他们之间的就是连续的有效的括号String
     * 找出他们中间最大的即可。
     *
     * */
    public int longestValidParentheses1(String s) {
        char[] c = s.toCharArray();
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            if(c[i] == '(') st.push(i);
            else {
                if(!st.isEmpty() && c[st.peek()] == '(') st.pop();
                else {
                    st.push(i);
                }
            }
        }

        int p = s.length();
        int max = 0;
        while(!st.isEmpty()) {
            max = Math.max(max, p - st.peek()-1);
            p = st.pop();
        }
        max = Math.max(max, p);
        return max;
    }

    /**
     * 第二种方法DP
     * 如果当前char是'('，那么以其为结尾的有效的括号String为0
     * 如果当前char是')'，情况1，如果前一个符号是'('，那么dp[i] = dp[i-2]+2;
     *                   情况2，如果前一个符号是')'，首先找出和[i-1]')'配对的'('，其index应该为[i-dp[i-1]];
     *                         那么我们要看[i-dp[i-1]-1]处char是不是'('，如果不是，dp[i]=0;
     *                                                                 如果是，dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]
     * 其实再整理一下逻辑，只要管')'就行，然后根据dp[i-1]找与其对应的'('，然后再看其之前的能不能连起来
     * 一定要注意数组越界的问题。
     *
     * */
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {      // i 要从1开始
            if(s.charAt(i) == ')' && i-dp[i-1]-1 >= 0 && s.charAt(i-dp[i-1]-1) == '(') {
                dp[i] = dp[i-1]+2;
                if(i-dp[i-1]-2 >= 0) dp[i] += dp[i-dp[i-1]-2];
                max = Math.max(max, dp[i]);
            }

        }
        return max;
    }
}

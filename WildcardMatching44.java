package DynamicProgramming;

import java.util.Arrays;

/**
 * Created by li on 7/9/2016.
 */
public class WildcardMatching44 {

    //Dynamic programming Space O(s.length())
    public boolean isMatchDpBest(String s, String p) {
        int m = s.length();
        int n = p.length();

        int count = 0;
        for (int j = 0; j < n; j++) {
            if (p.charAt(j) == '*') count++;
        }
        if (count == 0 && m != n) return false;
        if (n - count > m) return false;

        boolean[] dp = new boolean[m+1];

        dp[0] = true;

        for(int j = 1; j < n+1; j++) {
            if (p.charAt(j-1) != '*') {
                //genius
                for (int i = m; i >= 1; i--) {
                    dp[i] = (p.charAt(j-1) == '?' || p.charAt(j-1) == s.charAt(i-1)) && dp[i-1];
                }
                dp[0] = false;
            } else {    //p.charAt(j-1) == '*'
                for (int i = 1; i < m+1; i++) {
                    dp[i] = dp[i-1] || dp[i];
                }
            }
        }
        return dp[m];
    }

    //Dynamic programming Space O(mn)
    public boolean isMatchDp(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int j = 1; j < n+1; j++) {
            dp[0][j] = p.charAt(j-1) == '*' && dp[0][j-1];
        }

        for(int i = 1; i < m+1; i++) {
            for(int j = 1; j < n+1; j++) {
                if(p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                } else {
                    dp[i][j] = (p.charAt(j-1) == '?' || (s.charAt(i-1) == p.charAt(j-1))) && dp[i-1][j-1];
                }
            }
        }
        return dp[m][n];
    }


}

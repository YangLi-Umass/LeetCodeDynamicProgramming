package DynamicProgramming;

import java.util.Arrays;

/**
 * Created by li on 7/9/2016.
 */
public class WildcardMatching44 {

    public boolean isMatch(String s, String p) {

        return isMatch(s, 0, p ,0);
    }

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
        for(int i = 1; i < m+1; i++) {
            dp[i] = false;
        }

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
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i < m+1; i++) {
            dp[i][0] = false;
        }
        for (int j = 1; j < n+1; j++) {
            dp[0][j] = p.charAt(j-1)=='*' && dp[0][j-1];
        }

        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if(p.charAt(j-1 )!= '*') {
                    dp[i][j] = dp[i-1][j-1] && (p.charAt(j-1) == '?' || p.charAt(j-1) == s.charAt(i-1));
                } else {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }
            }
        }

        return dp[m][n];
    }


    //Time Limit Exceeded
    public boolean isMatch(String s, int sIndex, String p, int pIndex) {

        if (pIndex >= p.length()) return sIndex>=s.length();

        if (p.charAt(pIndex) != '*') {

            if (sIndex<s.length() && (p.charAt(pIndex) == s.charAt(sIndex) || p.charAt(pIndex) == '?'))
                return isMatch(s, sIndex+1, p, pIndex+1);
            else return false;

        } else {

            while (sIndex <= s.length()) {
                if (isMatch(s, sIndex, p, pIndex+1)) return true;
                sIndex++;
            }
            return false;
        }
    }

    //压缩后依旧超时
    public static String compressStarChar (String s) {
        if (s.equals("")) return "";
        if (s.equals("*")) return "*";
        StringBuilder stringBuilder = new StringBuilder();
        char currentChar = s.charAt(0);
        stringBuilder.append(currentChar);

        for (int i = 1; i < s.length(); i++) {
            if (currentChar == '*' && currentChar == s.charAt(i)) {

            } else {
                currentChar = s.charAt(i);
                stringBuilder.append(currentChar);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(compressStarChar("aaa********aaababaabaaabbabbbbbbbbaabababbabbb*****a"));
    }

}

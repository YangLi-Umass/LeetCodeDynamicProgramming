package DynamicProgramming;

import java.util.ArrayList;

/**
 * Created by li on 7/12/2016.
 */
public class RegularExpressionMatching10 {

    public static void main(String[] args) {
        String s = ".*";
        String p = "ab";
        System.out.println(isMatch(s,p));
    }

    //dynamic programming 5ms
    public static boolean isMatchDp(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];

        dp[0][0] = true;
        for (int i = 1; i < m+1; i++) {
            dp[i][0] = false;
        }

        for (int j = 1; j < n+1; j++) {
            dp[0][j] = p.charAt(j-1) == '*' && dp[0][j-2];
        }

        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if (p.charAt(j-1) != '*') {
                    dp[i][j] = dp[i-1][j-1] && (p.charAt(j-1) == '.' || p.charAt(j-1) == s.charAt(i-1));
                } else {
                    dp[i][j] = dp[i][j-2] || dp[i][j-1] ||
                            ((p.charAt(j-2) == '.' || s.charAt(i-1) == p.charAt(j-2)) && dp[i-1][j]);
                }
            }
        }
        return dp[m][n];

    }


    public static boolean isMatch(String s, String p) {

        return findCurrentMatch2(s, 0, p, 0);

    }


    //75ms
    public static boolean findCurrentMatch2 (String s, int sIndex, String p, int pIndex) {

        if (pIndex >= p.length()) return sIndex >= s.length();

        if (pIndex+1 >= p.length() || p.charAt(pIndex+1) != '*'){
            if (sIndex < s.length() && (p.charAt(pIndex) == '.' || p.charAt(pIndex) == s.charAt(sIndex)))
                return findCurrentMatch2(s, sIndex+1, p, pIndex+1);
            else
                return false;
        } else {
            System.out.println(s.length() + "," + pIndex);
            while (sIndex < s.length() && (p.charAt(pIndex) == '.' || p.charAt(pIndex) == s.charAt(sIndex))){
                if(findCurrentMatch2(s, sIndex, p, pIndex+2)) return true;
                sIndex ++;

            }
            return findCurrentMatch2(s, sIndex, p, pIndex+2);
        }

    }


    //57+ms
    public static boolean findCurrentMatch1 (String s, int sIndex, String p, int pIndex) {
        if (pIndex >= p.length()) return sIndex >= s.length();

        if (pIndex+1 >= p.length() || p.charAt(pIndex+1) != '*'){
            /*if (sIndex >= s.length()) return false;
            if (p.charAt(pIndex) == '.' || p.charAt(pIndex) == s.charAt(sIndex)) return findCurrentMatch1(s, sIndex+1, p, pIndex+1);
            else return false;*/

            //改写
            if (sIndex < s.length() && (p.charAt(pIndex) == '.' || p.charAt(pIndex) == s.charAt(sIndex)))
                return findCurrentMatch1(s, sIndex+1, p, pIndex+1);
            else
                return false;
        } else {

            if(sIndex >= s.length()) return findCurrentMatch1(s, sIndex, p, pIndex+2);
            if (findCurrentMatch1(s, sIndex, p, pIndex+2)) return true;
            int temp = findFirstNonGivenElement(s, s.charAt(sIndex), sIndex+1);
            if (p.charAt(pIndex) == '.') {
                temp = s.length();
            }
            if(s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.') {

                for (int k = sIndex+1; k <= temp; k++) {
                    if (findCurrentMatch1(s, k, p, pIndex+2)) return true;
                }
                return false;
            }

            //改写
            while (sIndex < s.length() && p.charAt(pIndex) == '.' || p.charAt(pIndex) == s.charAt(sIndex)){
                if(findCurrentMatch1(s, sIndex, p, pIndex+2)) return true;
                sIndex++;
            }
            return findCurrentMatch1(s, sIndex, p, pIndex+2);

        }

    }

    public static int findFirstNonGivenElement (String s, char c, int fromIndex) {
        while (fromIndex<s.length() && s.charAt(fromIndex) == c) {
            fromIndex++;
        }
        return fromIndex;
    }



}

package DynamicProgramming;

/**
 * Created by li on 10/11/2016.
 */
public class MaximalSquare221Oct11 {
    public int maximalSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;

        if (n == 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        int maxArea = 0;
        if(matrix[0][0] == 1) {
            dp[0][0] = 1;
            maxArea = 1;
        }
        else dp[0][0] = 0;

        for(int i = 1; i < m; i++) {
            if (matrix[i][0] == 1) {
                dp[i][0] = 1;
                maxArea = 1;
            }
            else dp[i][0] = 0;
        }

        for(int j = 1; j < n; j++) {
            if (matrix[0][j] == 1) {
                dp[0][j] = 1;
                maxArea = 1;
            }
            else dp[0][j] = 0;
        }

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) dp[i][j] = 0;
                else {
                    int minNeighbor = Math.min(dp[i-1][j], dp[i][j-1]);
                    minNeighbor = Math.min(minNeighbor, dp[i-1][j-1]);
                    dp[i][j] = minNeighbor + 1;
                    if (dp[i][j] > maxArea) {
                        maxArea = dp[i][j];
                    }
                }
            }
        }

        return maxArea*maxArea;
    }
}

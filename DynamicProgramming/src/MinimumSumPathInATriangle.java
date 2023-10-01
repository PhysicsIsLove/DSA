import java.util.Arrays;

public class MinimumSumPathInATriangle {
    public static void main(String[] args) {
        int[][] triangle = {
                {1, 0, 0, 0},
                {3, 6, 0, 0},
                {9, 1, 3, 0},
                {6, 4, 2, 3}
        };
        int[][] dp = new int[triangle.length][triangle[0].length];
        for(int i=0; i< triangle.length; i++){
            for(int j=0; j< triangle[0].length; j++){
                dp[i][j] = -1;
            }
        }
        int ans = findMinimumSumUsingRecursion(triangle, 0, 0);
        System.out.println("Ans using recursion " + ans);
        int ans1 = findMinimumSumUsingMemoization(triangle, dp, 0, 0);
        System.out.println("Ans using Memoization "+ ans1);
        int ans2 = findMinimumSumUsingTabulation(triangle);
        System.out.println("Ans using Tabulation "+ ans2);
    }
    public static int findMinimumSumUsingRecursion(int[][] triangle, int row, int col){
        if(row == triangle.length-1){
            return triangle[row][col];
        }
        int down = triangle[row][col] + findMinimumSumUsingRecursion(triangle, row+1 ,col);
        int diagonal = triangle[row][col] + findMinimumSumUsingRecursion(triangle, row+1, col+1);
        return Math.min(down, diagonal);
    }

    public static int findMinimumSumUsingMemoization(int[][] triangle, int[][] dp, int row, int col){
        if(row == triangle.length-1){
            return triangle[row][col];
        }
        if(dp[row][col] != -1){
            return dp[row][col];
        }
        int down = triangle[row][col] + findMinimumSumUsingRecursion(triangle, row+1 ,col);
        int diagonal = triangle[row][col] + findMinimumSumUsingRecursion(triangle, row+1, col+1);
        dp[row][col] = Math.min(down, diagonal);
        return dp[row][col];
    }

    public static int findMinimumSumUsingTabulation(int[][] triangle){
        int[][] dp = new int[triangle.length][triangle[0].length];
        for(int i=0; i< triangle[0].length; i++){
            dp[triangle.length-1][i] = triangle[triangle.length-1][i];
        }
        for(int i=triangle.length-2; i>=0; i--){
            for(int j=i; j>=0; j--){
                int fromBottom = triangle[i][j] + dp[i+1][j];
                int fromRightDiagonal = triangle[i][j] + dp[i+1][j+1];
                dp[i][j] = Math.min(fromRightDiagonal, fromBottom);
            }
        }
        return dp[0][0];
    }
}

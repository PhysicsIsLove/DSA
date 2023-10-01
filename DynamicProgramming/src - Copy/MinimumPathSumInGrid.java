public class MinimumPathSumInGrid {
    public static void main(String[] args) {
        int[][] grid = {
                {1,2,3,4},
                {9,1,4,6},
                {6,7,4,50},
                {9,3,40,2}
        };

        int ans = findMinimumPathSum(grid, grid.length-1, grid[0].length-1);
        System.out.println("Answer using recursion "+ ans);
        int[][] dp = new int[grid.length][grid[0].length];
        for(int i=0; i< grid.length; i++){
            for(int j=0; j< grid[0].length; j++){
                dp[i][j] = -1;
            }
        }
        int ans1 = findMinimumPathSumUsingMemoization(grid, grid.length-1, grid[0].length - 1, dp);
        System.out.println("Answer using memoization "+ ans1);

        int ans2 = findMinimumPathSumUsingTabulation(grid);
        System.out.println("Answer using Tabulation " + ans2);

    }
    public static int findMinimumPathSum(int [][] grid, int row, int col){
        if(row == 0 && col == 0){
            return grid[0][0];
        }
        int sumFromLeft = Integer.MAX_VALUE;
        int sumFromTop = Integer.MAX_VALUE;
        if(row > 0){
            sumFromTop = findMinimumPathSum(grid, row-1, col);
        }
        if(col > 0){
            sumFromLeft = findMinimumPathSum(grid, row, col-1);
        }
        return grid[row][col] + Math.min(sumFromLeft , sumFromTop);
    }

    public static int findMinimumPathSumUsingMemoization(int [][] grid, int row, int col, int[][] dp){
        if(row == 0 && col == 0){
            return grid[0][0];
        }
        if(dp[row][col] != -1){
            return dp[row][col];
        }
        int sumFromLeft = Integer.MAX_VALUE;
        int sumFromTop = Integer.MAX_VALUE;
        if(row > 0){
            sumFromTop = findMinimumPathSum(grid, row-1, col);
        }
        if(col > 0){
            sumFromLeft = findMinimumPathSum(grid, row, col-1);
        }
        dp[row][col] = grid[row][col] + Math.min(sumFromLeft , sumFromTop);
        return dp[row][col];
    }

    public static int findMinimumPathSumUsingTabulation(int [][] grid){
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        int rows = grid.length;
        int cols = grid[0].length;
        for(int i=0; i< rows; i++){
            for(int j=0; j< cols; j++){
                if(i == 0 && j == 0){
                    continue;
                }
                int top = Integer.MAX_VALUE;
                int left = Integer.MAX_VALUE;
                if(i > 0){
                    top = dp[i-1][j];
                }
                if(j > 0){
                    left = dp[i][j-1];
                }
                dp[i][j] = grid[i][j] + Math.min(top, left);
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }
}

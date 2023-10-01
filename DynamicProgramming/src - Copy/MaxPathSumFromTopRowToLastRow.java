public class MaxPathSumFromTopRowToLastRow {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 2, 3, 9},
                {100, 4, 5, 72},
                {2, 34, 56, 99},
                {1, 88, 4, 3}
        };
        int ans = Integer.MIN_VALUE;
        for(int i=0; i< grid[0].length; i++){
            ans = Math.max(ans, findMaxSumUsingRecursion(grid, 0, 0, i));
        }
        System.out.println("Answer using Recursion " + ans);

        int[][] dp = new int[grid.length][grid[0].length];
        for(int i=0; i< grid.length; i++){
            for(int j=0; j< grid[0].length; j++){
                dp[i][j] = -1;
            }
        }
        int ans1 = Integer.MIN_VALUE;
        for(int i=0; i< grid[0].length; i++){
            ans1= Math.max(ans1, findMaxSumUsingMemoization(grid, 0, 0, i, dp));
        }
        System.out.println("Answer using memoization "+ ans1);

        int ans3 = findMaxSumUsingTabulation(grid);
        System.out.println("Answer using Tabulation "+ ans3);

    }

    public static int findMaxSumUsingRecursion(int[][] grid, int currentSum, int row, int col){
        if(row < grid.length){
            currentSum += grid[row][col];
        } else{
            return currentSum;
        }
        int leftDiagonalSum = Integer.MIN_VALUE;
        int downSum = 0;
        int rightDiagonalSum = Integer.MIN_VALUE;

        if(col - 1 >= 0){
            leftDiagonalSum = findMaxSumUsingRecursion(grid, currentSum, row+1, col-1);
        }
        if(col + 1 < grid[0].length){
            rightDiagonalSum = findMaxSumUsingRecursion(grid, currentSum, row+1, col+1);
        }
        downSum = findMaxSumUsingRecursion(grid, currentSum, row+1, col);
        return Math.max(downSum, Math.max(leftDiagonalSum, rightDiagonalSum));

    }

    public static int findMaxSumUsingMemoization(int[][] grid, int currentSum, int row, int col, int[][] dp){
        if(row < grid.length){
            currentSum += grid[row][col];
        } else{
            return currentSum;
        }
        if(dp[row][col] != -1){
            return dp[row][col];
        }
        int leftDiagonalSum = Integer.MIN_VALUE;
        int downSum = 0;
        int rightDiagonalSum = Integer.MIN_VALUE;

        if(col - 1 >= 0){
            leftDiagonalSum = findMaxSumUsingRecursion(grid, currentSum, row+1, col-1);
        }
        if(col + 1 < grid[0].length){
            rightDiagonalSum = findMaxSumUsingRecursion(grid, currentSum, row+1, col+1);
        }
        downSum = findMaxSumUsingRecursion(grid, currentSum, row+1, col);
        dp[row][col] = Math.max(downSum, Math.max(leftDiagonalSum, rightDiagonalSum));
        return dp[row][col];

    }

    public static int findMaxSumUsingTabulation(int[][] grid){
        int[][] dp = new int[grid.length][grid[0].length];
        for(int j=0; j< grid[grid.length-1].length; j++){
            dp[dp.length-1][j] = grid[grid.length-1][j];
        }
        for(int i=grid.length-2; i>=0; i--){
            int row = i;

            for(int j=0; j< grid[grid.length-2].length; j++){
                int leftDiagonal = Integer.MIN_VALUE;
                int rightDiagonal = Integer.MIN_VALUE;

                if(j!=0){
                    leftDiagonal = dp[i+1][j-1] + grid[i][j];
                }
                if(j!= grid[0].length-1){
                    rightDiagonal = dp[i+1][j+1] + grid[i][j];
                }
                int down = dp[i+1][j] + grid[i][j];
                dp[i][j] = Math.max(down, Math.max(leftDiagonal, rightDiagonal));
            }
        }
        int ans = Integer.MIN_VALUE;
        for(int i=0; i< grid[0].length; i++){
            ans = Math.max(ans, dp[0][i]);
        }
        System.out.println("the tabulation table is ");
        for(int i=0; i< dp.length; i++){
            for(int j=0; j< dp[0].length; j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return ans;
    }
}

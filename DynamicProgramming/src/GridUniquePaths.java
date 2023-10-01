import java.util.Arrays;

public class GridUniquePaths {
    public static void main(String[] args) {
        int rows = 4;
        int cols = 6;

        int ans = findTotalUniquePathsUsingRecursion(rows-1, cols-1);
        System.out.println("Answer using recursion "+ ans);
        int[][] dp = new int[rows][cols];
        for(int i=0; i< rows; i++){
            for(int j=0; j< cols; j++){
                dp[i][j] = -1;
            }
        }
        int ans1 = findTotalUniquePathsUsingMemoization(rows-1, cols-1, dp);
        System.out.println("Answer using memoization "+ ans1);

        int ans2 = findTotalUniquePathsUsingTabulation(rows, cols);
        System.out.println("Answer using tabulation "+ ans2);

        int ans3 = findTotalUniquePathsUsingSpaceOptimization(rows, cols);
        System.out.println("Answer using space optimization "+ ans3);
    }

   public static int findTotalUniquePathsUsingRecursion(int row, int col){
        if(row == 0 && col == 0){
            return 1;
        }
        int totalPathsFromUp = 0;
        int totalPathsFromLeft = 0;
        if(col - 1 >= 0){
            totalPathsFromLeft = findTotalUniquePathsUsingRecursion(row, col-1);
        }
        if(row -1 >= 0){
            totalPathsFromUp = findTotalUniquePathsUsingRecursion(row-1, col);
        }
        return totalPathsFromLeft + totalPathsFromUp;
   }

   public static int findTotalUniquePathsUsingMemoization(int row, int col, int[][] dp){
        if(row == 0 && col == 0){
            return 1;
        }
        if(dp[row][col] != -1){
            return dp[row][col];
        }
       int totalPathsFromUp = 0;
       int totalPathsFromLeft = 0;
       if(col - 1 >= 0){
           totalPathsFromLeft = findTotalUniquePathsUsingRecursion(row, col-1);
       }
       if(row -1 >= 0){
           totalPathsFromUp = findTotalUniquePathsUsingRecursion(row-1, col);
       }
       dp[row][col] = totalPathsFromLeft + totalPathsFromUp;
       return dp[row][col];
   }

   public static int findTotalUniquePathsUsingTabulation(int rows, int cols){
        int[][] dp = new int[rows][cols];
        dp[0][0] = 1; // base case, if the person is already at the destination
       for(int i=0; i< rows; i++){
           for(int j=0; j< cols; j++){
               if(i == 0 || j == 0){
                   dp[i][j] = 1;
                   continue;
               }
               int top = 0;
               int left = 0;
               if(i > 0){
                   top = dp[i-1][j];
               }
               if(j > 0){
                   left = dp[i][j-1];
               }
               dp[i][j] = top + left;
           }
       }
       return dp[rows-1][cols-1];
   }

    public static int findTotalUniquePathsUsingSpaceOptimization(int rows, int cols){
        int[] tempArr = new int[cols];
        tempArr[0] = 1;
        for(int i=0; i< rows; i++){
            int[] currArr = new int[cols];
            for(int j=0; j < cols; j++){
                int top = tempArr[j];
                int left = j>0 ? currArr[j-1] : 0;
                currArr[j] = top + left;
            }
            tempArr = currArr;
        }
        return tempArr[cols-1];
    }
}

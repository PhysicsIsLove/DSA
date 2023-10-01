import java.util.Arrays;

public class MinimumCoinsChange {
    public static void main(String[] args) {
        int[] coins = {1, 5, 8, 10};
        int change = 11;

        int ans = findUsingRecursion(coins, change, coins.length-1, 0);
        System.out.println("Answer using recursion "+ ans);

        int[][] dp = new int[coins.length][change + 1];
        Arrays.stream(dp).forEach(item -> Arrays.fill(item, -1));
        int ans1 = findUsingMemoization(coins, change, coins.length-1, 0, dp);
        System.out.println("Answer using memoization "+ ans1);

        int ans2 = findUsingTabulation(coins, change);
        System.out.println("Answer using tabulation " + ans2);

        int ans3 = findUsingSpaceOptimization(coins, change);
        System.out.println("Answer using Sapce optimization "+ ans3);

    }

    public static int findUsingRecursion(int[] coins, int change, int index, int numCoins){
        if(index == -1){
            if(change == 0){
                return numCoins;
            } else{
                return Integer.MAX_VALUE;
            }
        }
        int notTake = findUsingRecursion(coins, change, index-1, numCoins);
        int take = Integer.MAX_VALUE;
        if(coins[index] <= change){
            take = findUsingRecursion(coins, change-coins[index], index, numCoins+1);
        }
        return Math.min(take, notTake);
    }

    public static int findUsingMemoization(int[] coins, int change, int index, int numCoins, int[][] dp){
        if(index == -1){
            if(change == 0){
                return numCoins;
            } else{
                return Integer.MAX_VALUE;
            }
        }
        if(dp[index][change] != -1){
            return dp[index][change];
        }
        int notTake = findUsingRecursion(coins, change, index-1, numCoins);
        int take = Integer.MAX_VALUE;
        if(coins[index] <= change){
            take = findUsingRecursion(coins, change-coins[index], index, numCoins+1);
        }
        dp[index][change] =  Math.min(take, notTake);
        return dp[index][change];
    }

    public static int findUsingTabulation(int[] coins, int change){
        int[][] dp = new int[coins.length][change+1];
        // base case
        Arrays.stream(dp).forEach(item -> Arrays.fill(item, Integer.MAX_VALUE - 1));
        for(int i=1; i<= change; i++){
            if(i % coins[0] == 0){
                dp[0][i] = i / coins[0];
            }
        }
        for(int i=1; i< coins.length; i++){
            for(int j=1; j<= change; j++){
                int notTake = dp[i-1][j];
                int take = Integer.MAX_VALUE;
                if(coins[i] <= j){
                    take = 1 + dp[i][j-coins[i]];
                }
                dp[i][j] = Math.min(take, notTake);
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    public static int findUsingSpaceOptimization(int[] coins, int change){
        int[] prev = new int[change+1];
        int[] curr = new int[change + 1];
        // base case
         Arrays.fill(prev, Integer.MAX_VALUE - 1);
        Arrays.fill(curr, Integer.MAX_VALUE - 1);
        for(int i=1; i<= change; i++){
            if(i % coins[0] == 0){
                prev[i] = i / coins[0];
            }
        }
        for(int i=1; i< coins.length; i++){
            for(int j=1; j<= change; j++){
                int notTake = prev[j];
                int take = Integer.MAX_VALUE;
                if(coins[i] <= j){
                    take = 1 + curr[j-coins[i]];
                }
                curr[j] = Math.min(take, notTake);
            }
            for(int k=0; k< prev.length; k++){
                prev[k] = curr[k];
                curr[k] = Integer.MAX_VALUE - 1;
            }
        }
        return prev[prev.length-1];
    }
}

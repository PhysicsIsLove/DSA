import java.util.Arrays;

/**
 * Given certain coin denominations find the total number of ways in which you can make the change for a particular sum
 * using the coins provided. An unlimited number of coins of each denomination is at out disposal
 */
public class WaysToMakeCoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 2, 3, 7};
        int change = 10;
        int ans = findUsingRecursion(coins, change, coins.length-1);
        System.out.println("Answer using recursion "+ ans);

        int[] dp = new int[coins.length];
        Arrays.fill(dp, -1);
        int ans1 = findUsingMemoization(coins, change, coins.length-1, dp);
        System.out.println("Answer using meomization "+ ans1);

        int ans2 = findUsingTabulation(coins, change);
        System.out.println("Asnwer using tabulation "+ ans2);
    }

    public static int findUsingRecursion(int[] coins, int change, int index){
        if(index == -1){
            if(change == 0){
                return 1;
            } else{
                return 0;
            }
        }
        int notTake = findUsingRecursion(coins, change, index-1);
        int take = 0;
        if(coins[index] <= change){
            take = findUsingRecursion(coins, change-coins[index], index);
        }
        return take + notTake;
    }

    public static int findUsingMemoization(int[] coins, int change, int index, int[] dp){
        if(index == -1){
            if(change == 0){
                return 1;
            } else{
                return 0;
            }
        }
        if(dp[index] != -1){
            return dp[index];
        }
        int notTake = findUsingRecursion(coins, change, index-1);
        int take = 0;
        if(coins[index] <= change){
            take = findUsingRecursion(coins, change-coins[index], index);
        }
        dp[index] = take + notTake;
        return dp[index];
    }

    public static int findUsingTabulation(int[] coins, int change){
        int[][] dp = new int[coins.length][change + 1];
        Arrays.stream(dp).forEach(item -> Arrays.fill(item, 0));
        for(int i=0; i< coins.length; i++){
            dp[i][0] = 1;
        }
        for(int i=0; i<=change; i++){
            if(coins[0] <= i){
                if(i == coins[0]){
                    dp[0][i] = 1;
                } else{
                    dp[0][i] = dp[0][i-coins[0]];
                }
            }else{
                dp[0][i] = 0;
            }
        }

        for(int i=1; i < coins.length; i++){
            for(int j=1; j<= change; j++){
                if(coins[i] <= j){
                    int notTake = dp[i-1][j];
                    int take = dp[i][j-coins[i]];
                    dp[i][j] = take + notTake;
                } else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}

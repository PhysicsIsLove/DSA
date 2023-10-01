import java.util.Arrays;

public class WaysToClimbNthStair {
    public static void main(String[] args) {
        int n = 10;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        System.out.println("using the tabulation " + findUsingTabulation(n));
        System.out.println("using the memoization " + findUsingMemoization(n, dp));
    }

    public static int findUsingTabulation(int n){
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public static int findUsingMemoization(int n, int[] dp){
        if(n ==0 || n == 1){
            return 1;
        }
        if(dp[n] != -1){
            return dp[n];
        } else{
            dp[n] = findUsingMemoization(n-1, dp) + findUsingMemoization(n-2, dp);
            return dp[n];
        }
    }
}

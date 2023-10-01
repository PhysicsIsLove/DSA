import java.util.Arrays;

public class FibonacciSeries {
    public static void main(String[] args) {
        nthFibUsingTabulation(5);
        nthFibUsingTabulationAndSpaceOptimization(5);
        int[] dp = new int[6];
        Arrays.fill(dp, -1);
        int n = nthFibUsingMemoization(5, dp);
        System.out.println("n th fib using memoization :" + n);
    }

    public static void nthFibUsingTabulation(int n){
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println("n th fib using tabulation : " + dp[n]);
    }

    public static void nthFibUsingTabulationAndSpaceOptimization(int n){

        int seccondPrev = 0;
        int prev = 1;
        int current = -1;
        for(int i=2; i<=n; i++){
            current = prev + seccondPrev;
            seccondPrev = prev;
            prev = current;
        }
        System.out.println("n th fib using tabulation and space optimization : " + current);
    }

    public static int nthFibUsingMemoization(int n, int[] dp){
        if(n <= 1){
            return n;
        }
        if(dp[n] != -1){
            return dp[n];
        } else{
            dp[n] = nthFibUsingMemoization(n-1, dp) + nthFibUsingMemoization(n-2, dp);
            return dp[n];
        }
    }
}

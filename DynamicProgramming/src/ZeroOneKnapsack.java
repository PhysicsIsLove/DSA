import java.util.Arrays;

public class ZeroOneKnapsack {
    public static void main(String[] args) {
        int[] weights = {3, 5, 4};
        int[] values = {50, 30, 90};
        int bagWeight = 8;

        int ans = findUsingRecursion(weights, values, bagWeight, weights.length-1, 0);
        System.out.println("Answer using recursion "+ ans);

        int[][] dp = new int[weights.length][bagWeight+1];
        Arrays.stream(dp).forEach(item -> Arrays.fill(item, -1));
        int ans1 = findUsingMemoization(weights, values, bagWeight, weights.length-1, 0, dp);
        System.out.println("Answer using memoization "+ ans1);
        int ans2 = findUsingTabulation(weights, values, bagWeight);
        System.out.println("Asnwer using tabulation "+ ans2);
        int ans3 = findUsingSpaceOptimization(weights, values, bagWeight);
        System.out.println("Answer using space optimization "+ ans3);
    }

    public static int findUsingRecursion(int[] weights, int[] values, int bagWeight, int index, int currentValue){
        if(index == -1){
            return currentValue;
        }
        int notTake = findUsingRecursion(weights, values, bagWeight, index-1, currentValue);
        int take = Integer.MIN_VALUE;
        if(weights[index] <= bagWeight){
            take = findUsingRecursion(weights, values, bagWeight - weights[index],index-1, currentValue + values[index]);
        }
        return Math.max(take, notTake);
    }

    public static int findUsingMemoization(int[] weights, int[] values, int bagWeight, int index, int currentValue, int[][] dp){
        if(index == -1){
            return currentValue;
        }
        if(dp[index][bagWeight] != -1){
            return dp[index][bagWeight];
        }
        int notTake = findUsingRecursion(weights, values, bagWeight, index-1, currentValue);
        int take = Integer.MIN_VALUE;
        if(weights[index] <= bagWeight){
            take = findUsingRecursion(weights, values, bagWeight - weights[index],index-1, currentValue + values[index]);
        }
        dp[index][bagWeight] = Math.max(take, notTake);
        return dp[index][bagWeight];
    }

    public static int findUsingTabulation(int[] weights, int[] values, int bagWeight){
        int[][] dp = new int[weights.length][bagWeight+1];
        for(int i=weights[0]; i<= bagWeight; i++){
            dp[0][i] = values[0];
        }
        for(int i=1; i< dp.length; i++){
            for(int j=1; j<=bagWeight; j++){
                int notTake = dp[i-1][j];
                int take = Integer.MIN_VALUE;
                if(weights[i] <= j){
                    take = values[i] + dp[i-1][j - weights[i]];
                }
                dp[i][j] = Math.max(take, notTake);
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    public static int findUsingSpaceOptimization(int[] weights, int[] values, int bagWeight){
        int[] dp = new int[bagWeight+1];
        for(int i=weights[0]; i<= bagWeight; i++){
            dp[i] = values[0];
        }
        for(int i=0; i < weights.length; i++){
            for(int j=bagWeight; j >= 1; j--){
                int notTake = dp[j];
                int take = Integer.MIN_VALUE;
                if(weights[i] <= j){
                    take = values[i] + dp[j - weights[i]];
                }
                dp[j] = Math.max(take, notTake);
            }
        }
        return dp[dp.length-1];
    }
}

import java.util.Arrays;

public class MinimumCostToCutStick {
    public static void main(String[] args) {
        int[] arr = {0, 3, 5, 8, 12}; //  the first and the last elements do not represent a cut
        Arrays.sort(arr);

        int ans = findUsingRecursion(arr, 0, arr.length-1);

        System.out.println("Answer using Recursion: "+ ans);

        int[][] dp = new int[arr.length][arr.length];
        Arrays.stream(dp).forEach(item -> Arrays.fill(item, -1));
        int ans1 = findUsingMemoization(arr, 0, arr.length-1, dp);
        System.out.println("answer using memoization "+ ans1);

        int ans2 = findUsingTabulation(arr);
        System.out.println("Answer using Tabulation "+ ans2);
    }

    public static int findUsingRecursion(int[] arr, int i, int j){
        if( j - i == 1){
            return 0;
        }
        int min = Integer.MAX_VALUE;

        for(int k=i+1; k < j; k++){
            // make a cut at k
            int leftCost = findUsingRecursion(arr, i, k);
            int rightCost = findUsingRecursion(arr, k, j);
            int totalCost = (arr[j] - arr[i] + leftCost + rightCost);
            min = Math.min(min, totalCost);
        }
        return min;

    }

    public static int findUsingMemoization(int[] arr, int i, int j ,int[][] dp){
        if( j - i == 1){
            return 0;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        int min = Integer.MAX_VALUE;

        for(int k=i+1; k < j; k++){
            // make a cut at k
            int leftCost = findUsingMemoization(arr, i, k, dp);
            int rightCost = findUsingMemoization(arr, k, j, dp);
            int totalCost = (arr[j] - arr[i] + leftCost + rightCost);
            min = Math.min(min, totalCost);
        }
        dp[i][j] = min;
        return dp[i][j];

    }

    public static int findUsingTabulation(int[] arr){
        int[][] dp = new int[arr.length][arr.length];

        for(int i=arr.length-2; i>=0; i--){
            for(int j=i+1; j < arr.length; j++){
                if(j == i+1){
                    dp[i][j] = 0;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                for(int k=i+1; k < j; k++){
                    // make a cut at k
                    int leftCost = dp[i][k];
                    int rightCost = dp[k][j];
                    int totalCost = (arr[j] - arr[i] + leftCost + rightCost);
                    min = Math.min(min, totalCost);
                }
                dp[i][j] = min;
            }
        }

        return dp[0][dp.length-1];

    }


}

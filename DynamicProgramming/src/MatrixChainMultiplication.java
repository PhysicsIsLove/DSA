import java.util.Arrays;

public class MatrixChainMultiplication {
    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, 5 , 10, 25};
        int ans = findUsingRecursion(arr, 1, arr.length-1);
        System.out.println("Answer is "+ ans);

        System.out.println("Now using memoization ");
        int[][] dp = new int[arr.length][arr.length];
        Arrays.stream(dp).forEach(item -> Arrays.fill(item, -1));
        int ans1 = findUsingMemoization(arr, 1, arr.length-1, dp);
        System.out.println("Answer using memoization "+ ans1);

        int ans2 = findUsingTabulation(arr);
        System.out.println("Answer using tabulation "+ ans2);
    }

    public static int findUsingRecursion(int[] arr, int i, int j){
        if(i == j){
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for(int k=i; k < j; k++){
            int partition1 = findUsingRecursion(arr, i, k);
            int partition2 = findUsingRecursion(arr, k+1, j);
            int numOperations = partition1 + partition2 + arr[i-1] * arr[k] * arr[j];
            if(i == 1 && j == arr.length-1){
                System.out.println("---- "+ numOperations + " ----");
            }
            ans = Math.min(ans, numOperations);
        }

        return ans;
    }

    public static int findUsingMemoization(int[] arr, int i, int j, int[][] dp){
        if(i == j){
            return 0;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        int ans = Integer.MAX_VALUE;
        for(int k=i; k < j; k++){
            int partition1 = findUsingMemoization(arr, i, k, dp);
            int partition2 = findUsingMemoization(arr, k+1, j, dp);
            int numOperations = partition1 + partition2 + arr[i-1] * arr[k] * arr[j];
            if(i == 1 && j == arr.length-1){
                System.out.println("---- "+ numOperations + " ----");
            }
            ans =  Math.min(ans, numOperations);
        }
        dp[i][j] =ans;

        return dp[i][j];
    }

    public static int findUsingTabulation(int[] arr){
        int[][] dp = new int[arr.length][arr.length];
        for(int i=0; i< arr.length; i++){
            dp[i][i] = 0;
        }
        for(int i=arr.length-1; i >= 1; i--){
            for(int j=i+1; j<arr.length ; j++){
                int min = Integer.MAX_VALUE;
                for(int k=i; k < j; k++){
                    int partition1 = dp[i][k];
                    int partition2 = dp[k+1][j];
                    int numOperations = partition1 + partition2 + arr[i-1] * arr[k] * arr[j];
                    if(i == 1 && j == arr.length-1){
                        System.out.println("---- "+ numOperations + " ----");
                    }
                   min = Math.min(min, numOperations);
                }
                dp[i][j] = min;
            }
        }
        return dp[1][dp.length-1];

    }

    // TODO
    public static void findUsingTabulation2(int[] arr){
        int[][] matrixSizes = new int[arr.length-1][2];
        for(int i=1; i<arr.length; i++){
            matrixSizes[i-1][0] = arr[i-1];
            matrixSizes[i-1][1] = arr[i];
        }
        int[] dp = new int[matrixSizes.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1; i<matrixSizes.length; i++){

        }
    }
}

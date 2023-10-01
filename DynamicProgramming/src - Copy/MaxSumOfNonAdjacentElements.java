import java.util.Arrays;

public class MaxSumOfNonAdjacentElements {

    public static void main(String[] args) {
        int[] arr = {2, 1, 4 , 9};
        int[] dp = new int[arr.length];
        int[] dp2 = new int[arr.length];
        Arrays.fill(dp, -1);
        Arrays.fill(dp2, -1);
        int ans = findMaxSum(arr, 0, dp);
        System.out.println("Max sum of non-adjacent elements, using index = 0: "+ ans);

        System.out.println("the dp array is ");
        for(int item : dp){
            System.out.print(item + " ");
        }

        int ans2 = findMaxSumUsingMemoization(arr, arr.length-1, dp2);
        System.out.println("Max sum of non-adjacent elements, using index = n-1: "+ ans2);

        System.out.println("the dp array is ");
        for(int item : dp2){
            System.out.print(item + " ");
        }

        int ans4 = findMaxSumUsingTabulation(arr);

        int ans5 = findMaxSumUsingSpaceOptimization(arr);
        System.out.println("Answer using space optimization "+ ans5);
    }

    public static int findMaxSum(int[] arr, int index, int[] dp){
        if(index >= arr.length){
            return 0;
        }
        if(dp[index] != -1){
            return dp[index];
        }
        int sum1 = arr[index] + findMaxSum(arr, index + 2, dp);
        int sum2 = findMaxSum(arr, index + 1, dp);
        dp[index] = Math.max(sum1, sum2);
        return dp[index];
    }

    public static int findMaxSumUsingMemoization(int[] arr, int index, int[] dp2){
        if(index < 0){
            return 0;
        }
        if(dp2[index] != -1){
            return dp2[index];
        }
        int sum1 = arr[index] + findMaxSumUsingMemoization(arr, index - 2, dp2);
        int sum2 = findMaxSumUsingMemoization(arr, index - 1, dp2);
        dp2[index] = Math.max(sum1, sum2);
        return dp2[index];
    }

    public static int findMaxSumUsingTabulation(int[] arr){
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        for(int i=1; i< arr.length; i++){
            int pick = arr[i];
            if(i > 1){
                pick = arr[i] + dp[i-2];
            }
            int notPick = dp[i-1];
            dp[i] = Math.max(pick, notPick);
        }

        System.out.println("\nInside tabulation the dp array is ");
        for(int item : dp){
            System.out.print(item + " ");
        }
        System.out.println("\nThe answer is " + dp[dp.length - 1]);
        return dp[dp.length-1];
    }

    public static int findMaxSumUsingSpaceOptimization(int[] arr){
        int prev = arr[0];
        int prev2 = 0;
        for(int i=1; i< arr.length; i++){
            int pick = arr[i] + prev2;
            int notPick = prev;
            int maxTillHere = Math.max(pick, notPick);
            prev2 = prev;
            prev = maxTillHere;
        }
        return prev;
    }
}

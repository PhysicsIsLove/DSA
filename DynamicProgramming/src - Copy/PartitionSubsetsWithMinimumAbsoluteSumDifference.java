import java.util.Arrays;

public class PartitionSubsetsWithMinimumAbsoluteSumDifference {

    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) {
        int[] arr = {4, 2, 1, 5, 6 ,3};
        int totalSum = Arrays.stream(arr).reduce(0, (a, b) -> a + b);
        findUsingRecursion(arr, 0, arr.length-1, totalSum);
        System.out.println("Minimum difference sum "+ ans);

        int ans1 = findUsingRecursion2(arr, 0, arr.length-1, totalSum);
        System.out.println("Answer using second recursion type "+ ans1);

        int[][] dp = new int[arr.length+1][totalSum+1];
        Arrays.stream(dp).forEach(item -> Arrays.fill(item, -1));
        int ans2 = findUsingMemoization(arr, 0, arr.length-1, totalSum, dp);
        System.out.println("Answer using memoization "+ ans2);

        int ans3 = findUsingTabulation(arr);
        System.out.println("answer using tabulation "+ ans3);

    }
    public static void findUsingRecursion(int[] arr, int currentSum, int index, int totalSum){
        if(index == 0){
            int otherSum = totalSum - currentSum;
            int absDiff = Math.abs(currentSum - otherSum);
            ans = Math.min(absDiff, ans);
            return;
        }
        findUsingRecursion(arr, currentSum, index - 1, totalSum);
        findUsingRecursion(arr, currentSum+arr[index], index-1, totalSum);
        return;
    }

    public static int findUsingRecursion2(int[] arr, int currentSum, int index, int totalSum){
        if(index == -1){
            int otherSum = totalSum - currentSum;
            int absDiff = Math.abs(currentSum - otherSum);
            return absDiff;
        }
        int notTake = findUsingRecursion2(arr, currentSum, index - 1, totalSum);
        int take = findUsingRecursion2(arr, currentSum + arr[index], index-1, totalSum);
        return Math.min(notTake, take);
    }

    public static int findUsingMemoization(int[] arr, int currentSum, int index, int totalSum, int[][] dp){
        if(index == -1){
            int otherSum = totalSum - currentSum;
            int absDiff = Math.abs(currentSum - otherSum);
            return absDiff;
        }
        if(dp[index][currentSum] != -1){
            return dp[index][currentSum];
        }
        int notTake = findUsingRecursion2(arr, currentSum, index - 1, totalSum);
        int take = findUsingRecursion2(arr, currentSum + arr[index], index-1, totalSum);
        dp[index][currentSum] = Math.min(notTake, take);

        return dp[index][currentSum];
    }

    public static int findUsingTabulation(int[] arr){
        int totalSum = Arrays.stream(arr).reduce(0, (a, b) -> a + b);
        boolean[][] dp = new boolean[arr.length][totalSum+1];
        dp[0][arr[0]] = true;
        for(int i=0; i< dp.length; i++){
            dp[i][0] = true;
        }
        for(int i=1; i<arr.length; i++){
            for(int j=1; j< dp[0].length; j++){
                boolean notTake = dp[i-1][j];
                boolean take = false;
                if(arr[i] <= j){
                    take = dp[i-1][j-arr[i]];
                }
                dp[i][j] = take || notTake;
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i=1; i< dp[0].length; i++){
            ans = Math.min(ans, Math.abs(i- ( totalSum-i)));
        }
        return ans;
    }
}

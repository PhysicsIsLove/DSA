import java.util.Arrays;

/**
 * Whether a subset exists whose sum is equal to that subset.
 */
public class SubsetSumEqualToTarget {
    public static void main(String[] args) {
        int[] arr = {4, 2, 1, 5, 6 ,3};
        int targetSum = 90;
        boolean ans = findUsingRecursion(arr, targetSum, arr.length-1);
        System.out.println("Ans using recursion "+ ans);
        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        int ans1 = findUsingMemoization(arr, targetSum, arr.length-1, dp);
        System.out.println("Answer using memoization "+ (ans1 == 1));

        boolean ans2 = findUsingTabulation(arr, targetSum);
        System.out.println("Answer using tabulation "+ ans2);

        boolean ans3 = findUsingSpaceOptimization(arr, targetSum);
        System.out.println("Answer using tabulation and space optimization " + ans3);
    }

    public static boolean findUsingRecursion(int[] arr, int target, int index){
        if(target == 0){
            return true;
        }
        if(index == 0){
            return arr[0] == target;
        }
        boolean notTake = findUsingRecursion(arr, target, index - 1);
        boolean take = false;
        if(arr[index] <= target){
            take = findUsingRecursion(arr, target-arr[index], index-1);
        }
        return take || notTake;
    }

    public static int findUsingMemoization(int[] arr, int target, int index, int[] dp){
        if(target == 0){
            return 1;
        }
        if(index == 0){
            return arr[0] == target ? 1 : 0;
        }
        if(dp[index] != -1){
            return dp[index];
        }
        boolean notTake = findUsingRecursion(arr, target, index - 1);
        boolean take = false;
        if(arr[index] <= target){
            take = findUsingRecursion(arr, target-arr[index], index-1);
        }
        if(take || notTake){
            dp[index] = 1;
        } else{
            dp[index] = 0;
        }
        return dp[index];
    }

    public static boolean findUsingTabulation(int[] arr, int target){
        boolean[][] dp = new boolean[arr.length][target + 1];
        for(int i=0; i< dp.length; i++){
            dp[i][0] = true;
        }
        if(arr[0] <= target){
            dp[0][arr[0]] = true;
        }
        for(int i=1; i< dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                boolean notTake = dp[i-1][j];
                boolean take = false;
                if(arr[i] <= j){
                    take = dp[i-1][j-arr[i]];
                }
                dp[i][j] = take || notTake;
            }
        }
//        System.out.println("Tabulation dp table");
//        for(int i=0; i<dp.length; i++){
//            for(int j=0; j<dp[0].length; j++){
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
        return dp[dp.length-1][dp[0].length-1];
    }

    public static boolean findUsingSpaceOptimization(int[] arr, int target){
        boolean[] prev = new boolean[target + 1];
        prev[0] = true;

        if(arr[0] <= target){
            prev[arr[0]] = true;
        }
        boolean[] curr = new boolean[target + 1];
        curr[0] = true;
        for(int i=1; i< arr.length; i++){

            for(int j=1; j<=target; j++){
                boolean notTake = prev[j];
                boolean take = false;
                if(arr[i] <= j){
                    take = prev[j-arr[i]];
                }
                curr[j] = take || notTake;
            }
            for(int k=0; k< curr.length; k++){
                prev[k] = curr[k];
            };
            Arrays.fill(curr, false);
            curr[0] = true;
        }

        return prev[prev.length-1];
    }
}

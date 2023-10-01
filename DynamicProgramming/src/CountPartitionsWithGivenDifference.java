import java.util.Arrays;

public class CountPartitionsWithGivenDifference {
    public static void main(String[] args) {
        int[] arr = {5,1,9,11};
        int diff = 4;
        // the sum of the subsets are s1 and s2
        // total sum = t
        // s1 + s2 = t
        // s1 - s2 = diff
        // therefore s1 = (t + diff) / 2;
        int totalSum = Arrays.stream(arr).reduce(0, (a, b) -> a + b);

        if((totalSum + diff)%2 == 1){ // it means dividing by 2 will result into a decimal number
            System.out.println("No Partitions possible");
        }

        int requiredSubsetSum = (totalSum + diff) / 2;

        int ans = findUsingRecursion(arr, arr.length-1, requiredSubsetSum);
        System.out.println("Answer using recursion " + ans);

        int[][] dp = new int[arr.length][requiredSubsetSum+1];
        Arrays.stream(dp).forEach(item -> Arrays.fill(item, -1));
        int ans1 = findUsingMemoization(arr, arr.length-1, requiredSubsetSum, dp);
        System.out.println("Answer using memoization "+ ans1);

        int ans2 = findUsingTabulation(arr, requiredSubsetSum);
        System.out.println("Answer using tabulation "+ ans2);

        int ans3 = findUsingSpaceOptimization(arr, requiredSubsetSum);
        System.out.println("Answer using space optimzation "+ ans3);

    }

    public static int findUsingRecursion(int[] arr, int index, int targetSum){
        if(index == -1){
            if(targetSum == 0){
                return 1;
            } else{
                return 0;
            }
        }
        int notTake = findUsingRecursion(arr, index-1, targetSum);
        int take = 0;
        if(arr[index] <= targetSum){
            take = findUsingRecursion(arr, index-1, targetSum-arr[index]);
        }
        return take+notTake;
    }

    public static int findUsingMemoization(int[] arr, int index, int targetSum, int[][] dp){
        if(index == -1){
            if(targetSum == 0){
                return 1;
            } else{
                return 0;
            }
        }
        if(dp[index][targetSum] != -1){
            return dp[index][targetSum];
        }
        int notTake = findUsingRecursion(arr, index-1, targetSum);
        int take = 0;
        if(arr[index] <= targetSum){
            take = findUsingRecursion(arr, index-1, targetSum-arr[index]);
        }
        dp[index][targetSum] = take + notTake;
        return dp[index][targetSum];
    }

    public static int findUsingTabulation(int[] arr, int targetSum){
        int[][] dp = new int[arr.length][targetSum+1];
        Arrays.stream(dp).forEach(item -> Arrays.fill(item, 0));
        Arrays.stream(dp).forEach(item -> item[0] = 1);
        for(int i=1; i<=targetSum; i++){
            if(i == arr[0]){
                dp[0][i] = 1;
            }
        }
        for(int i=1; i< arr.length; i++){
            for(int j=1; j<=targetSum; j++){
                int notTake = dp[i-1][j];
                int take = 0;
                if(arr[i] <= j){
                    take = dp[i-1][j-arr[i]];
                }
                dp[i][j] = take + notTake;
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    public static int findUsingSpaceOptimization(int[] arr, int targetSum){
        int[] prev = new int[targetSum+1];
        Arrays.fill(prev, 0);
        prev[0] = 1;
        int[] curr = new int[targetSum + 1];
        curr[0] = 1;
        for(int i=1; i<=targetSum; i++){
            if(i == arr[0]){
                prev[i] = 1;
            }
        }
        for(int i=1; i< arr.length; i++){
            for(int j=1; j<=targetSum; j++){
                int notTake = prev[j];
                int take = 0;
                if(arr[i] <= j){
                    take = prev[j-arr[i]];
                }
                curr[j] = take + notTake;
            }
            for(int j=0; j<= targetSum; j++){
                prev[j] = curr[j];
                curr[j] = 0;
            }
            curr[0] = 1;
        }
        return prev[prev.length-1];
    }
}

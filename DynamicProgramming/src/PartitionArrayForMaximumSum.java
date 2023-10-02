import java.util.Arrays;

/**
 * Given an array of integers and a number k, partition the array in such a way the each partition cannot have more than k elements
 * and the sum of all the partitions is maximum. The sum of a parition is given by the length of the partition multiplied by the
 * greatest element in that parition
 */
public class PartitionArrayForMaximumSum {
    public static void main(String[] args) {
        int[] arr = {1, 5, 8, 9, 5, 8, 12, 15, 4};
        int k = 3;
        int ans = findUsingRecursion(arr,k, 0, arr.length-1);
        System.out.println("Answer using recursion "+ ans);

        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        int ans1 = findUsingMemoization(arr, k, 0, arr.length-1, dp);
        System.out.println("Answer using memoization "+ ans1);

        int ans2 = findUsingTabulation(arr, k);
        System.out.println("Answer using tabulation "+ ans2);
    }

    public static int findUsingRecursion(int[] arr, int k, int startIndex, int endIndex){
        if(endIndex < startIndex){
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        for(int i=startIndex; i< Math.min(startIndex + k, endIndex + 1); i++){
            int partitionSumLeft = getTheSumOfAPartition(arr, startIndex, i);
            int partitionSumRight = findUsingRecursion(arr, k, i+1, endIndex);
            ans = Math.max(ans, partitionSumLeft + partitionSumRight);
        }
        return ans;
    }

    public static int findUsingMemoization(int[] arr, int k, int startIndex, int endIndex, int[] dp){
        if(endIndex < startIndex){
            return 0;
        }
        if(dp[startIndex] != -1){
            return dp[startIndex];
        }
        int ans = Integer.MIN_VALUE;
        for(int i=startIndex; i< Math.min(startIndex + k, endIndex + 1); i++){
            int partitionSumLeft = getTheSumOfAPartition(arr, startIndex, i);
            int partitionSumRight = findUsingMemoization(arr, k, i+1, endIndex, dp);
            ans = Math.max(ans, partitionSumLeft + partitionSumRight);
        }
        dp[startIndex] = ans;
        return dp[startIndex];
    }

    public static int findUsingTabulation(int[] arr, int k){
        int[] dp = new int[arr.length+1];
        for(int i=arr.length-1; i>=0; i--){
            int ans = Integer.MIN_VALUE;
            for(int j=i; j< Math.min(i + k, arr.length); j++){
                int partitionSumLeft = getTheSumOfAPartition(arr, i, j);
                int partitionSumRight = dp[j+1];
                ans = Math.max(ans, partitionSumLeft + partitionSumRight);
            }
            dp[i] = ans;
        }
        return dp[0];
    }

    public static int getTheSumOfAPartition(int[] arr, int i, int j){
        int max = Integer.MIN_VALUE;
        for(int k=i; k<=j; k++){
            max = Math.max(max, arr[k]);
        }
        return max * (j - i + 1);
    }
}

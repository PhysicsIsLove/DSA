import java.util.Arrays;

/**
 * Find the length of the longest increasing subsequence
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
//        int[] arr = {1,2,7, 5, 3, 20, 23, 21, 45, 32, 56, 23, 121 , 121 ,1, 43 ,23};
        int[] arr = {1,2,3,4,5,0,1,2,3,4,5};
        int[] lengths = new int[arr.length];
        Arrays.fill(lengths, 1);
        int maxLength = 0;
        for(int i=1; i< arr.length; i++){
            for(int j=0; j< i; j++){
                if(arr[i] > arr[j]){
                    if(j != 0) {
                        lengths[i] = Math.max(lengths[i], 1 + lengths[j]);
                    } else{
                        lengths[i] = 2;
                    }
                } else{
                    lengths[i] = 1;
                }
                maxLength = Math.max(maxLength, lengths[i]);
            }
        }
        int ans = lengths[lengths.length-1];
        System.out.println("Answer "+ maxLength);

        int ans1 = findUsingRecursion(arr, 0, Integer.MIN_VALUE, 0);
        System.out.println("Answer using recursion "+ ans1);

        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        int ans2 = findUsingMemoization(arr, 0, Integer.MIN_VALUE, 0, dp);
        System.out.println("Answer using memoization "+ ans2);

        int ans3 = findUsingRecursion2(arr, 0, -1);
        System.out.println("Answer using recursion2: " + ans3);

        int[][] dp2 = new int[arr.length][arr.length+1];
        Arrays.stream(dp2).forEach(item -> Arrays.fill(item, -1));
        int ans4 = findUsingMemoization2(arr, 0, -1, dp2);
        System.out.println("Answer using memoization2 "+ ans4 );

        int[] dp3 = new int[arr.length];
        Arrays.fill(dp3, -1);
        int ans5 = findUsingMemoization3(arr, 0, -1, dp3);
        System.out.println("Answer using memoization3: "+ ans5);
    }

    public static int findUsingRecursion(int[] arr, int index, int lastElement, int currentLength){
        if(index == arr.length){
            return currentLength;
        }
        int take = Integer.MIN_VALUE;
        int notTake = Integer.MIN_VALUE;
        if(arr[index] > lastElement){
            take = findUsingRecursion(arr, index+1, arr[index], currentLength+1);
        }
            notTake = findUsingRecursion(arr, index+1, lastElement, currentLength);

        return Math.max(take, notTake);

    }

    public static int findUsingMemoization(int[] arr, int index, int lastElement, int currentLength, int[] dp){
        if(index == arr.length){
            return currentLength;
        }
        int take = Integer.MIN_VALUE;
        int notTake = Integer.MIN_VALUE;
        if(dp[index] != -1){
            return dp[index];
        }
        if(arr[index] > lastElement){
            take = findUsingMemoization(arr, index+1, arr[index], currentLength+1, dp);
        }
        notTake = findUsingMemoization(arr, index+1, lastElement, currentLength, dp);

        dp[index] = Math.max(take, notTake);
        return dp[index];

    }

    public static int findUsingRecursion2(int[] arr, int index, int prevIndex){
        if(index == arr.length){
            return 0;
        }
        int take = Integer.MIN_VALUE;
        int notTake = Integer.MIN_VALUE;
        if(prevIndex == -1 || arr[index] > arr[prevIndex]){
            take = 1 + findUsingRecursion2(arr, index+1, index);
        }
        notTake = findUsingRecursion2(arr, index+1, prevIndex);

        return Math.max(take, notTake);

    }

    public static int findUsingMemoization2(int[] arr, int index, int prevIndex, int[][] dp){
        if(index == arr.length){
            return 0;
        }
        if(dp[index][prevIndex+1] != -1){
            return dp[index][prevIndex+1];
        }
        int take = Integer.MIN_VALUE;
        int notTake = Integer.MIN_VALUE;
        if(prevIndex == -1 || arr[index] > arr[prevIndex]){
            take = 1 + findUsingMemoization2(arr, index+1, index, dp);
        }
        notTake = findUsingMemoization2(arr, index+1, prevIndex, dp);
        dp[index][prevIndex+1] = Math.max(take, notTake);
        return dp[index][prevIndex+1];

    }

    public static int findUsingMemoization3(int[] arr, int index, int prevIndex, int[] dp){
        if(index == arr.length){
            return 0;
        }
        if(dp[index] != -1){
            return dp[index];
        }
        int take = Integer.MIN_VALUE;
        int notTake = Integer.MIN_VALUE;
        if(prevIndex == -1 || arr[index] > arr[prevIndex]){
            take = 1 + findUsingMemoization3(arr, index+1, index, dp);
        }
        notTake = findUsingMemoization3(arr, index+1, prevIndex, dp);
        dp[index] = Math.max(take, notTake);
        return dp[index];

    }

    // TODO
    public static int findUsingTabulation(int[] arr){
        int[][] dp = new int[arr.length][arr.length+1];

        for(int i=arr.length-1; i>=0; i--){
            for(int j=i; j>0; j--){
                if(arr[i] > arr[j]){
//                    dp[i][j] = Math.max(dp[i][j], 1+)
                }
            }
        }
        return 1;
    }


}

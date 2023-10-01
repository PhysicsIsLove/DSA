public class MaxSumOfNonAdjacentElements {

    public static void main(String[] args) {
        int[] arr = {2, 1, 4 ,9, 3, 6, 9, 10, 11};
        int ans = findMaxSum(arr, 0, 0, Integer.MIN_VALUE);
        System.out.println("max sum of non-adjacent elements, starting from index = 0 : "+ ans);

        int ans2 = findMaxSum2(arr, arr.length-1, 0, Integer.MIN_VALUE);
        System.out.println("max sum of non-adjacent elements, starting from index n-1 : "+ ans2);

        int ans3 = findMaxSum3(arr, 0);
        System.out.println("max sum of non-adjacent elements, using another nethod: "+ ans3);
    }

    public static int findMaxSum(int[] arr, int index, int currentSum, int maxSum){
        if(index >= arr.length){
            return Math.max(currentSum, maxSum);
        }
        // sum if you take the number at the index
        int sum1 = findMaxSum(arr, index+2, currentSum + arr[index], maxSum);
        // sum if you don't take the number at the index
        int sum2 = findMaxSum(arr, index+1, currentSum, maxSum);

        return Math.max(sum1, sum2);
    }

    public static int findMaxSum2(int[] arr, int index, int currentSum, int maxSum){
        if(index < 0 ){
            return Math.max(currentSum, maxSum);
        }
        // sum if you take the number at the index
        int sum1 = findMaxSum2(arr, index-2, currentSum + arr[index], maxSum);
        // sum if you don't take the number at the index
        int sum2 = findMaxSum2(arr, index-1, currentSum, maxSum);

        return Math.max(sum1, sum2);
    }

    public static int findMaxSum3(int[] arr, int index){
        if(index >= arr.length){
            return 0;
        }
        int sum1 = arr[index] + findMaxSum3(arr, index + 2);
        int sum2 = findMaxSum3(arr, index + 1);
        return Math.max(sum1, sum2);
    }

}

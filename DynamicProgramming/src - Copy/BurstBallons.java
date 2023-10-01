import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BurstBallons {
    public static void main(String[] args) {
        int[] arr = {3,1, 5};

        int ans = findUsingRecursion(arr);

        System.out.println("Answer using Recursion "+ ans);
        Map<String, Integer> map = new HashMap<>();

        int ans1 = findUsingMemoization(arr, map);

        System.out.println("Answer using HashMap memoization "+ ans1);

        int[] newArr = new int[arr.length + 2];
        newArr[0] = 1;
        newArr[newArr.length-1] = 1;
        for(int i=0; i< arr.length; i++){
            newArr[i+1] = arr[i];
        }
        int ans2 = findUsingRecursion2(newArr, 1, newArr.length-2);
        System.out.println("Answer using bottom up recursion "+ ans2);

        int[][] dp = new int[newArr.length][newArr.length];
        Arrays.stream(dp).forEach(item -> Arrays.fill(item, -1));
        int ans3 = findUsingMemoization2(newArr, 1, newArr.length -2, dp);
        System.out.println("Answer using Memoization 2 "+ ans3);

        int ans4 = findUsingTabulation(newArr);
        System.out.println("Answer using tabulation "+ ans4);
    }

    public static int findUsingRecursion(int[] arr){
        if(arr.length == 0){
            return 0;
        }

        int ans = Integer.MIN_VALUE;
        for(int i=0; i< arr.length; i++){

            int[] newArr = new int[arr.length-1];
            int counter = 0;
            for(int j=0; j< arr.length; j++){
                if(i != j){
                    newArr[counter] = arr[j];
                    counter += 1;
                }
            }
            int left = 1;
            int right = 1;
            if(i > 0){
                left = arr[i-1];
            }
            if(i<arr.length-1){
                right = arr[i+1];
            }

            int pointsFromSubArray = findUsingRecursion(newArr);
            int points = left * right * arr[i] + pointsFromSubArray;
            ans = Math.max(ans, points);
        }
        return ans;
    }

    public static int findUsingMemoization(int[] arr, Map<String, Integer> map){
        if(arr.length == 0){
            return 0;
        }
        String key = "";
        for(int item: arr){
            key = key + item + " ";
        }
        if(map.containsKey(key)){
            return map.get(key);
        }
        int ans = Integer.MIN_VALUE;
        for(int i=0; i< arr.length; i++){

            int[] newArr = new int[arr.length-1];
            int counter = 0;
            for(int j=0; j< arr.length; j++){
                if(i != j){
                    newArr[counter] = arr[j];
                    counter += 1;
                }
            }
            int left = 1;
            int right = 1;
            if(i > 0){
                left = arr[i-1];
            }
            if(i<arr.length-1){
                right = arr[i+1];
            }

            int pointsFromSubArray = findUsingMemoization(newArr, map);
            int points = left * right * arr[i] + pointsFromSubArray;
            ans = Math.max(ans, points);
        }
        map.put(key, ans);
        return map.get(key);
    }

    public static int findUsingRecursion2(int[] arr, int i, int j){
        if(i > j){
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        for(int ind=i; ind<=j; ind++){
            int points = arr[ind] * arr[i-1] * arr[j+1] + findUsingRecursion2(arr, i, ind-1) + findUsingRecursion2(arr, ind+1, j) ;
            ans = Math.max(ans, points);
        }
        return ans;
    }

    public static int findUsingMemoization2(int[] arr, int i, int j, int[][] dp){
        if(i > j){
            return 0;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        int ans = Integer.MIN_VALUE;
        for(int ind=i; ind<=j; ind++){
            int points = arr[ind] * arr[i-1] * arr[j+1] + findUsingMemoization2(arr, i, ind-1, dp) + findUsingMemoization2(arr, ind+1, j, dp) ;
            ans = Math.max(ans, points);
        }
        dp[i][j] = ans;
        return dp[i][j];
    }

    // TODO Not understood fully, the tabulation approach
    public static int findUsingTabulation(int[] arr){
        int[][] dp = new int[arr.length][arr.length];
        for(int i=arr.length-1; i >= 1; i--){
            for(int j=1; j<arr.length-1; j++){
                if(i > j) continue;
                int ans = Integer.MIN_VALUE;
                for(int ind=i; ind<=j; ind++){
                    int points = arr[ind] * arr[i-1] * arr[j+1] + dp[i][ind-1] + dp[ind+1][j] ;
                    ans = Math.max(ans, points);
                }
                dp[i][j] = ans;
            }
        }
        return dp[1][dp.length-2];
    }
}

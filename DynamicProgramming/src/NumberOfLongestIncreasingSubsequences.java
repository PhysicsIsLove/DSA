import java.util.Arrays;

public class NumberOfLongestIncreasingSubsequences {
    public static void main(String[] args) {
        int[] arr = {1, 5, 4, 3, 2, 6, 7, 10, 8, 9};
        int[] dp = new int[arr.length];
        int[] count = new int[arr.length];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
        int ans = 0;
        int maxLength = 0;
        for(int i=1; i< arr.length; i++){
            for(int j=0; j<i; j++){
                if(arr[i] > arr[j]){
                    if(dp[i] == 1 + dp[j]){
                        count[i] += 1;
                    } else if (dp[i] < 1 + dp[j]){
                        count[i] = count[j];
                    }
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            ans = Math.max(ans, count[i]);
        }
        System.out.println("Answer is "+ ans);
    }
}

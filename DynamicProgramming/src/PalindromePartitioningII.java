import java.util.Arrays;

public class PalindromePartitioningII {

    public static void main(String[] args) {
        String s = "bababcbadcedewaaawfgtgoipp";
//        String s = "aabb";
//        String s = "A";
        int ans = findUsingRecursion(s, 0) - 1;
        System.out.println("Answer using Recursion "+ ans);
        int[] dp = new int[s.length()+1];

        Arrays.fill(dp, -1);
        int ans1 = findUsingMemoization(s, 0, dp) - 1;
        System.out.println("answer using memoization "+ ans1);

        int ans2 = findUsingTabulation(s);
        System.out.println("Answer using tabulation "+ ans2);
    }

    public static int findUsingRecursion(String s, int i){

        if(i == s.length()){
            return 0;
        }
        int ans = 1000;
        for(int k=i; k<s.length(); k++){
            if(isPalindrome(s.substring(i, k+1))){
                int numOperations = 1 + findUsingRecursion(s, k+1);
                ans = Math.min(ans, numOperations);
            }
        }
        return ans;
    }

    public static int findUsingMemoization(String s, int i, int[] dp) {
        if(i == s.length()){
            return 0;
        }
        if(dp[i] != -1){
            return dp[i];
        }
        int ans = 1000;
        for(int k=i; k<s.length(); k++){
            if(isPalindrome(s.substring(i, k+1))){
                int numOperations = 1 + findUsingMemoization(s, k+1, dp);
                ans = Math.min(ans, numOperations);
            }
        }
        dp[i] = ans;
        return dp[i];
    }

    public static int findUsingTabulation(String s){
        int[] dp = new int[s.length()+1];
        dp[s.length()] = 0;
        for(int i=s.length()-1; i>=0; i--){
            int ans = 1000000;
            for(int k=i; k<s.length(); k++){
                if(isPalindrome(s.substring(i, k+1))){
                    int numOperations = 1 + dp[k+1];
                    ans = Math.min(ans, numOperations);
                }
            }
            dp[i] = ans;
        }
        return dp[0]-1;
    }

    public static boolean isPalindrome(String s1){
        return s1.equals(new StringBuilder(s1).reverse().toString());
    }
}

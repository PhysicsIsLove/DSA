import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestStringChain {
    public static void main(String[] args) {

        String[] arr = {"a", "b", "ab", "aa", "acb", "c", "aab", "acbd", "cd", "ced", "cded", "bcded"};
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        int[] indexes = new int[arr.length];
        int lastIndex = 0;
        int maxLength = 0;
        for(int i=1; i< arr.length; i++){
            indexes[i] = i;
            for(int j=0; j< i; j++){
                if(checkIfConditionValid(arr[j], arr[i]) && 1 + dp[j] > dp[i]){
                    dp[i] = 1 + dp[j];
                    indexes[i] = j;
                }
            }
            if(dp[i] > maxLength){
                maxLength = dp[i];
                lastIndex = i;
            }
        }
        List<String> ans = new ArrayList<>();
        ans.add(arr[lastIndex]);
        while(indexes[lastIndex] != lastIndex){
            lastIndex = indexes[lastIndex];
            ans.add(arr[lastIndex]);
        }
        Collections.reverse(ans);
        System.out.println("Answer is "+ ans);

    }

    public static boolean checkIfConditionValid(String s1, String s2){
        if(Math.abs(s1.length() - s2.length()) == 0){
            return false;
        }
        String str1 = s1;
        String str2 = s2;
        // check if a character was inserted
        for(int i=0; i< str2.length(); i++){
            String temp = str2.substring(0, i) + str2.substring(i+1, str2.length());
            if(temp.equals(str1)){
                return true;
            }
        }
        return false;
    }
}

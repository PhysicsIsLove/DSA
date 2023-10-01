public class CheckIfAStringIsPalindrome {
    public static void main(String[] args) {
        String s = "MADAM";
        boolean ans = isPalindrome(s);
        System.out.println(ans);
    }

    public static boolean isPalindrome(String s){
        return helper(0, s);
    }

    public static boolean helper(int index, String s){
        if(index >= s.length()/2){
            return true;
        } else{
            if(s.charAt(index) != s.charAt(s.length() - index - 1)){
                return false;
            } else{
                return helper(index + 1, s);
            }
        }
    }
}

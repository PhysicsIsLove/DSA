import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public static void main(String[] args) {
        String s = "aabbc";
        List<String> tempList = new ArrayList<>();
        List<List<String>> listOfAllPalindromePartitions = new ArrayList<>();
        findAllPalindromePartiotions(s, tempList, listOfAllPalindromePartitions);
        System.out.println("Printing the list");
        listOfAllPalindromePartitions.forEach(list ->
                System.out.println(list));
    }

    public static void findAllPalindromePartiotions(String s, List<String> tempList, List<List<String>> listOfAllPalindromePartitions){
        if(s.equals("")){
            listOfAllPalindromePartitions.add(new ArrayList<>(tempList));
            return;
        }
        for(int i=1; i<=s.length(); i++){
            String subString = s.substring(0,i);
            if(isPalindrome(subString)){
                tempList.add(subString);
                findAllPalindromePartiotions(s.substring(i, s.length()), tempList, listOfAllPalindromePartitions);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static boolean isPalindrome(String s){
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString().equals(s);
    }

}

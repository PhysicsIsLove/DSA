import java.util.ArrayList;
import java.util.List;

public class PrintAllSubsequences {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        printAllSubsequences(arr);
    }
    public static void printAllSubsequences(int[] arr){
        List<Integer> list = new ArrayList<>();
        helper(arr, 0, list );
    }

    public static void helper(int[] arr, int currentIndex, List<Integer> list){
        if(currentIndex == arr.length){
            for(Integer i : list){
                System.out.print(i + " ");

            }
            if(list.size() == 0){
                System.out.print("{}");
            }
            System.out.println();
            return;
        }
//        helper(arr, currentIndex + 1, list); // It can be put here also, in this case, it will first print the empty subset
        list.add(arr[currentIndex]);
        helper(arr, currentIndex+1, list);
        list.remove(list.size() - 1);
        helper(arr, currentIndex + 1, list);
    }
}

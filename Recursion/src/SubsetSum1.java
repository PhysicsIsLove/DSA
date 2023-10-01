import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generate a list of the sums of all the subsets of an array in a sorted manner.
 */
public class SubsetSum1 {
    public static void main(String[] args) {
        int[] arr = {1,3,2};
        List<Integer> list = new ArrayList<>();
        findSubsetSum(arr, 0, 0, list);
        Collections.sort(list);
        System.out.println("List of sums");
        System.out.println(list);
    }

    public static void findSubsetSum(int[] arr, int index, int currentSum, List<Integer> listOfSums){
        if(index == arr.length){
            listOfSums.add(currentSum);
            return;
        }
        findSubsetSum(arr, index+1, currentSum + arr[index], listOfSums);
        findSubsetSum(arr, index+1, currentSum, listOfSums);
    }
}

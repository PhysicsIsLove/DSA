import java.util.ArrayList;
import java.util.List;

/**
 * Using the swapping technique.
 */
public class Permutations2 {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        List<List<Integer>> list = new ArrayList<>();
        generatePermutations(arr, 0, list);
        System.out.println("Printing the permutations");
        for (List<Integer> integers : list) {
            System.out.println(integers);
        }
    }

    public static void generatePermutations(int[] arr, int index, List<List<Integer>> permutations){
        if(index == arr.length){
            List<Integer> tempList = new ArrayList<>();
            for(int k=0; k<arr.length; k++){
                tempList.add(arr[k]);
            }
            permutations.add(tempList);
            return;
        }
        for(int j=index; j< arr.length; j++){
            swap(arr, index, j);
            generatePermutations(arr, index+1, permutations);
            swap(arr, index, j);
        }

    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

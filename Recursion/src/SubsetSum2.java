import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetSum2 {
    public static void main(String[] args) {
        int[] arr = {1,2,2,2,3,3};
        Arrays.sort(arr);
        List<Integer> subset = new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();
        generateSubsets(arr, 0, subset, list);
        System.out.println("List of all non duplicate subsets");
        list.forEach(System.out::println);
        System.out.println("Length of the list of subsets "+ list.size());
    }

    public static void generateSubsets(int[] arr, int index, List<Integer> subset, List<List<Integer>> list){
        list.add(new ArrayList<>(subset));
        for(int i=index; i< arr.length; i++){
            if(i > index && arr[i] == arr[i-1]) continue;
            subset.add(arr[i]);
            generateSubsets(arr, i+1, subset, list);
            subset.remove(subset.size() - 1);
        }
    }
}

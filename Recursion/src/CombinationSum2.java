import java.util.*;

/**
 * Same like combination1, but the sequences should not be duplicate, and they should be sorted in a lexicographical manner
 */
public class CombinationSum2 {

    public static void main(String[] args) {
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> tempList = new ArrayList<>();
        int[] arr = {1,1,1,2,2};
        int targetSum = 4;
        Arrays.sort(arr);
        findCombinations(arr, 0, targetSum, tempList, set);
        System.out.println("Elements in the set from the first method");
        set.stream().forEach(item -> System.out.println(item));

        System.out.println("Elements in the set from the second method");
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> tempList2 = new ArrayList<>();
        findCombinations2(arr, 0, targetSum, tempList2, list);
        list.stream().forEach(item -> System.out.println());

    }

    /**
     * This is the brute force technique where the sequence is checked if it already exists in the main list of sequences.
     */
    public static void findCombinations(int[] arr, int index, int targetSum,  List<Integer> tempList, Set<List<Integer>> set){
        if(index == arr.length){
            if(targetSum == 0){
                if(!set.contains(tempList)){
                    List<Integer> temp = new ArrayList<>();
                    temp.addAll(tempList);
                    set.add(temp);
                } else{
                    System.out.println("The set already contains "+ tempList);
                }
            }
            return;
        }
        if(arr[index] <= targetSum){
            tempList.add(arr[index]);
            findCombinations(arr, index+1, targetSum-arr[index], tempList, set);
            tempList.remove(tempList.size() - 1);
        }
        findCombinations(arr, index+1, targetSum, tempList, set);
    }

    public static void findCombinations2(int[] arr, int index, int targetSum,  List<Integer> tempList, List<List<Integer>> list){
//        if(index == arr.length){
            if(targetSum == 0){
                System.out.println("Adding a sequence "+ tempList);
                list.add(new ArrayList<>(tempList));
                return;
            }
//        }
        for(int i=index; i<arr.length; i++){
            if(i> index && arr[i] == arr[i-1]) continue;
            if(arr[i] <= targetSum){
                tempList.add(arr[i]);
                findCombinations2(arr, i+1, targetSum-arr[i], tempList, list);
                tempList.remove(tempList.size()-1);
            }
        }
    }
}

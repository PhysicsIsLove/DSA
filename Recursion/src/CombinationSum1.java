import java.util.ArrayList;
import java.util.List;

public class CombinationSum1 {
    public static void main(String[] args) {
        int[] arr = {2,3,4,5};
        int targetSum = 7;
        List<Integer> tempList = new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();
        findCombinations(arr, targetSum, 0, tempList, list);
    }

    public static void findCombinations(int[] arr, int targetSum, int index, List<Integer> tempList, List<List<Integer>> list){
        if(index == arr.length){
            if(targetSum == 0){
                System.out.print("A combination found : ");
                tempList.forEach(item -> System.out.print(item + " "));
                System.out.println();
                list.add(tempList);
            }
            return;
        }
        if(arr[index] <= targetSum){
            tempList.add(arr[index]);
            findCombinations(arr, targetSum-arr[index], index, tempList, list);
            tempList.remove(tempList.size() - 1);

        }
        findCombinations(arr, targetSum, index+1, tempList, list);

    }
}

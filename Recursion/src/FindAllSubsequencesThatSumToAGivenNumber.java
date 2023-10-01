import java.util.ArrayList;
import java.util.List;

public class FindAllSubsequencesThatSumToAGivenNumber {
    public static void main(String[] args) {
        int[] arr = {1,2,1};
        int targetSum = 3;
        List<Integer> list = new ArrayList<>();
        System.out.println("Printing all the subsequencces");
        printSubsequences(arr, 0, 0, targetSum, list);
        List<List<Integer>> listOfSubsequences = new ArrayList<>();
        listOfSubsequences = findSubsequences(arr, 0, 0, targetSum, list, listOfSubsequences);
        System.out.println("Printing in the main method");
        listOfSubsequences.forEach(item-> item.forEach(System.out::println));
        System.out.println("Find any subsequence");
        findAnySubsequence(arr, 0, 0, targetSum, list);
        System.out.println("Finding the total number of subsequences");
        int n = findTotalSubsequences(arr, 0, 0, targetSum);
        System.out.println("Total subsequences : "+ n);
    }

    /**
     * The subsequence gets printed inside the function only and it is also not optimized
     */
    public static void printSubsequences(int[] arr, int index, int currentSum, int targetSum, List<Integer> list){
        if(index == arr.length){
            if( currentSum == targetSum ){
                System.out.println("A subsequence found:");
                list.forEach(System.out::println);
            }
            return;
        }
        list.add(arr[index]);
        printSubsequences(arr, index + 1, currentSum + arr[index], targetSum, list);
        list.remove(list.size() - 1);
        printSubsequences(arr, index + 1, currentSum, targetSum, list);
    }

    /**
     * This function returns a list of the subsequences. Note that when adding the tempList to the list, we are creating a new ArrayList and initializing it with the tempList.
     * This is done because we dealing with the reference of tempList and on removing any item from the tempList, the tempList that was added to the list will also get affected.
     */
    public static List<List<Integer>> findSubsequences(int[] arr, int index, int currentSum, int targetSum, List<Integer> tempList, List<List<Integer>> list ){
        if(index == arr.length){
            if( currentSum == targetSum ){
                System.out.println("A subsequence found:");
                tempList.forEach(System.out::println);
                list.add(new ArrayList<>(tempList));
            }
            return list;
        }
        tempList.add(arr[index]);
        findSubsequences(arr, index + 1, currentSum + arr[index], targetSum, tempList, list);
        tempList.remove(tempList.size() - 1);
        findSubsequences(arr, index + 1, currentSum, targetSum, tempList, list);
        return list;
    }

    /** Find any subsequencce that sums to a given number
     */
    public static boolean findAnySubsequence(int[] arr, int index, int currentSum, int targetSum, List<Integer> tempList){
        if(index == arr.length){
            if( currentSum == targetSum ){
                System.out.println("A subsequence found:");
                tempList.forEach(System.out::println);
                return true;
            }
            return false;
        }
        tempList.add(arr[index]);
        if(findAnySubsequence(arr, index + 1, currentSum + arr[index], targetSum, tempList)) {return true ;};
        tempList.remove(tempList.size() - 1);
        if(findAnySubsequence(arr, index + 1, currentSum, targetSum, tempList)){ return true ;};
        return false;
    }

    /**
     * Find the total number of subsequences that sum to a given number
     */
    public static int findTotalSubsequences(int[] arr, int index, int currentSum, int targetSum){
        if(index == arr.length){
            if( currentSum == targetSum ){
                System.out.println("A subsequence found:");
                return 1;
            }
            return 0;
        }
        int left = findTotalSubsequences(arr, index + 1, currentSum + arr[index], targetSum);
        int right = findTotalSubsequences(arr, index + 1, currentSum, targetSum);
        return left + right;
    }


}

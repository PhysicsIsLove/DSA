import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations1 {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3)); // using list instead of primitive array since it makes it easier to create new sublists.
        List<Integer> currentPermutation = new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();
        generatePermutations(arr, currentPermutation, list);
        System.out.println("Listing out the permutations for method 1");
        list.forEach(perm -> System.out.println(perm));

        // Second method
        int[] arr2 = {1,2,3};
        List<Integer> currentPermutation2 = new ArrayList<>();
        List<List<Integer>> list2 = new ArrayList<>();
        boolean[] isTaken = new boolean[arr2.length];
        generatePermutations2(arr2, currentPermutation2, list2, isTaken);
        System.out.println("Listing out the permutations for method 2");
        list2.forEach(System.out::println);
    }

    public static void generatePermutations(List<Integer> arr, List<Integer> currentPermutation, List<List<Integer>> permutations){
        if(arr.size() == 0){
            permutations.add(new ArrayList<>(currentPermutation));
            return;
        }
        for(int i=0; i< arr.size(); i++){
            currentPermutation.add(arr.get(i));
            List<Integer> newArr = new ArrayList<>();
            newArr.addAll(arr.subList(0,i));
            newArr.addAll(arr.subList(i+1, arr.size()));
            generatePermutations(newArr, currentPermutation, permutations);
            currentPermutation.remove(currentPermutation.size() - 1);
        }
    }

    public static void generatePermutations2(int[] arr, List<Integer> currentPermutation, List<List<Integer>> permutations, boolean[] isTaken){
        if(currentPermutation.size() == arr.length){
            permutations.add(new ArrayList<>(currentPermutation));
            return;
        }
        for(int i=0; i< arr.length; i++){
            if(isTaken[i] == false){
                currentPermutation.add(arr[i]);
                isTaken[i] = true;
                generatePermutations2(arr, currentPermutation, permutations, isTaken);
                currentPermutation.remove(currentPermutation.size() - 1);
                isTaken[i] = false;
            }
        }
    }
}

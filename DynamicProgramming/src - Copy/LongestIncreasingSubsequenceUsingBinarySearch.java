import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequenceUsingBinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,2,7, 5, 3, 8, 0, 1, 2, 3, 4};
//        int[] arr = {1, 2, 3, 4, 5,8, 10};
        List<Integer> tempList = new ArrayList<>();
//        for(int item: arr){
//            tempList.add(item);
//        }
        tempList.add(arr[0]);
        for(int i=1; i< arr.length; i++){
            if(arr[i] > tempList.get(tempList.size()-1)){
                tempList.add(arr[i]);
            }
            else{
                int index = Collections.binarySearch(tempList, arr[i]);
                if(index < 0){
                    tempList.set(Math.abs(index) - 1, arr[i]);
                }
            }
        }
        System.out.println("Length of the longest subsequence "+ tempList.size());
    }
}

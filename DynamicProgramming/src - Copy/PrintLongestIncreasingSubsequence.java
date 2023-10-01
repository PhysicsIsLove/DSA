import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrintLongestIncreasingSubsequence {

    public static void main(String[] args) {

        int[] arr = {1,2,7, 5, 3, 20, 23, 21, 45, 32, 56, 23, 121 , 121 ,1, 43 ,23};
//        int [] arr = {1, 2, 7, 5, 6};// 1, 2, 5, 6
        int[] lengths = new int[arr.length];
        int[] indexes = new int[arr.length];
        Arrays.fill(indexes, 0);
        Arrays.fill(lengths, 1);
        int indexWhereMaxLengthOccurred = 0;
        int maxLength = 0;
        for(int i=1; i<arr.length; i++){
            indexes[i] = i;
            for(int j=0; j<i; j++){
                if(arr[i] > arr[j] && lengths[i] < 1 + lengths[j]){
                    lengths[i] = 1 + lengths[j];
                    indexes[i] = j;
                }
            }
            if(lengths[i] > maxLength){
                maxLength = lengths[i];
                indexWhereMaxLengthOccurred = i;
            }
        }
        List<Integer> list = new ArrayList<>();
        list.add(arr[indexWhereMaxLengthOccurred]);
        while(indexes[indexWhereMaxLengthOccurred] != indexWhereMaxLengthOccurred){
            indexWhereMaxLengthOccurred = indexes[indexWhereMaxLengthOccurred];
            list.add(arr[indexWhereMaxLengthOccurred]);

        }
        Collections.reverse(list);
        System.out.println("The subsequence is: "+ list);
    }
}

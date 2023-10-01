import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {3,4,6,2,1,6,5,4,8,9,7,6,5,42,11};
        System.out.println("Before sort");
        Arrays.stream(arr).forEach(item -> System.out.print(item + " "));
        int[] mergerArr = mergeSort(arr, 0, arr.length);
        System.out.println("After sort");
        Arrays.stream(mergerArr).forEach(item -> System.out.print(item + " "));
    }

    public static int[] mergeSort(int[] arr, int startIndex, int endIndex){
        if(endIndex - startIndex <= 1){
            if(endIndex == startIndex){
                return new int[]{};
            } else {
                return new int[]{arr[startIndex]};
            }
        }
        int midIndex = (startIndex + endIndex ) / 2;

        int[] leftSortedArr = mergeSort(arr, 0, midIndex);
        int[] rightSortedArr = mergeSort(arr, midIndex, endIndex);
        int[] mergedArr = mergeArrays(leftSortedArr, rightSortedArr);
        return mergedArr;
    }

    public static int[] mergeArrays(int[] leftArr, int[] rightArr){
        int[] mergedArr = new int[leftArr.length + rightArr.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int i = 0;
        while(leftIndex < leftArr.length && rightIndex < rightArr.length){
            if(leftArr[leftIndex] < rightArr[rightIndex]){
                mergedArr[i] = leftArr[leftIndex];
                i+=1;
                leftIndex += 1;
            } else{
                mergedArr[i] = rightArr[leftIndex];
                i+=1;
                rightIndex += 1;
            }
        }
        if(leftIndex == leftArr.length){
            while(rightIndex < rightArr.length){
                mergedArr[i] = rightArr[rightIndex];
                i+=1;
                rightIndex += 1;
            }
        } else if (rightIndex == rightArr.length){
            while(rightIndex < rightArr.length){
                mergedArr[i] = leftArr[leftIndex];
                i+=1;
                leftIndex += 1;
            }
        }
        return mergedArr;
    }
}

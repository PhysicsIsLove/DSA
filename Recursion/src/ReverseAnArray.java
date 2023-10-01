import java.util.Arrays;

public class ReverseAnArray {
    public static void main(String[] args) {
        // We can reverse an array by swapping an element with its corresponding last element.
        int[] arr = {1,2,3,4,5};
        System.out.println("Before");
        Arrays.stream(arr).forEach(System.out::println);
        reverse(arr);
        System.out.println("After");
        Arrays.stream(arr).forEach(System.out::println);
    }

    public static void reverse(int[] arr){
        helper(0, arr);
    }

    public static void helper(int index, int[] arr){
        if(index >= arr.length/2){
            return;
        }
        swap(arr, index, arr.length - index - 1);
    }

    public static void swap(int[] arr, int index1, int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}

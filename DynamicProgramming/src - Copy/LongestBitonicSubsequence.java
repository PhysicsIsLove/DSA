import java.util.Arrays;

public class LongestBitonicSubsequence {
    public static void main(String[] args) {
//        int[] arr = {1,2,7, 5, 3, 20, 23, 21, 45, 32, 56, 23, 121 , 121 ,1, 43 ,23};
//        int[] arr = {1,2,3, 2, 1};
        int[] arr = {1, 11, 2, 10, 4, 5, 2, 1};


        int[] dpLeft = new int[arr.length];
        int[] dpRight = new int[arr.length];
        Arrays.fill(dpLeft, 1);
        Arrays.fill(dpRight, 1);
        int maxLeftLength = 0;
        int maxRightLength = 0;
        for(int i=1; i< arr.length; i++){
            int left_i = i;
            int right_i = arr.length - i - 1;
            for(int j=0; j< i; j++){
                int left_j = j;
                int right_j = arr.length - j - 1;
                if(arr[left_i] > arr[left_j]){
                        dpLeft[left_i] = Math.max(dpLeft[left_i], 1 + dpLeft[left_j]);
                }
                if(arr[right_i] > arr[right_j]){
                    dpRight[right_i] = Math.max(dpRight[right_i], 1 + dpRight[right_j]);
                    }
                maxLeftLength = Math.max(maxLeftLength, dpLeft[i]);
                maxRightLength = Math.max(maxRightLength, dpRight[i]);
            }
        }
        int max = 0;
        for(int i=0; i< arr.length; i++){
            max = Math.max(max, dpLeft[i] + dpRight[i] - 1);
        }
        System.out.println("Ans: "+ max);
    }
}

import java.util.Arrays;

public class LargestDivisibleSubset {
    public static void main(String[] args) {

        int[] arr = {1, 16, 7, 8, 4, 10, 15, 20, 80, 5, 160};
        Arrays.sort(arr);
        int[] lengths = new int[arr.length];
        Arrays.fill(lengths, 1);
        int maxLength = 0;
        for(int i=1; i< arr.length; i++){
            for(int j=0; j< i; j++){
                int num1 = arr[i];
                int num2 = arr[j];
                if(doesSatisfyDivisibilityCriteria(num1, num2)){
                    lengths[i] = Math.max(lengths[i], 1 + lengths[j]);
                }
                maxLength = Math.max(lengths[i], maxLength);
            }
        }
        System.out.println("MaxLength is "+ maxLength);
    }

    public static boolean doesSatisfyDivisibilityCriteria(int a, int b){
        if(a%b == 0 || b%a == 0){
            return true;
        } return false;
    }
}

public class KthPermutation {
    public static void main(String[] args) {
        int n = 4;

        int[] arr = new int[n];
        for(int i=0; i< n; i++){
            arr[i] = i + 1;
        }
        for(int k=1; k<getFactorial(n); k++){
            String kthPerm = findKthPermutation(arr, k-1, "");
            System.out.println("Perm " + k + " : " + kthPerm);
        }

    }

    public static String findKthPermutation(int[] arr, int k, String perm){
        if(arr.length == 0){
            return perm;
        }
        int indexOfFirstDigit = (k)/getFactorial(arr.length-1);
        int firstDigit = arr[indexOfFirstDigit];
        perm = perm + Integer.toString(firstDigit);
        int[] newArr = new int[arr.length - 1];
        int index = 0;
        for(int i=0; i< arr.length; i++){
            if(i != indexOfFirstDigit){
                newArr[index] = arr[i];
                index += 1;
            }
        }
        int newK = k % getFactorial(arr.length - 1);
        return findKthPermutation(newArr, newK, perm);
    }

    public static int getFactorial(int n){
        if(n == 0) return 1;
        int prod = 1;
        for(int i=1; i<=n; i++){
            prod = prod * i;
        }
        return prod;
    }
}

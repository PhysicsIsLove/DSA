public class FindFactorialOfN {
    public static void main(String[] args) {
        int n = 10;
        int ans = findFactorial(n);
        System.out.println(ans);
        findFactorial2(10, 1);
    }
    /**
     * This is using the functional way, where the function returns something to the main calling function
     * @param n
     * @return
     */
    public static int findFactorial(int n){
        if(n == 0){
            return 1;
        } else{
            return n * findFactorial(n-1);
        }
    }

    /**
     * This is a parameterized version of the recursive calls
     * @param n
     * @param currentProduct
     */
    public static void findFactorial2(int n, int currentProduct){
        if(n == 0){
            System.out.println(currentProduct);
        } else{
            findFactorial2(n-1, currentProduct * n);
        }
    }
}

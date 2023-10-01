public class SumOfTheFirstNNumbers {
    public static void main(String[] args) {
        int n = 10;
        int ans = findSum(n);
        System.out.println(ans);
        findSum2(10,0);
    }

    /**
     * This is using the functional way, where the function returns something to the main calling function
     * @param n
     * @return
     */
    public static int findSum(int n){
        if(n == 0){
            return 0;
        } else{
            return n + findSum(n-1);
        }
    }

    /**
     * This is a parameterized version of the recursive calls
     * @param n
     * @param currentSum
     */
    public static void findSum2(int n, int currentSum){
        if(n == 0){
            System.out.println(currentSum);
        } else{
            findSum2(n-1, currentSum + n);
        }
    }
}

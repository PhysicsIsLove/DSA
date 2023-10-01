public class PrintNumsFrom1toN {

    public static void main(String[] args) {
        int n = 10;
        printNums(n);
        printNums2(n);
        printNums3(1,n);
    }

    /**
     * A Backtrack approach
     * @param n
     */
    public static void printNums(int n){
        if(n == 1){
            System.out.println(n);
        } else{
            printNums(n-1);
            System.out.println(n);
        }
    }

    /**
     * A Backtrack approach
     * @param n
     */
    public static void printNums2(int n) {
        if(n == 0){
            return;
        }
        printNums(n-1);
        System.out.println(n);
    }

    /**
     * This is more like recursion
     * @param i
     * @param n
     */
    public static void printNums3(int i, int n){
        if( i> n){
            return;
        } else{
            System.out.println(i);
            printNums3(i+1, n);
        }
    }

}

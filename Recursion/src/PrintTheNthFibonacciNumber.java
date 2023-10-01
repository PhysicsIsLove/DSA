public class PrintTheNthFibonacciNumber {
    public static void main(String[] args) {
        int nthFibonacci = getNthFibonacci(20);
        System.out.println(nthFibonacci);
    }

    public static int getNthFibonacci(int n){
        if(n <= 1){
            return n;
        } else{
            return getNthFibonacci(n-1) + getNthFibonacci(n-2);
        }
    }
}

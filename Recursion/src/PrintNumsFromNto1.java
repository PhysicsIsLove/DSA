public class PrintNumsFromNto1 {
    public static void main(String[] args) {
        int n= 10;
        printNums(n);
    }

    public static void printNums(int n){
        if(n == 0){
            return ;
        } else{
            System.out.println(n);
            printNums(n-1);
//            return;
        }
    }

    public static void printNums2(int n){
        
    }
}

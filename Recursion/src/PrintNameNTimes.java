public class PrintNameNTimes {
    public static void main(String[] args) {
        String name = "Abhishek";
        int n = 10;
        printName(n, name);
    }

    public static void printName(int n, String name){
        if(n == 0){
            return;
        }
        System.out.println(name);
        printName(n-1, name);
    }
}

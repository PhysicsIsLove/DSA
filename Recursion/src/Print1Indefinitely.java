public class Print1Indefinitely {
    public static void main(String[] args) {
//        print();
        while(true){
            System.out.println(1);
        }
    }

    public static void print(){
        System.out.println(1);
        print();
    }
}

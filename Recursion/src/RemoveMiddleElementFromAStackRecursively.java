import java.util.Stack;

public class RemoveMiddleElementFromAStackRecursively {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(1);stack.add(2);stack.add(3);stack.add(4);stack.add(5);stack.add(6);stack.add(7);stack.add(8);stack.add(9);
        removeMidElement(stack);
        stack.forEach(System.out::println);
    }

    public static void removeMidElement(Stack<Integer> stack){
        if(stack.size() == 0){
            return;
        }

        int midPosition = (stack.size() / 2) +1;
        helper(stack, midPosition);
    }

    public static void helper(Stack<Integer> stack, int midPosition){
        if(stack.size() == midPosition){
            stack.pop();
            return;
        }
        int temp = stack.pop();
        helper(stack, midPosition);
        stack.push(temp);
    }

}

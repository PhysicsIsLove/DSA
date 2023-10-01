import java.util.Stack;

public class ReverseAStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(1);stack.add(2);stack.add(3);stack.add(4);stack.add(5);stack.add(6);
        System.out.println("Before reverse");
        stack.forEach(System.out::println);
        reverseStack(stack);
        System.out.println("After reverse");
        stack.forEach(System.out::println);
    }

    public static void reverseStack(Stack<Integer> stack){
        if(stack.size() == 1 ){
            return;
        } else{
            int topElement = stack.pop();
            reverseStack(stack);
            insertToStack(stack, topElement);
        }
    }

    public static void insertToStack(Stack<Integer> stack, int elementToInsert){
        Stack<Integer> tempStack = new Stack<>();
        while(!stack.isEmpty() && stack.peek() < elementToInsert){
            tempStack.push(stack.pop());
        }
        stack.push(elementToInsert);
        while(!tempStack.isEmpty()){
            stack.push(tempStack.pop());
        }
    }
}

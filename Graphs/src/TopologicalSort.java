import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Only applicable for a Directed Acyclcic graph
 */
public class TopologicalSort {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(List.of());
        adjList.add(List.of(0,2 ));
        adjList.add(List.of(3));
        adjList.add(List.of(4));
        adjList.add(List.of(0));
        adjList.add(List.of(4));
        boolean[] visitedArr = new boolean[adjList.size()];
        List<Integer> ans = new ArrayList<>();
        for(int i=0; i< adjList.size(); i++){
            if(!visitedArr[i]){
                getTopologicalOrdering(adjList, stack, i, visitedArr);
            }
        }

        while(!stack.isEmpty()){
            ans.add(stack.pop());
        }
        System.out.println("The topological ordering is "+ ans);
    }

    public static void getTopologicalOrdering(List<List<Integer>> adjList, Stack<Integer> stack, int node, boolean[] visitedArr){
        visitedArr[node] = true;

        for(int adjNode: adjList.get(node)){
            if(!visitedArr[adjNode]){
                getTopologicalOrdering(adjList, stack, adjNode, visitedArr);
            }
        }
        stack.add(node);
    }
}

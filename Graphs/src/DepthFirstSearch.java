import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepthFirstSearch {

    public static void main(String[] args) {
        int numNodes = 5;
        // 1-based indexing of the nodes
        int[][] input = {
                {1,2},
                {1,3},
                {3,4},
                {2,4},
                {3,5},
                {5,4}
        };
        Boolean[] visitedArray = new Boolean[numNodes + 1];
        Arrays.fill(visitedArray, false);
        List<List<Integer>> adjList = GraphRepresentation.makeAdjList(input, numNodes);
        List<Integer> dfsResult = new ArrayList<>();
        dfs(adjList, 1, visitedArray, dfsResult);
        System.out.println(dfsResult);
    }

    public static List<Integer> dfs(List<List<Integer>> adjList, int node, Boolean[] visitedArray, List<Integer> result){
        result.add(node);
        visitedArray[node] = true;
        for(Integer n: adjList.get(node)){
            if(!visitedArray[n]){
                dfs(adjList, n, visitedArray, result);
            }
        }
        return result;
    }
}

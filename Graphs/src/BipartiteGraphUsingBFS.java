import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Find whether a graph can be colored using 2 colors, with no two adjacent nodes having the same color
 * Lets assume that the graph is un-directed
 */
public class BipartiteGraphUsingBFS {
    public static void main(String[] args) {
        int[][] graph = {
                {1, 5},
                {1, 3},
                {0, 3},
                {1, 4},
                {0, 2},
                {2, 5}
        };

        List<List<Integer>> adjList = GraphRepresentation.makeAdjList(graph, 6);

        boolean ans = findIfGraphIsBipartite(adjList);

        System.out.println("Answer is "+ ans);

    }

    public static boolean findIfGraphIsBipartite(List<List<Integer>> adjList){
        Queue<Integer> queue = new LinkedList<>();
        int[] visitedArr = new int[adjList.size()];
        // the two colors will be -1 and 1. 0 meaning that it has not yet been colored.
        queue.add(0);
        visitedArr[0] = 1; // colored the first node with 1
        while(!queue.isEmpty()){
            int node = queue.poll();
            int color = visitedArr[node];
            for(int adjNode : adjList.get(node)){
                if(visitedArr[adjNode] == 0){
                    queue.add(adjNode);
                    visitedArr[adjNode] = color == 1 ? -1 : 1;
                } else{
                    if(color == visitedArr[adjNode]){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

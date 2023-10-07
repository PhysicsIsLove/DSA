import java.util.ArrayList;
import java.util.List;

// it is a non directed graph
public class BipartiteGraphUsinggDFS {
    public static void main(String[] args) {
        int[][] graph = {
                {1, 5},
                {1, 3},
                {0, 3},
                {1, 4},
                {0, 2},
                {2, 5}
        };
        boolean ans = true;
        List<List<Integer>> adjList = GraphRepresentation.makeAdjList(graph, 6);
        int[] visitedArr = new int[adjList.size()];
        for(int i=0; i< adjList.size(); i++){
            if(visitedArr[i] == 0){
                if(!findIfGraphIsBipartite(adjList, visitedArr, i, -1)){
                    ans = false;
                    break;
                }
            }
        }
        System.out.println("Answer is "+ ans);
    }

    public static boolean findIfGraphIsBipartite(List<List<Integer>> adjList, int[] visitedArr, int node, int color){
        if(visitedArr[node] == color){
            return false;
        }
        visitedArr[node] = color;
        for(int adjNode: adjList.get(node)){
            if(visitedArr[adjNode] == 0){
                if(!findIfGraphIsBipartite(adjList, visitedArr, adjNode, color == 1 ? -1 : 1)){
                    return false;
                }
            } else if (color == visitedArr[adjNode]){
                return false;
            }
        }
        return true;
    }
}

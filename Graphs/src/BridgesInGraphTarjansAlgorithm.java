import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BridgesInGraphTarjansAlgorithm {
    public static int timer = 1;
    public static void main(String[] args) {
        int[][] graph = {
                {0, 1},
                {1, 3},
                {1, 2},
                {2, 3},
                {2, 4}
        };
        int numNodes = 5;
        List<List<Integer>> adjList = GraphRepresentation.makeAdjList(graph, numNodes);
        List<List<Integer>> listOfBridges = new ArrayList<>();

        boolean[] visitedArr = new boolean[numNodes];
        Arrays.fill(visitedArr, false);
        int[] times = new int[numNodes];
        int[] lows = new int[numNodes];

        dfs(adjList, 0, -1,  visitedArr, times, lows, listOfBridges);

        System.out.println("list of bridges "+ listOfBridges);

    }

    public static void dfs(List<List<Integer>> adjList, int node, int parentNode,  boolean[] visited, int[] times, int[] lows, List<List<Integer>> listOfBridges){
        visited[node] = true;
        times[node] = timer;
        lows[node] = timer;

        for(int adjNode: adjList.get(node)){
            if(!visited[adjNode]){
                timer += 1;
                dfs(adjList, adjNode, node, visited, times, lows, listOfBridges);
            }
            if(adjNode != parentNode){
                lows[node] = Math.min(lows[node], lows[adjNode]);
                if(lows[adjNode] > times[node]){
                    listOfBridges.add(List.of(node, adjNode));
                }
            }
        }
    }
}

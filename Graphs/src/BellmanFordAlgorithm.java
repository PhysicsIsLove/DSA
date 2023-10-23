import java.util.Arrays;

/**
 * It finds the shortest distance from a node to every other node, even with negative edges.
 * The Dijkstra algorithm fails for the negative edges, it runs into a TimeLimitExceeded error scenario.
 * It helps us to detect negative cycles.
 * It is applicable to directed graph, and hence it also works for un directed graph, we just need to convert it to a directed graph
 *
 * Relax all the edges N-1 times sequentially
 * Note that here we are not creating any adjacency list and all
 */
public class BellmanFordAlgorithm {
    public static void main(String[] args) {
        int numNodes = 6;
        int[][] graph =  {
                {0,1,4},
                {0,2,4},
                {1,2,2},
                {2,3,3},
                {2,4,1},
                {2,5,6},
                {3,5,2},
                {4,5,3}
        }; //  the first two indices represent the nodes and the last node represent the weight between those nodes
        findUsingBellmanFordAlgorithm(graph, numNodes);
    }

    public static void findUsingBellmanFordAlgorithm(int[][] graph, int numNodes){
        int[] distances = new int[numNodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0;
        for(int i=0; i< numNodes-1; i++){
            for(int[] edge: graph){
                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];

                if(distances[u] != Integer.MAX_VALUE){
                    if(distances[u] + weight < distances[v]){
                        distances[v] = distances[u] + weight;
                    }
                }
            }
        }
        Arrays.stream(distances).forEach(item -> System.out.print(item + " "));
    }
}

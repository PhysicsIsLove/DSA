import java.util.*;

/**
 * Find the shortest path to all the nodes from the node 0.
 * Store the total distnace in terms of the weights in an array
 */
public class ShortestPathInDirectedAcyclicGraph {
    public static void main(String[] args) {

        int[][] graph = {
                {0, 1, 2},
                {0, 4, 1},
                {1, 2, 3},
                {4, 2, 2},
                {4, 5, 4},
                {2, 3, 6},
                {5, 3, 1}
        };

        List<List<List<Integer>>> adjList = convertToAdjList(graph, 6);
        int[] distances = findMinimumDistances(adjList);
        Arrays.stream(distances).forEach(System.out::println);

    }

    public static int[] findMinimumDistances(List<List<List<Integer>>> adjList){
        int[] distances = new int[adjList.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0;
        int[] inDegree = new int[adjList.size()];
        for(int i=0; i< adjList.size(); i++){
            for(List<Integer> node : adjList.get(i)){
                inDegree[node.get(0)] += 1;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(List<Integer> adjNodeInfo: adjList.get(node)){
                int adjNode = adjNodeInfo.get(0);
                int adjNodeWeight = adjNodeInfo.get(1);
                inDegree[adjNode] -= 1;
                if(inDegree[adjNode] == 0){
                    queue.add(adjNode);
                }
                distances[adjNode] = Math.min(distances[node] + adjNodeWeight, distances[adjNode]);
            }
        }
        return distances;
    }

    public static List<List<List<Integer>>> convertToAdjList(int[][] graph, int numNodes){
        List<List<List<Integer>>> adjList = new ArrayList<>();
        for(int i=0; i< numNodes; i++){
            adjList.add(new ArrayList<>());
        }
        for(int[] edge: graph){
            int node1 = edge[0];
            int node2 = edge[1];
            int weight= edge[2];
            adjList.get(node1).add(new ArrayList<>(List.of(node2, weight)));
        }

        return adjList;
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphRepresentation {
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

        makeAdjList(input, numNodes);
        makeAdjMatrix(input, numNodes);
    }

    public static void makeAdjMatrix(int[][] inputGraph, int n){

        int[][] matrix = new int[n+1][n+1];
        for(int i=0; i< inputGraph.length; i++){
            int u = inputGraph[i][0];
            int v = inputGraph[i][1];
            matrix[u][v] = 1;
            matrix[v][u] = 1;
        }
    }

    public static List<List<Integer>> makeAdjList(int[][] inputGraph, int n){
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i=0; i < inputGraph.length; i++){
            int u = inputGraph[i][0];
            int v = inputGraph[i][1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        return adjList;
    }

    public static <T> void printAMatrix(T[][] matrix){
        for(int i=0; i< matrix.length; i++){
            for(int j=0; j< matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printAMatrix(int[][] matrix){
        for(int i=0; i< matrix.length; i++){
            for(int j=0; j< matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static List<List<List<Integer>>> makeAdjacencyListForWeightedGraph(int[][] weightedGraph, int numNodes){
        List<List<List<Integer>>> adjList = new ArrayList<>();
        for(int i=0; i< numNodes; i++){
            adjList.add(new ArrayList<>());
        }
        for(int[] edge : weightedGraph){
            int node1 = edge[0];
            int node2 = edge[1];
            int weight = edge[2];

            adjList.get(node1).add(Arrays.asList(node2, weight));
            adjList.get(node2).add(Arrays.asList(node1, weight));
        }
        return adjList;
    }

    public static List<List<List<Integer>>> makeAdjacencyListForWeightedDirectedGraph(int[][] weightedGraph, int numNodes){
        List<List<List<Integer>>> adjList = new ArrayList<>();
        for(int i=0; i< numNodes; i++){
            adjList.add(new ArrayList<>());
        }
        for(int[] edge : weightedGraph){
            int node1 = edge[0];
            int node2 = edge[1];
            int weight = edge[2];

            adjList.get(node1).add(Arrays.asList(node2, weight));
        }
        return adjList;
    }
}

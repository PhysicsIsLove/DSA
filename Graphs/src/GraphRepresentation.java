import java.util.ArrayList;
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
}

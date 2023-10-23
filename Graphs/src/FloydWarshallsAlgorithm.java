import java.util.Arrays;
import java.util.List;

/**
 * It is a multi source to multi target shortest part alogorithm
 * Also helps us identify negative cycles
 * Go via every node that is there in the graph
 * Applicable for directed graph, an undirected graph can be converted to a directed one to apply the algorithm
 */
public class FloydWarshallsAlgorithm {
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
        };
        List<List<List<Integer>>> adjList = GraphRepresentation.makeAdjacencyListForWeightedDirectedGraph(graph, numNodes);
        int[][] costMatrix = new int[numNodes][numNodes];
        Arrays.stream(costMatrix).forEach(row -> Arrays.setAll(row, i -> Integer.MAX_VALUE));
        for(int node=0; node<numNodes; node++){
            for(List<Integer> adjNodeInfo: adjList.get(node)){
                int adjNode = adjNodeInfo.get(0);
                int wt = adjNodeInfo.get(1);
                costMatrix[node][node] = 0;
                costMatrix[node][adjNode] = wt;
            }
        }

        for(int node=0; node< numNodes; node++){
            int viaNode = node;

            for(int node1=0; node1 < costMatrix.length; node1++){
                for(int node2=0; node2 < costMatrix.length; node2++){
                    int dist1 = costMatrix[node1][viaNode];
                    int dist2 = costMatrix[viaNode][node2];
                    if(dist1 == Integer.MAX_VALUE || dist2 == Integer.MAX_VALUE){

                    } else{
                        costMatrix[node1][node2] = Math.min(costMatrix[node1][node2], dist1 + dist2);
                    }

                }
            }
        }
        GraphRepresentation.printAMatrix(costMatrix);
    }
}

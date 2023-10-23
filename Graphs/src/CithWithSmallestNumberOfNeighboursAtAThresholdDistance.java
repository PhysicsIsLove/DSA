import java.util.Arrays;
import java.util.List;

/**
 * Graph 43, Striver Graph DP playlist youtube
 */
public class CithWithSmallestNumberOfNeighboursAtAThresholdDistance {
    public static void main(String[] args) {

        int[][] graph = {
                {0, 1, 3},
                {0, 2, 1},
                {2, 3, 1},
                {1, 3, 4}
        };

        int numNodes = 4;
        int thersholdValue = 4;


        List<List<List<Integer>>> adjList = GraphRepresentation.makeAdjacencyListForWeightedGraph(graph, numNodes);
        int[][] costMatrix = new int[numNodes][numNodes];
        Arrays.stream(costMatrix).forEach(row ->  Arrays.setAll(row, i -> Integer.MAX_VALUE));
        for(int node = 0; node < numNodes; node ++){
            costMatrix[node][node] = 0;
            for(List<Integer> edge : adjList.get(node)){
                int adjNode = edge.get(0);
                int weight = edge.get(1);
                costMatrix[node][adjNode] = weight;
            }
        }

        for(int viaNode = 0; viaNode < numNodes ; viaNode++){
            for(int i = 0; i< costMatrix.length; i++){
                for(int j=0; j< costMatrix[0].length; j++){
                    int dist1 = costMatrix[i][viaNode];
                    int dist2 = costMatrix[viaNode][j];
                    if(dist1 != Integer.MAX_VALUE && dist2 != Integer.MAX_VALUE){
                        costMatrix[i][j] = Math.min(costMatrix[i][j], dist1 + dist2);
                    }
                }
            }
        }
        int currentMin = costMatrix.length;
        int ans = -1;
        for(int i=0; i< costMatrix.length; i++){
            int value = 0;
            for(int j=0; j< costMatrix[0].length; j++){
                if(costMatrix[i][j] <= thersholdValue){
                    value += 1;
                }
            }
            if(value <= currentMin){
                ans = i;
                currentMin = value;
            }
        }
        System.out.println("The answer is "+ ans);
    }
}

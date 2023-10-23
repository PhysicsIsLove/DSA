import java.util.*;

public class DijkstraAlgorithmUsingPriorityQueue {

    public static void main(String[] args) {
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

        List<List<List<Integer>>> adjList = GraphRepresentation.makeAdjacencyListForWeightedGraph(graph, 6);
        findShortestDistancesFromNode(adjList, 0);
    }



    public static void findShortestDistancesFromNode(List<List<List<Integer>>> adjList, int startNode){
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        }); // at index 0, the distance will be stored and at index 1 the node will be stored.

        int[] distances = new int[adjList.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0;
        pq.add(Arrays.asList(0, startNode));
        while(!pq.isEmpty()){
            List<Integer> nodeInfo = pq.poll();
            int node = nodeInfo.get(1);
            int itsOwnDistance = nodeInfo.get(0);
            for(List<Integer> adjNodeInfo : adjList.get(node)){
                int adjNode = adjNodeInfo.get(0);
                int adjNodeDist = adjNodeInfo.get(1);

                if(itsOwnDistance + adjNodeDist < distances[adjNode]){
                    distances[adjNode] = itsOwnDistance + adjNodeDist;
                    pq.add(Arrays.asList(itsOwnDistance + adjNodeDist, adjNode));
                }
            }
        }
        System.out.println("The distances are ");
        Arrays.stream(distances).forEach(item -> System.out.print(item + " "));
    }
}

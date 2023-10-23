import java.util.*;

public class PrintShortestPathUsingDijkstraAlgorithm {
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
        int startNode = 0;
        int targetNode = 5;

        List<List<List<Integer>>> adjList = GraphRepresentation.makeAdjacencyListForWeightedGraph(graph, 6);

        findShortestPathBetweenTwoNodes(startNode, targetNode, adjList);
    }

    public static void findShortestPathBetweenTwoNodes (int startNode, int endNode, List<List<List<Integer>>> adjList) {
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<Integer> shortestPath = new ArrayList<>();

        pq.add(new Integer[]{0, startNode}); // the first element is the weight and the second element is the node itself
        Integer[][] distances = new Integer[adjList.size()][2];
        Arrays.setAll(distances, i ->  new Integer[]{Integer.MAX_VALUE, -1});
        distances[startNode][0] = 0;

        while(!pq.isEmpty()){
            Integer[] nodeInfo = pq.poll();
            int distance = nodeInfo[0];
            int node = nodeInfo[1];

            for(List<Integer> edge : adjList.get(node)){
                int adjNode = edge.get(0);
                int weight = edge.get(1);

                if(weight + distance < distances[adjNode][0]){
                    distances[adjNode][0] = weight + distance;
                    distances[adjNode][1] = node; // updating the parent;
                    pq.add(new Integer[]{weight + distance, adjNode});
                }
            }
        }
        while(distances[endNode][1] != -1){
            shortestPath.add(0, endNode);
            endNode = distances[endNode][1];
        }
        shortestPath.add(0, startNode);
        System.out.println("The shortest path is "+ shortestPath);
    }
}

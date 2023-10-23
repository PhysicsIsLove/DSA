import java.util.*;

public class DijkstraAlgorithmUsingTreeSet {
    public static void main(String[] args) {
        // the reason of using Treeset instead of Priority queue is only a very little time complexity optimization.
        // the objective  is to remove an already existing node whose distance has been calculated to be higher.
        // the removal will anyways take O(log(n)) time.
        // I think the removal could also have been done in the priority queue

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

    /**
     * WILL NOT WORK
     * The treeset in java is implemented in such a way that the equality is based on the thing we are comparing.
     * Example if the comparator is based on comparing the first digit, then
     * listSet2.add(Arrays.asList(1,2));
     * listSet2.add(Arrays.asList(1,3)); this won't get inserted, since the first number 1 is same in both the lists
     * @param adjList
     * @param startNode
     */
    public static void findShortestDistancesFromNode(List<List<List<Integer>>> adjList, int startNode){
        TreeSet<List<Integer>> set = new TreeSet<>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        }); // at index 0, the distance will be stored and at index 1 the node will be stored.

        int[] distances = new int[adjList.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0;
        set.add(Arrays.asList(0, startNode));
        while(!set.isEmpty()){
            List<Integer> nodeInfo = set.first();
            set.remove(nodeInfo);
            int node = nodeInfo.get(1);
            int itsOwnDistance = nodeInfo.get(0);

            for(List<Integer> adjNodeInfo : adjList.get(node)){
                int adjNode = adjNodeInfo.get(0);
                int adjNodeDist = adjNodeInfo.get(1);

                if(itsOwnDistance + adjNodeDist < distances[adjNode]){
                    if(distances[adjNode] != Integer.MAX_VALUE){
                        set.remove(List.of(distances[adjNode], adjNode));
                    }
                    distances[adjNode] = itsOwnDistance + adjNodeDist;
                    set.add(Arrays.asList(itsOwnDistance + adjNodeDist, adjNode));
                    System.out.println("Set.size "+ set.size());
                }
            }
        }
        System.out.println("The distances are ");
        Arrays.stream(distances).forEach(item -> System.out.print(item + " "));
    }
}

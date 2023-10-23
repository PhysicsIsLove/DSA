import java.util.*;

public class PrimsAlgorithmMinimumSpanningTree {
    public static void main(String[] args) {

        int[][] graph = {
                {0,1,2},
                {0,2,1},
                {2,1,1},
                {2,4,2},
                {2,3,2},
                {3,4,1}
        };
        List<List<List<Integer>>> adjList = GraphRepresentation.makeAdjacencyListForWeightedGraph(graph, 5);
        findTheMinimumSpanningTree(adjList);

    }

    public static void findTheMinimumSpanningTree(List<List<List<Integer>>> adjList){
        int sum = 0;
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        });// Each element of the pq will be of the type, [weight, node, parentNode]
        boolean[] visitedArr = new boolean[adjList.size()];
        Arrays.fill(visitedArr, false);
        List<List<Integer>> mst = new ArrayList<>();
        int startNode = 0;
        pq.add(List.of(0, startNode, -1));
        while(!pq.isEmpty()){
            List<Integer> edgeInfo = pq.poll();
            int node = edgeInfo.get(1);
            int parentNode = edgeInfo.get(2);
            int weight = edgeInfo.get(0);
            if(visitedArr[node]){
                continue;
            }
            visitedArr[node] = true;
            if(parentNode != -1){
                sum += weight;
                mst.add(List.of(parentNode, node));
            }
            for(List<Integer> adjNodeInfo : adjList.get(node)){
                int adjNode = adjNodeInfo.get(0);
                int adjNodeWeight = adjNodeInfo.get(1);
                if(!visitedArr[adjNode]){
                    pq.add(List.of(adjNodeWeight, adjNode, node));
                }
            }
        }

        System.out.println("the mst is "+ mst);
        System.out.println("The minimum sum is "+ sum);
    }
}

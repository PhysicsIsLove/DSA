import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class KruskalsAlgorithm {
    public static void main(String[] args) {
        int numNodes = 7;
        int[][] graph = {
                {1, 1, 4},
                {8, 3, 6},
                {2, 1, 2},
                {4, 1, 5},
                {3, 2, 3},
                {3, 2, 4},
                {5, 3, 4},
                {7, 2, 6},
                {9, 4, 5}
        };


        Arrays.sort(graph, new EdgesComparator());
        findMinimumSpanningTree(graph, numNodes);
    }

    public static void findMinimumSpanningTree(int[][] graph, int numNodes){
        List<List<Integer>> mst = new ArrayList<>();
        int[] ranks = new int[numNodes];
        Arrays.fill(ranks, 0);
        int[] parents = new int[numNodes];
        for(int i=0; i< numNodes; i++){
            parents[i] = i;
        }
        int weightsSum = 0;
        for(int[] edge : graph){
            int node1 = edge[1];
            int node2 = edge[2];
            int weight = edge[0];
            if(getParent(node1, parents) == getParent(node2, parents)){
                // no need to do anything
            } else{
                union(node1, node2, ranks, parents);
                mst.add(List.of(weight, node1, node2));
                weightsSum += weight;
            }
        }
        System.out.println("the mst is "+ mst);
        System.out.println("the total weight of the mst is "+ weightsSum);
    }

    public static void union(int node1, int node2, int[] ranks, int[] parents){
        int parent1 = getParent(node1, parents);
        int parent2 = getParent(node2, parents);
        if(ranks[node1] == ranks[node2]){
            parents[parent1] = parent2;
            ranks[parent2] += 1;
        } else if (ranks[node1] > ranks[node2]){
            parents[parent2] = parent1;
        } else{
            parents[parent1] = parent2;
        }
    }

    public static int getParent(int node, int[] parents){
        if(node != parents[node]){
            parents[node] = getParent(parents[node], parents);
        }
        return parents[node];
    }

    public static class EdgesComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0];
        }
    }
}

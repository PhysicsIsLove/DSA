import java.util.Arrays;

public class DisjointSetByUnionRank {
    public static void main(String[] args) {
        int numNodes = 6;
        int[][] graph = {
                {0, 1},
                {1, 2},
                {4, 1},
                {3, 5}
        };

        int[] parents = new int[numNodes];
        for(int i=0; i< numNodes; i++){
            parents[i] = i;
        }
        int[] ranks = new int[numNodes];
        Arrays.fill(ranks, 0);
        for(int[] edge : graph){
            unionByRank(edge[0], edge[1], ranks, parents);
        }
        System.out.println("Parents ");
        Arrays.stream(parents).forEach(item -> System.out.print(" "+ item));

        System.out.println("\nRanks ");
        Arrays.stream(ranks).forEach(item -> System.out.print(" "+ item));
    }

    public static void unionByRank(int node1, int node2, int[] ranks, int[] parents){
        int parent1 = getParent(node1, parents);
        int parent2 = getParent(node2, parents);
        if(parent1 == parent2){
            return;
        }
        else if(ranks[parent1] == ranks[parent2]){
            parents[parent1] = parent2;
            ranks[parent2] += 1;
        } else if (ranks[parent1] > ranks[parent2]){
            parents[parent2] = parent1;
        } else {
            parents[parent1] = parent2;
        }
    }

    public static int getParent(int node, int[] parents){
        if(node != parents[node]){
            parents[node] = getParent(parents[node], parents);
        }
        return parents[node];
    }
}

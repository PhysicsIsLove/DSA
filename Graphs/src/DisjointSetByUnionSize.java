import java.util.Arrays;

public class DisjointSetByUnionSize {
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
        int[] sizes = new int[numNodes];
        Arrays.fill(sizes, 1);
        for(int[] edge : graph){
            unionBySize(edge[0], edge[1], sizes, parents);
        }
        System.out.println("Parents ");
        Arrays.stream(parents).forEach(item -> System.out.print(" "+ item));

        System.out.println("\nSizes ");
        Arrays.stream(sizes).forEach(item -> System.out.print(" "+ item));
    }

    public static void unionBySize(int node1, int node2, int[] sizes, int[] parents){
        int parent1 = getParent(node1, parents);
        int parent2 = getParent(node2, parents);
        if(parent1 == parent2){
            return;
        }
        else if(sizes[parent1] == sizes[parent2]){
            parents[parent1] = parent2;
            sizes[parent2] += sizes[parent1];
        } else if (sizes[parent1] > sizes[parent2]){
            parents[parent2] = parent1;
            sizes[parent1] += sizes[parent2];
        } else {
            parents[parent1] = parent2;
            sizes[parent2] += sizes[parent1];
        }
    }

    public static int getParent(int node, int[] parents){
        if(node != parents[node]){
            parents[node] = getParent(parents[node], parents);
        }
        return parents[node];
    }
}

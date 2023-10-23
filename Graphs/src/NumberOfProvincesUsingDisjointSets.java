import com.sun.security.jgss.GSSUtil;

/**
 * It can also be solved using DFS method
 */
public class NumberOfProvincesUsingDisjointSets {
    public static void main(String[] args) {
        int[][] graph = {
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 4},
                {5, 6}
        };
        int numNodes = 7;

        int[] parents = new int[numNodes];
        int[] ranks = new int[numNodes];
        for(int i=0; i<numNodes; i++){
            parents[i] = i;
        }

        for(int[] edge: graph){
            unionByRank(edge[0], edge[1], parents, ranks);
        }
        int numOfProvinces = 0;
        for(int i=0; i< numNodes; i++){
            if(i == parents[i] ){
                numOfProvinces += 1;
            }
        }
        System.out.println("Number of provinces "+ numOfProvinces);

    }

    public static void unionByRank(int node1, int node2, int[] parents, int[] ranks){
        int parent1 = getParent(node1, parents);
        int parent2 = getParent(node2, parents);

        if(parent1 == parent2){
            return;
        }

        int rank1 = ranks[parent1];
        int rank2 = ranks[parent2];

        if(rank1 == rank2){
            parents[parent1] = parent2;
            ranks[parent2] += 1;
        } else if (rank1 > rank2){
            parents[parent2] = parent1;
        } else {
            parents[parent1] = parent2;
        }
    }

    public static int getParent(int node, int[] parents){
        if(parents[node] != node){
            parents[node] = getParent(parents[node], parents);
        }
        return parents[node];
    }
}

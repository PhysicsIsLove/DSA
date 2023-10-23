import java.util.Arrays;

public class NumberOfOperationsToMakeAGraphConnected {

    public static int extraEdge = 0;
    public static void main(String[] args) {
        extraEdge = 0;
        int[][] graph = {
                {0, 1},
                {0, 2},
                {2, 3},
                {1, 3},
                {1, 2},
                {0, 3},
                {4, 5},
                {6, 7}
        };
        int numNodes = 8;

        int[] parents = new int[numNodes];
        for(int i=0; i< numNodes; i++){
            parents[i] = i;
        }
        int[] ranks = new int[numNodes];
        Arrays.fill(ranks, 0);
        for(int[] edge : graph){
            unionByRank(edge[0], edge[1], ranks, parents);
        }

        int numConnectedComponents = 0;
        for(int i=0; i< numNodes; i++)
        {
            if(parents[i] == i){
                numConnectedComponents += 1;
            }
        }
        System.out.println("Number of connnected Components "+ numConnectedComponents);
        System.out.println("Number of extra edges "+ extraEdge);
        if(extraEdge >= numConnectedComponents - 1){
            System.out.println("The number of edges required is "+ (numConnectedComponents - 1));
        } else{
            System.out.println("Not possible, there aren't enough edges to connected the disconnected components");
        }

    }

    public static void unionByRank(int node1, int node2, int[] ranks, int[] parents){
        int parent1 = getParent(node1, parents);
        int parent2 = getParent(node2, parents);

        if(parent1 == parent2){
            extraEdge += 1;
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

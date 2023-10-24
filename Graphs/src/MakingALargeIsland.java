import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MakingALargeIsland {
    public static void main(String[] args) {

        int[][] graph = {
                {1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
//                {1, 0, 1}

        };

        int rows = graph.length;
        int cols = graph[0].length;

        int[] parents = new int[rows * cols];
        for(int i=0; i< parents.length; i++){
            parents[i] = i;
        }
        int[] sizes = new int[rows * cols];
        Arrays.fill(sizes, 1);

        int[] delRow = {1, 0, -1, 0}; // bottom, right, top, left
        int[] delCol = {0, 1, 0, -1};

        int numCols = graph[0].length;
        int numRows = graph.length;

        for(int i=0; i< graph.length; i++){
            for(int j=0; j< graph[0].length; j++){
                if(graph[i][j] == 1){
                    for(int k = 0;  k< 4; k ++){
                        int newRow = i + delRow[k];
                        int newCol = j + delCol[k];
                        if(newRow >=0 && newRow < numRows && newCol >= 0 && newCol < numCols ){
                            if(graph[newRow][newCol] == 1){
                                int index = i * numCols + j;
                                int newIndex = newRow * numCols + newCol;

                                if(parents[newIndex] != parents[index]){
                                    unionBySize(index, newIndex, parents, sizes);
                                }
                            }
                        }
                    }
                }
            }
        }

        Set<Integer> uniqueParents ;;
        int maxSize = Integer.MIN_VALUE;
        for(int i=0; i < graph.length; i++){
            for(int j=0; j< graph[0].length; j++){
                if(graph[i][j] == 0){
                    uniqueParents = new HashSet<>();
                    for(int k=0; k< 4; k++){
                        int newRow = i + delRow[k];
                        int newCol = j + delCol[k];
                        if(newRow >=0 && newRow < numRows && newCol >= 0 && newCol < numCols ){
                            if(graph[newRow][newCol] == 1){
                                uniqueParents.add(getParent(newRow * numCols + newCol, parents));
                            }
                        }
                    }
                    int count = 0;
                   for(Integer parent: uniqueParents){
                       count += sizes[parent];
                   }
                   if(count > maxSize){
                       maxSize = count;
                   }
                }
            }
        }
        System.out.println("The max size possible is "+ (maxSize + 1));
    }

    public static void unionBySize(int index1, int index2, int[] parents, int[] sizes){
        int parent1 = getParent(index1, parents);
        int parent2 = getParent(index2, parents);

        int size1 = sizes[parent1];
        int size2 = sizes[parent2];

        if(size1 == size2){
            parents[parent1] = parent2;
            sizes[parent2] += 1;
        } else if (size1 > size2){
            parents[parent2] = parent1;
            sizes[parent1] += sizes[parent2];
        } else{
            parents[parent1] = parent2;
            sizes[parent2] += sizes[parent1];
        }
    }

    public static int getParent(int index, int[] parents) {
        if(parents[index] != index){
            parents[index] = getParent(parents[index], parents);
        }
        return parents[index];
    }
}

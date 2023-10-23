import java.util.*;

public class FindTheNumberOfDistinctIslands {
    public static void main(String[] args) {
        Set<List<List<Integer>>> set = new HashSet<>();


        int[][] graph = {
                {0,1,0,0,1},
                {0,1,1,1,0},
                {0,0,0,0,1},
                {0,1,0,0,0},
                {0,1,1,1,0}
        };

        int rows = graph.length;
        int cols = graph[0].length;
        boolean[][] visitedMatrix = new boolean[rows][cols];
        for(int i=0; i<rows; i++){
            for(int j=0; j< cols; j++){
                if(graph[i][j] == 1 && !visitedMatrix[i][j]){
                    List<List<Integer>> list = new ArrayList<>();
                    list.add(Arrays.asList(i, j));
                    int originX = i;
                    int originY = j;
                    dfs(graph, rows, cols, i, j, visitedMatrix, list);
                    list.forEach(coordinate -> {
                        coordinate.set(0, originX -coordinate.get(0));
                        coordinate.set(1, originY -coordinate.get(1));
                    });
                    set.add(list);
                }
            }
        }
        System.out.println(set.size());
    }

    public static void dfs(int[][] graph, int rows, int cols, int row, int col, boolean[][] visitedMatrix, List<List<Integer>> list){
        int[][] directions = {
                {1,0}, {-1,0}, {0,1}, {0, -1}
        };
        for(int[] direction: directions){
            int newRow = direction[0] + row;
            int newCol = direction[1] + col;
            if(isCellValid(rows, cols, newRow, newCol) && graph[newRow][newCol] == 1 && !visitedMatrix[newRow][newCol]){
                visitedMatrix[newRow][newCol] = true;
                list.add(Arrays.asList(newRow, newCol));
                dfs(graph, rows, cols, newRow, newCol, visitedMatrix, list);
            }
        }
    }

    public static boolean isCellValid(int rows, int cols, int row, int col){
        if(row >= 0 && row < rows && col >= 0 && col < cols ){
            return true;
        } return false;
    }
}

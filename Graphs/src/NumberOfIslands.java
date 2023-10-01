import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    public static void main(String[] args) {
        int[][] graph = {
                {0,1,1,1,0},
                {0,1,1,0,0},
                {1,0,0,1,0},
                {1,1,0,0,1}
        };
        int rows = graph.length;
        int cols = graph[0].length;

        int[][] visitedArr = new int[rows][cols];
        int count = 0;
        for(int i=0; i< rows; i++){
            for(int j=0; j< cols; j++){
                if(graph[i][j] == 1 && visitedArr[i][j] == 0){
                    count += 1;
                    bfs(graph, visitedArr, i, j);
                }
            }
        }
        System.out.println("Total number of connected components " + count);
    }

    public static void bfs(int[][] graph, int[][] visitedArr, int row, int col){
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        queue.add(new ArrayList<>(Arrays.asList(row, col)));
        visitedArr[row][col] = 1;
        while(!queue.isEmpty()){
            ArrayList<Integer> rowAndCol = queue.poll();
            int r = rowAndCol.get(0);
            int c = rowAndCol.get(1);
            for(int delRow = -1; delRow<= 1; delRow++){
                for(int delCol = -1; delCol <= 1; delCol++){
                    int newRow = r + delRow;
                    int newCol = c + delCol;
                    if(newRow >= 0 && newRow < graph.length && newCol >=0 && newCol < graph[0].length){
                        if(graph[newRow][newCol] == 1 && visitedArr[newRow][newCol] == 0){
                            queue.add(new ArrayList<>(Arrays.asList(newRow, newCol)));
                            visitedArr[newRow][newCol] = 1;
                        }
                    }
                }
            }
        }
    }
}

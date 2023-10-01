import java.util.LinkedList;
import java.util.Queue;

/**
 * There is a box of oranges. Some of them are rotten. Find the time it takes to fully rotten the box. If it is not possible, then return -1
 */
public class RottenOranges {

    public static void main(String[] args) {
        int[][] graph = {
                {0,1,2,1,0},
                {0,1,1,0,0},
                {1,0,0,0,1},
                {1,2,1,1,1}
        };
        int maxTime = Integer.MIN_VALUE;
        Queue<Integer[]> queue = new LinkedList<>();
        for(int i=0; i<graph.length; i++){
            for(int j=0; j<graph[0].length; j++){
                if(graph[i][j] == 2){
                    queue.add(new Integer[]{i, j, 0});
                }
            }
        }

        int ans = bfs(graph, queue, maxTime);
        for(int i=0; i< graph.length; i++){
            for(int j=0; j<graph[0].length; j++){
                if(graph[i][j] == 1){
                    System.out.println("not all ornages could become rotten ");
                    return;
                }
            }
        }
        System.out.println("Time taken for all the oranges to become rotten "+ ans);

    }

    public static int bfs(int[][] graph, Queue<Integer[]> queue, int maxTime){
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};
        while(!queue.isEmpty()){
            Integer[] temp = queue.poll();
            int row = temp[0];
            int col = temp[1];
            int time = temp[2];
            maxTime = Math.max(maxTime, time);
            for(int i=0; i<4; i++){
                int newRow = row + delRow[i];
                int newCol = col + delCol[i];
                if(newRow >=0 && newRow < graph.length && newCol >= 0 && newCol < graph[0].length){
                    if(graph[newRow][newCol] == 1){
                        graph[newRow][newCol] = 2;
                        queue.add(new Integer[]{newRow, newCol, time + 1});
                    }
                }
            }
        }
        return maxTime;
    }
}

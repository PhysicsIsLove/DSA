import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DistanceOfNearestCellHaving1 {
    public static void main(String[] args) {

        int[][] matrix = {
                {0, 0, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0}


        };
        int[][] ans = new int[matrix.length][matrix.length];
        boolean[][] visitedMatrix = new boolean[matrix.length][matrix.length];
        for(int i=0; i< matrix.length; i++){
            for(int j=0; j< matrix[0].length; j++){
                ans[i][j] = fillTheMatrixWithNearestDistancesUsingDFS(matrix, i, j, visitedMatrix);
            }
        }

        int[][] ans1 = new int[matrix.length][matrix[0].length];
        Arrays.stream(ans1).forEach(item -> Arrays.fill(item, Integer.MAX_VALUE));
        boolean[][] visitedMatrix1 = new boolean[matrix.length][matrix.length];
        for(int i=0; i< matrix.length; i++){
            for(int j=0; j< matrix[0].length; j++){
                if(matrix[i][j] == 1){
                    fillTheMatrixWithNearestDistancesUsingBFS(matrix, ans1, i, j, visitedMatrix1 );
                    for(int x = 0; x< matrix.length; x++){
                        for(int y=0; y< matrix[0].length; y++){
                            if(matrix[x][y] == 1 && visitedMatrix1[x][y] == true){

                            } else{
                                visitedMatrix1[x][y] = false;
                            }
                        }
                    }
                }
            }
        }

        boolean[][] visitedMatrix2 = new boolean[matrix.length][matrix[0].length];
        int[][] ans2 = new int[matrix.length][matrix[0].length];
        fillTheMatrixWithNearestDistancesUsingOptimizedBFS(matrix, ans2, visitedMatrix2);

        System.out.println("Answer using DFS");
        GraphRepresentation.printAMatrix(ans);

        System.out.println("Answer using BFS");
        GraphRepresentation.printAMatrix(ans1);

        System.out.println("Answer using optimized BFS");
        GraphRepresentation.printAMatrix(ans2);
    }

    public static int fillTheMatrixWithNearestDistancesUsingDFS(int[][] matrix, int row, int col, boolean[][] visitedMatrix){
        visitedMatrix[row][col] = true;
        if(matrix[row][col] == 1){
            visitedMatrix[row][col] = false;
            return 0;
        } else{
            int leftDist = Integer.MAX_VALUE - 1000000;
            int rightDist = Integer.MAX_VALUE - 1000000;
            int topDist = Integer.MAX_VALUE - 1000000;
            int btmDist = Integer.MAX_VALUE - 1000000;
            if(row > 0 && visitedMatrix[row-1][col] == false){

                topDist = 1 + fillTheMatrixWithNearestDistancesUsingDFS(matrix, row-1, col, visitedMatrix);
            }
            if( row < matrix.length-1 && visitedMatrix[row+1][col] == false ){
                btmDist = 1 + fillTheMatrixWithNearestDistancesUsingDFS(matrix, row+1, col, visitedMatrix);
            }
            if(col > 0 && visitedMatrix[row][col-1] == false){
                leftDist = 1 + fillTheMatrixWithNearestDistancesUsingDFS(matrix, row, col-1, visitedMatrix);
            }
            if( col < matrix[0].length-1 && visitedMatrix[row][col+1] == false){
                rightDist = 1 + fillTheMatrixWithNearestDistancesUsingDFS(matrix, row, col+1, visitedMatrix);
            }
            visitedMatrix[row][col] = false;
            return Math.min(Math.min(leftDist, rightDist), Math.min(topDist, btmDist));
        }


    }

    /**
     * Very sub - optimal solution, very important how to handle the visited Matrix
     * @param matrix
     * @param ans
     * @param row
     * @param col
     * @param visitedArr
     */
    public static void fillTheMatrixWithNearestDistancesUsingBFS(int[][] matrix, int[][] ans, int row, int col, boolean[][] visitedArr){

        ans[row][col] = 0;
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(List.of(row, col, 0));
        visitedArr[row][col] = true;
        while(!queue.isEmpty()){
            List<Integer> cellInfo = queue.poll();
            int i = cellInfo.get(0);
            int j = cellInfo.get(1);
            int dist = cellInfo.get(2);

            ans[i][j] = Math.min(dist, ans[i][j]);
            if(i > 0 && matrix[i-1][j] != 1 && !visitedArr[i - 1][j]){
                queue.add(List.of(i-1, j, dist + 1));
                visitedArr[i-1][j] = true;
            }
            if(i < matrix.length-1 && matrix[i+1][j] != 1 && !visitedArr[i + 1][j]){
                queue.add(List.of(i+1, j, dist + 1));
                visitedArr[i+1][j] = true;
            }
            if(j > 0 && matrix[i][j-1] != 1 && !visitedArr[i][j - 1]){
                queue.add(List.of(i, j-1, dist + 1));
                visitedArr[i][j-1] = true;
            }
            if(j < matrix[0].length-1 && matrix[i][j+1] != 1 && !visitedArr[i][j + 1]){
                queue.add(List.of(i, j+1, dist + 1));
                visitedArr[i][j+1] = true;
            }
        }
    }

    public static void fillTheMatrixWithNearestDistancesUsingOptimizedBFS(int[][] matrix, int[][] ans, boolean[][] visitedMatrix){
        Queue<List<Integer>> queue = new LinkedList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        for(int i=0; i< rows; i++){
            for(int j=0; j< cols; j++){
                if (matrix[i][j] == 1) {
                    queue.add(List.of(i, j, 0));
                    visitedMatrix[i][j] = true;
                }
            }
        }
        while(!queue.isEmpty()){
            List<Integer> cell = queue.poll();
            int row = cell.get(0);
            int col = cell.get(1);
            int dist = cell.get(2);
            ans[row][col] = dist;
            if(row-1 >=0 && !visitedMatrix[row-1][col]){
                queue.add(List.of(row-1, col, dist+1));
                visitedMatrix[row-1][col] = true;
            }
            if(row+1 < rows && !visitedMatrix[row+1][col]){
                queue.add(List.of(row+1, col, dist+1));
                visitedMatrix[row+1][col] = true;
            }
            if(col-1 >= 0 && !visitedMatrix[row][col-1]){
                queue.add(List.of(row, col-1, dist+1));
                visitedMatrix[row][col-1] = true;
            }
            if(col+1 < cols && !visitedMatrix[row][col+1]){
                queue.add(List.of(row, col+1, dist+1));
                visitedMatrix[row][col+1] = true;
            }
        }
    }
}

public class NumberOfEnclaves {
    public static void main(String[] args) {
        int[][] graph = {
                {0, 0, 0, 0},
                {0, 1, 0, 1},
                {0, 1, 1, 0},
                {0, 0, 0, 1}
        };
        //1  -> land
        // 0 -> water
        // ans -> total number of land initially - total numer of land finally
        int initialCount = 0;
        int finalCount = 0;
        for(int i=0; i< graph.length; i++){
            for(int j=0; j< graph[0].length; j++){
                initialCount += graph[i][j];
            }
        }

        boolean[][] visited = new boolean[graph.length][graph[0].length];
        for(int i=0; i< graph.length; i++){
            for( int j=0; j< graph[0].length; j++){
                if(graph[i][j] == 1 && !visited[i][j]){
                    if(isSurroundedByWater(graph, i, j, visited)){
                        System.out.println(" At "+ i + " " + j);
                        replaceWith0s(graph, i, j);
                    }
                }
            }
        }
        for(int i=0; i< graph.length; i++){
            for(int j=0; j< graph[0].length; j++){
                finalCount += graph[i][j];
            }
        }
        System.out.println("Total number of land enclaves "+ (initialCount - finalCount));
    }

    public static boolean isSurroundedByWater(int[][] graph, int row, int col, boolean[][] visited){

        if(row == 0 || row == graph.length-1 || col == 0 || col == graph[0].length-1){
            return false;
        }
        visited[row][col] = true;
        int[][] directions = {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
        for(int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if(isPositionValid(graph, newRow,newCol) && graph[newRow][newCol] == 1 && !visited[newRow][newCol]){
                if(!isSurroundedByWater(graph, newRow, newCol, visited)){
                    return false;
                }
            }
        }
        return true;
    }

    public static void replaceWith0s(int[][] graph, int row, int col){
        graph[row][col] = 0;
        int[][] directions = {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
        for(int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if(isPositionValid(graph, newRow, newCol) && graph[newRow][newCol] == 1 ){
                replaceWith0s(graph, newRow, newCol);
            }
        }
    }

    public static boolean isPositionValid(int[][] graphs, int row, int col){
        if(row >= 0 && row < graphs.length && col >=0 && col < graphs[0].length){
            return true;
        } else{
            return false;
        }
    }
}

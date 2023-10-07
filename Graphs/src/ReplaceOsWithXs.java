public class ReplaceOsWithXs {
    public static void main(String[] args) {
        String[][] graph = {
                {"X", "O", "X", "O"},
                {"X", "O", "X", "O"},
                {"X", "X", "X", "X"},
                {"X", "O", "X", "O"},
                {"X", "O", "X", "O"},
                {"X", "O", "O", "O"}

        };
        boolean[][] visitedMatrix = new boolean[graph.length][graph[0].length];
        int rows = graph.length;
        int cols = graph[0].length;
        for(int i=0; i< rows; i++){
            for(int j=0; j< cols; j++){
                if(graph[i][j].equals("O") &&  !visitedMatrix[i][j]){
                    if(checkIfApplicable(graph, rows, cols, i, j, visitedMatrix)){
                        System.out.println("Applicable at  "+ i + " " + j);
                        replaceWithX(graph, rows, cols, i, j);
                    }
                }
            }
        }
        GraphRepresentation.printAMatrix(graph);
    }

    public static void replaceWithX(String[][] graph, int rows, int cols, int row, int col){
        if(row == 0 || row == rows-1 || col == 0 || col == cols-1){
            return;
        }
        graph[row][col] = "X";
        int[][] directions = {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
        for(int[] direction : directions){
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                if(graph[newRow][newCol].equals("O")){
                    replaceWithX(graph, rows, cols, newRow, newCol);
                }

        }
    }

    public static boolean checkIfApplicable(String[][] graph, int rows, int cols, int row, int col, boolean[][] visitedMatrix){
        if(row == 0 || row == rows-1 || col == 0 || col == cols-1){
            return false;
        }
        visitedMatrix[row][col] = true;
        int[][] directions = {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
        for(int[] direction : directions){
            int newRow = row + direction[0];
            int newCol = col + direction[1];
//            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                if ( graph[newRow][newCol].equals("O") && !visitedMatrix[newRow][newCol]) {
                    if (!checkIfApplicable(graph, rows, cols, newRow, newCol, visitedMatrix)) {
                        return false;
                    }
                }
//            }
        }



        return true;
    }
}

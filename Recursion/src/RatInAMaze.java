import java.util.ArrayList;
import java.util.List;

public class RatInAMaze {
    public static void main(String[] args) {
        int[][] maze = {
                { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 1, 1, 0, 0 },
                { 0, 1, 1, 1 }
        };
        int numRows = maze.length;
        int numCols = maze[0].length;

        boolean[][] visitedMatrix = new boolean[numRows][numCols];
        List<String> ans = new ArrayList<>();
        visitedMatrix[0][0] = true;
        // Should be sorted in a lexicographical order, DLRU

        solveTheMaze(maze, visitedMatrix, 0, 0, "", ans );
        ans.forEach(System.out::println);

    }

    public static void solveTheMaze(int[][] maze, boolean[][] visitedMatrix, int row, int col, String currentPath, List<String> list){
        if(row == maze.length-1 && col == maze[0].length-1){
            list.add(currentPath);
            return;
        }
        // go down
        if(isValidPosition(maze, visitedMatrix, row+1, col)){
            visitedMatrix[row+1][col] = true;
            solveTheMaze(maze, visitedMatrix, row+1, col, currentPath + "D", list);
            visitedMatrix[row+1][col] = false;
        }
        // go left
        if(isValidPosition(maze, visitedMatrix, row, col-1)){
            visitedMatrix[row][col-1] = true;
            solveTheMaze(maze, visitedMatrix, row, col-1, currentPath + "L", list);
            visitedMatrix[row][col-1] = false;
        }
        // go right
        if(isValidPosition(maze, visitedMatrix, row, col+1)){
            visitedMatrix[row][col+1] = true;
            solveTheMaze(maze, visitedMatrix, row, col+1, currentPath + "R", list);
            visitedMatrix[row][col+1] = false;
        }
        // go up
        if(isValidPosition(maze, visitedMatrix, row-1, col)){
            visitedMatrix[row-1][col] = true;
            solveTheMaze(maze, visitedMatrix, row-1, col, currentPath + "U", list);
            visitedMatrix[row-1][col] = false;
        }
    }

    public static boolean isValidPosition(int[][] maze, boolean[][] visitedMatrix, int newRow, int newCol){
        if(newRow < 0 || newCol < 0){
            return false;
        }
        if(newRow >= maze.length || newCol >= maze[0].length){
            return false;
        }
        if(maze[newRow][newCol] == 0 || visitedMatrix[newRow][newCol] == true){
            return false;
        }
        return true;
    }

}

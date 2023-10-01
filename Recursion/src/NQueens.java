import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fill N Queens in a chessboard of size N, such that they do not attack each other
 */
public class NQueens {
    public static void main(String[] args) {
        int boardSize = 4;
        List<List<List<Integer>>> solutions = new ArrayList<>();
        List<List<Integer>> board = new ArrayList<>();
        for(int i=0; i< boardSize; i++){
            Integer[] arr = new Integer[boardSize];
            Arrays.fill(arr, 0);
            board.add(new ArrayList<>(Arrays.asList(arr)));
        }
        solveBoard(0, board, solutions);
        System.out.println("Printing out the solutions");
        solutions.forEach(solution -> {
            solution.forEach(System.out::println);
            System.out.println("-------------------");
        });


    }

    public static void solveBoard(int col, List<List<Integer>> board, List<List<List<Integer>>> solutions){
        if(col == board.size()){
            List<List<Integer>> tempBoard = new ArrayList<>();
            board.forEach(row -> tempBoard.add(new ArrayList<>(row)));
            solutions.add(new ArrayList<>(tempBoard));
            return ;
        }
        for(int i = 0; i< board.size(); i++){
            // for a given col, going through all the rows
            boolean isSafe = !isAttacking(board, i, col);
            if(isSafe){
                board.get(i).set(col, 1);
                solveBoard(col+1, board, solutions);
                board.get(i).set(col, 0);
            }
        }
    }

    public static boolean isAttacking(List<List<Integer>> board, int row, int col){
        // upper left diagonal
        int i = row;
        int j = col;
        while(i >= 0 && j >= 0){
            if(board.get(i).get(j) == 1){
                return true;
            }
            i -= 1;
            j -= 1;
        }

        // bottom left diagonal
        i = row;
        j = col;
        while( i< board.size() && j >= 0){
            if(board.get(i).get(j) == 1){
                return true;
            }
            i += 1;
            j -= 1;
        }

        // check in that row itself
        int c = col;
        while(c >= 0){
            if(board.get(row).get(c) == 1){
                return true;
            }
            c -= 1;
        }
        return false;
    }
}

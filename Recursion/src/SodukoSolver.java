public class SodukoSolver {
    public static void main(String[] args) {
        int[][] sudoku = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {1, 2, 3, 4, 5, 6, 7, 8, 9}

        };

        int[][] sudoku2 = {
                {7, 8, 5, 4, 3, 9, 1, 2, 6},
                {6, 1, 2, 8, 7, 5, 3, 4, 9},
                {4, 9, 3, 6, 2, 1, 5, 7, 8},
                {8, 5, 7, 9, 4, 3, 2, 6, 1},
                {2, 6, 1, 7, 5, 8, 9, 3, 4},
                {9, 3, 4, 1, 6, 2, 7, 8, 5},
                {5, 7, 8, 3, 9, 4, 6, 1, 2},
                {1, 2, 6, 5, 8, 7, 4, 9, 3},
                {3, 4, 9, 2, 1, 6, 8, 0, 7}
        };

        boolean isSolved = solveSudoku(sudoku);
        System.out.println(isSolved);
    }

    public static boolean solveSudoku(int[][] sudoku){

        for(int i=0; i< sudoku.length; i++){
            for(int j=0; j<sudoku.length; j++){
                if(sudoku[i][j] == 0){
                    for(int candidate = 1; candidate <= 9; candidate++){
                        if(isValid(sudoku, i, j, candidate)){
                            sudoku[i][j] =  candidate;
                            if(!solveSudoku(sudoku)){
                                sudoku[i][j] = 0;
                                continue;
                            } else{
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    public static boolean isValid(int[][] sudoku, int row, int col, int candidate){
        // check in that row
        for(int j=0; j<sudoku.length; j++){
            if(candidate == sudoku[row][j]){
                return false;
            }
        }
        // check in that col
        for(int i=0; i< sudoku.length; i++){
            if(candidate == sudoku[i][col]){
                return false;
            }
        }
        // check in the inner box
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for(int i=boxRow; i< boxRow + 3; i++){
            for(int j = boxCol; j< boxCol + 3; j++){
                if(sudoku[i][j] == candidate){
                    return false;
                }
            }
        }
        return true;
    }
}

public class FloodFillAlgorithm {
    public static void main(String[] args) {
        int[][] graph = {
                {0,1,1,1,0},
                {0,1,1,0,0},
                {1,0,0,1,0},
                {1,1,0,0,1}
        };
        int startRow = 0;
        int startCol = 0;
        int newColor = 3;
        int initialColor = 0;

        dfs(graph, startRow, startCol, initialColor, newColor);
        System.out.println("After coloring the graph");
        for(int i=0; i<graph.length; i++){
            for(int j=0; j<graph[0].length; j++){
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void dfs(int[][] graph, int startRow, int startCol, int initialColor,  int newColor){
        if(graph[startRow][startCol] == initialColor){
            graph[startRow][startCol] = newColor;
        } else{
            return;
        }
        if(startRow - 1 >= 0){
            dfs(graph, startRow-1, startCol, initialColor, newColor);
        }
        if(startRow + 1 < graph.length){
            dfs(graph, startRow+1, startCol, initialColor, newColor);
        }
        if(startCol - 1 >= 0){
            dfs(graph, startRow, startCol-1, initialColor, newColor);
        }
        if(startCol + 1 < graph[0].length){
            dfs(graph, startRow, startCol+1, initialColor, newColor);
        }
    }
}

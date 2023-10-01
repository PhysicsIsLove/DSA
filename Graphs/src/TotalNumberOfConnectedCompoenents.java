import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TotalNumberOfConnectedCompoenents {
    public static void main(String[] args) {

    }

    public int totalConnectedComponents(int[][] isConnected) {

        List<List<Integer>> adjList = new ArrayList<>();
        for(int i =0; i< isConnected.length; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i=0; i< isConnected.length; i++){
            for(int j = 0; j< isConnected.length; j++){
                int node1 = i;
                int node2 = j;
                if(isConnected[i][j] == 1){
                    adjList.get(node1).add(node2);
                    adjList.get(node2).add(node1);
                }
            }
        }
        Boolean[] visitedArray = new Boolean[isConnected.length];
        Arrays.fill(visitedArray, false);
        int count = 0;
        for(int i=0; i< visitedArray.length; i++){
            if(visitedArray[i] == false){
                count += 1;
                dfs(adjList, visitedArray, i);
            }
        }
        return count;
    }

    public void dfs(List<List<Integer>> adjList, Boolean[] visitedArray, int node){
        visitedArray[node] = true;
        for(Integer n: adjList.get(node)){
            if(visitedArray[n] == false){
                dfs(adjList, visitedArray, n);
            }
        }
    }
}

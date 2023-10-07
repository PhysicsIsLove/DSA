import java.util.ArrayList;
import java.util.List;

public class DetectCycleInDirectedGraph {
    public static void main(String[] args) {


        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(List.of());
        adjList.add(List.of(2, 0));
        adjList.add(List.of(3));
        adjList.add(List.of(0));
        adjList.add(List.of(0));
        boolean[] visitedArr = new boolean[adjList.size()];
        boolean[] pathVisited = new boolean[adjList.size()];

        boolean ans = false;
        for(int i=0; i< adjList.size(); i++){
            if(visitedArr[i] == false){
                if(findIfGraphHasCycle(adjList, visitedArr, pathVisited, i)){
                    ans = true;
                    break;
                }
            }
        }

        System.out.println("Answer is "+ ans);

    }

    public static boolean findIfGraphHasCycle(List<List<Integer>> adjList, boolean[] visitedArr, boolean[] pathVisited, int node){
        visitedArr[node] = true;
        pathVisited[node] = true;
        for(int adjNode: adjList.get(node)){
            if(visitedArr[adjNode] == false){
                if(findIfGraphHasCycle(adjList, visitedArr, pathVisited, adjNode)){
                    return true;
                }
            } else{
                if(pathVisited[adjNode] == true){
                    return true;
                }
            }
        }
        pathVisited[node] = false;
        return false;
    }
}

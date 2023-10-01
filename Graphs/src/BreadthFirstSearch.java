import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {
    public static void main(String[] args) {
        int[][] input = {
                {1,2},
                {1,3},
                {3,4},
                {2,4},
                {3,5},
                {5,4}
        };
        List<List<Integer>> adjList = GraphRepresentation.makeAdjList(input, 5);
        List<Integer> listOfAllNodes = bfs(adjList, 5);
        System.out.println(listOfAllNodes);
    }

    public static List<Integer> bfs(List<List<Integer>> adjList, int startNode){
        int[] visitedArr = new int[adjList.size()];
        visitedArr[startNode] = 1;
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.add(startNode);
        while(!queue.isEmpty()){
            int node = queue.poll();
            list.add(node);
            for(Integer adjNode: adjList.get(node)){
                if(visitedArr[adjNode] == 0){
                    visitedArr[adjNode] = 1;
                    queue.add(adjNode);
                }
            }
        }
        return list;
    }
}

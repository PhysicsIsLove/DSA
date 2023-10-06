;import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DetectCycleInUndirectedGraphUsingBFS {
    public static void main(String[] args) {
        int numNodes = 2;
        // 1-based indexing of the nodes
        int[][] input = {
//                {1,2},
//                {2,1},
//                {3,4},
//                {1,3},
//                {3,5},
//                {3,6},
//                {0,3}
                {0,1},{1,0}
        };
        List<List<Integer>> adjlist = GraphRepresentation.makeAdjList(input, numNodes);
        boolean cycleExists = findIfCycleExists(adjlist, numNodes);
        System.out.println("Does Cycle Exists : " + cycleExists);

    }

    public static boolean findIfCycleExists(List<List<Integer>> adjList, int numNodes){
        boolean[] visitedArr = new boolean[numNodes];
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(List.of(0, -1));
        visitedArr[0] = true;
        while(!queue.isEmpty()){
            List<Integer> nodes = queue.poll();
            int node  =nodes.get(0);
            int parentNode = nodes.get(1);
            for(Integer adjacentNode: adjList.get(node)){
                if(visitedArr[adjacentNode] == true){
                    if(adjacentNode != parentNode){
                        return true;
                    }
                } else{
                    visitedArr[adjacentNode] = true;
                    queue.add(List.of(adjacentNode, node));
                }
            }
        }
        return false;
    }
}

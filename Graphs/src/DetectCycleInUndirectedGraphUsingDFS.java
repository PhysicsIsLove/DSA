import java.util.List;

public class DetectCycleInUndirectedGraphUsingDFS {
    public static void main(String[] args) {
        int numNodes = 4;
        // 1-based indexing of the nodes
        int[][] input = {
//                {1,2},
//                {2,3},
//                {3,4},
//                {4,0},
                {0,1},
                {0,2},
//                {2,4},
//                {3,4},
                {1,3},
//                {3,5},
//                {3,6},
//                {0,3}
//                {0,1},{1,0}
        };
        List<List<Integer>> adjlist = GraphRepresentation.makeAdjList(input, numNodes);
        boolean cycleExists = findIfCycleExists(adjlist, numNodes);
        System.out.println("Does Cycle Exists : " + cycleExists);
    }

    public static boolean findIfCycleExists(List<List<Integer>> adjList, int numNodes){
        boolean[] visitedArr = new boolean[numNodes];

        for(int i=0; i<numNodes; i++){
            if(!visitedArr[i]){
                visitedArr[i] = true;
                if(dfs(i, -1, adjList, visitedArr)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(int node, int parentNode, List<List<Integer>> adjList, boolean[] visitedArr){
        for(Integer adjacentNode : adjList.get(node)){
            if(visitedArr[adjacentNode]){
                if(adjacentNode != parentNode){
                    return true;
                }
            } else{
                visitedArr[adjacentNode] = true;
                if(dfs(adjacentNode, node, adjList, visitedArr)){
                    return true;
                }
            }
        }
        return false;
    }
}

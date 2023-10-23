import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The graph has to the DAG
 */
public class TopologicalSortUsingKahnAlgorithmBFS {
    public static void main(String[] args) {

        List<List<Integer>> adjList = new ArrayList<>();

        adjList.add(List.of());
        adjList.add(List.of(0,2 ));
        adjList.add(List.of(3));
        adjList.add(List.of(4));
        adjList.add(List.of(0));
        adjList.add(List.of(4));
        int[] inDegree = new int[adjList.size()];
        for(int i=0; i< adjList.size(); i++){
            for(int node : adjList.get(i)){
                inDegree[node] += 1;
            }
        }
        List<Integer> ans = findTopologicalSorting(adjList, inDegree);
        System.out.println("the topological sort is "+ ans);
    }

    public static List<Integer> findTopologicalSorting(List<List<Integer>> adjList, int[] indegree){
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i< indegree.length; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }
        while(!queue.isEmpty()){
            int node = queue.poll();
            ans.add(node);
            for(int adjNode: adjList.get(node)){
                indegree[adjNode] -= 1;
                if(indegree[adjNode] == 0){
                    queue.add(adjNode);
                }
            }
        }
        return ans;
    }
}

import java.util.ArrayList;
import java.util.List;

public class MColoringProblem {
    public static void main(String[] args) {
        int[][] paths = {
                {1, 2},
                {2, 3},
                {3, 1}
        };
        int n = 3;

        gardenNoAdj(n, paths);

    }

    public static int[] gardenNoAdj(int n, int[][] paths) {
        // n is the number of nodes in the graph
        // it is an m-coloring problem, but m is not given, we have to find the smallest m actually
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<n; i++){
            adjList.add(new ArrayList<>());
        }
        for(int[] path: paths){
            int node1 = path[0] - 1;
            int node2 = path[1] - 1;
            adjList.get(node1).add(node2);
            adjList.get(node2).add(node1);
        }

        int[] ans = new int[n];
        solve(n, adjList, 0, ans);
        return ans;
    }

    public static boolean solve(int n, List<List<Integer>> adjList, int node, int[] ans){
        if(node == n){
            return true;
        }
        for(int color=1; color<=n; color++){
            if(isSafe(color, adjList, node, ans)){
                ans[node] = color;
                boolean isPossible = solve(n, adjList, node+1, ans);
                if(isPossible){
                    return true;
                } else{
                    ans[node] = 0;
                }
            }
        }
        return false;
    }

    public static boolean isSafe(int color, List<List<Integer>> adjList, int node, int[] ans){
        for(Integer i: adjList.get(node)){
            if(color == ans[i]){
                return false;
            }
        }
        return true;
    }
}

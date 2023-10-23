import java.util.*;

public class StronglyConnectedComponentsKosarajuAlgorithm {

    public static void main(String[] args) {
        int numNodes = 9;
        int[][] graph = {
                {3, 1},
                {2, 0},
                {0, 3},
                {1, 2},
                {1, 4},
                {4, 5},
                {5, 6},
                {6, 7},
                {7, 8},
                {8, 6}
        };

        Stack<Integer> stack = new Stack<>();

        List<List<Integer>> adjList = makeAdjList(graph, numNodes);
        boolean[] visitedArr = new boolean[numNodes];
        for(int i=0; i< numNodes; i++){
            if(!visitedArr[i]){
                dfs(adjList, i, visitedArr, stack);
            }
        }
        System.out.println("Stack is "+ stack.peek());

        List<List<Integer>> connectedComponents = new ArrayList<>();

        adjList = reverseEdgesOfTheGraph(graph, numNodes);
        Arrays.fill(visitedArr, false);

        while(!stack.isEmpty()){
            int node = stack.pop();
            if(visitedArr[node] == false){
                List<Integer> newConnectedComponent = new ArrayList<>();
                dfs2(adjList, node, visitedArr, newConnectedComponent);
                connectedComponents.add(newConnectedComponent);
            }
        }
        System.out.println("the total number of connected components are "+ connectedComponents.size());
        System.out.println("the components are "+ connectedComponents);
    }

    public static void dfs (List<List<Integer>> adjList, int node, boolean[] visited, Stack<Integer> stack){
        visited[node] = true;
        for(int adjNode: adjList.get(node)){
            if(!visited[adjNode]){
                dfs(adjList, adjNode, visited, stack);
            }
        }
        stack.push(node);
    }

    public static void dfs2 (List<List<Integer>> adjList, int node, boolean[] visited, List<Integer> connectedComp){
        visited[node] = true;
        connectedComp.add(node);
        for(int adjNode: adjList.get(node)){
            if(!visited[adjNode]){
                dfs2(adjList, adjNode, visited, connectedComp);
            }
        }
    }


    public static List<List<Integer>> makeAdjList(int[][] graph, int numNodes){
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i< numNodes; i++){
            adjList.add(new ArrayList<>());
        }
        for(int[] edge: graph){
            int node1 = edge[0];
            int node2 = edge[1];
            adjList.get(node1).add(node2);
        }
        return adjList;
    }

    public static List<List<Integer>> reverseEdgesOfTheGraph(int[][] graph, int numNodes){
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i< numNodes; i++){
            adjList.add(new ArrayList<>());
        }
        for(int[] edge: graph){
            int node1 = edge[0];
            int node2 = edge[1];
            adjList.get(node2).add(node1);
        }
        return adjList;
    }
}

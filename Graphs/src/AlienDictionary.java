import java.util.*;

/**
 * Given a sorted list of words of an alien dictionary. Find the order of the letters in that dictonary.
 * The number of character in that dictionary are given as n
 */
public class AlienDictionary {
    public static void main(String[] args) {
        int numLetters = 4;
        String[] words = {"baa", "abcd", "abca", "cab", "cad"}; // Alien's dictionary
//        String[] words = {"abca", "abcd", "baa", "cab", "cad"}; // Humans dictionary
        List<List<Integer>> adjList = makeAdjList(words, numLetters);
        List<Integer> topologicalOrder = findTopologicalSortOrdering(adjList);
        String ans = "";
        for(Integer c: topologicalOrder){
            char x = (char) ('a' + c);
            ans += x;
        }
        System.out.println("The order is "+ ans);

    }

    public static List<List<Integer>> makeAdjList(String[] listOfWords, int numWords){
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<numWords; i++){
            adjList.add(new ArrayList<>());
        }
        for(int i=0; i< listOfWords.length-1; i++){
            String word1 = listOfWords[i];
            String word2 = listOfWords[i+1];
            int index = 0;
            while(word1.charAt(index) == word2.charAt(index)){
                index += 1;
            }
            int c1 = word1.charAt(index) - 'a';
            int c2 = word2.charAt(index) - 'a';
            adjList.get(c1).add(c2);
        }
        return adjList;
    }

    public static List<Integer> findTopologicalSortOrdering(List<List<Integer>> adjList){
        int[] inDegree = new int[adjList.size()];
        for(int i=0; i< adjList.size(); i++){
            for(int node : adjList.get(i)){
                inDegree[node] += 1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i< inDegree.length; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }
        while(!queue.isEmpty()){
            int node = queue.poll();
            ans.add(node);
            for(int adjNode: adjList.get(node)){
                inDegree[adjNode] -= 1;
                if(inDegree[adjNode] == 0){
                    queue.add(adjNode);
                }
            }
        }
        return ans;
    }
}

import java.util.*;

/**
 * Given a list of accounts. Merge them if two or more accounts belong to the same person
 */
public class MergeAccounts {
    public static void main(String[] args) {

        List<List<String>> list = new ArrayList<>();
        list.add(List.of("John","johnsmith@mail.com","john_newyork@mail.com"));
        list.add(List.of("John","johnsmith@mail.com","john00@mail.com"));
        list.add(List.of("Mary","mary@mail.com"));
        list.add(List.of("John","johnnybravo@mail.com"));

        accountsMerge(list);


    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> map = new HashMap<>();
        int numNodes = accounts.size();
        int[] parents = new int[numNodes];
        for(int i=0; i< numNodes; i++){
            parents[i] = i;
        }
        int[] ranks = new int[numNodes];

        for(int i=0; i< numNodes; i++){
            List<String> accountInfo = accounts.get(i);
            for(int j=1; j< accountInfo.size(); j++){
                String email = accountInfo.get(j);
                if(map.containsKey(email)){
                    unionByRank(i, map.get(email), ranks, parents);
                } else{
                    map.putIfAbsent(email, getParent(i, parents));
                }
            }
        }

        map.forEach((key, value) -> {
            map.put(key, getParent(value, parents));
        });
        Map<Integer, List<String>> finalMap = new HashMap<>();
        map.forEach((key, value) -> {
            if(finalMap.containsKey(value)){
                finalMap.get(value).add(key);
            } else{
                List<String> emails = new ArrayList<>();
                emails.add(key);
                finalMap.put(value, emails);
            }
        });

        List<List<String>> ans = new ArrayList<>();
        finalMap.forEach((key, value) -> {
            List<String> emails = value;
            Collections.sort(emails);
            emails.add(0, accounts.get(key).get(0));
            ans.add(emails);
        });

        return ans;
    }

    public static void unionByRank(int node1, int node2, int[] ranks, int[] parents){
        int parent1 = getParent(node1, parents);
        int parent2 = getParent(node2, parents);

        int rank1 = ranks[node1];
        int rank2 = ranks[node2];

        if(parent1 == parent2){
            return;
        } else{
            if(rank1 == rank2){
                parents[parent1] = parent2;
                ranks[parent2] += 1;
            } else if (rank1 > rank2){
                parents[parent2] = parent1;
            } else{
                parents[parent1] = parent2;
            }
        }
    }

    public static int getParent(int node, int[] parents){
        if(node != parents[node]){
            parents[node] = getParent(parents[node], parents);
        }
        return parents[node];
    }
}

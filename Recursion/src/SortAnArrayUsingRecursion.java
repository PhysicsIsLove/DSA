import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortAnArrayUsingRecursion {

    public static void main(String[] args) {
        List<Integer> ans = sort(new ArrayList<>(Arrays.asList(5,1, 0,9,-3,4,6,1,2,3,4,8,9,4,3,22,1)));
        ans.stream().forEach(System.out::println);
    }

    public static List<Integer> sort(List<Integer> list){
        if(list.size() <= 1){
            return list;
        } else{
            int elementToBeInserted = list.get(0);
            List<Integer> temp = new ArrayList<>();
            temp.addAll(list.subList(1, list.size()));
//            List<Integer> temp = new ArrayList<>(list.subList(1, list.size()));
//            List<Integer> sortedList = sort(list.subList(1,list.size()));
//            List<Integer> sortedList = sort(temp);
            List<Integer> sortedList = sort(list.subList(1, list.size()));
            int indexToBeInsertedAt = sortedList.size();
            for(int i=0; i< sortedList.size(); i++){
//                if(i == sortedList.size()){
//                    sortedList.add(i, elementToBeInserted);
//                }
                if(elementToBeInserted < sortedList.get(i)){
//                    sortedList.add(i, elementToBeInserted);
                    indexToBeInsertedAt = i;
                    break;
                }
            }
            sortedList.add(indexToBeInsertedAt, elementToBeInserted);
            return sortedList;

        }
    }


}

import java.util.Arrays;

public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        int[] arr = {4, 2, 1, 5, 6 ,3};
        int totalSum = Arrays.stream(arr).reduce(0, (a,b) -> a+b);
        System.out.println("total sum "+ totalSum);
        if(totalSum % 2 == 1){
            System.out.println("False");
        }
        else{
            boolean ans = findUsingRecursion(arr, totalSum/2, arr.length-1);
            if(ans){
                System.out.println("True");
            } else{
                System.out.println("False");
            };
        }

        // the other steps are the same as the SubsetSumEqualToTarget -> Memoization and Tabulation
    }

    public static boolean findUsingRecursion(int[] arr, int target, int index){
        if(target == 0){
            return true;
        }
        if(index == 0){
            return arr[0] == target;
        }
        boolean notTake = findUsingRecursion(arr, target, index - 1);
        boolean take = false;
        if(arr[index] <= target){
            take = findUsingRecursion(arr, target-arr[index], index-1);
        }
        return take || notTake;
    }
}

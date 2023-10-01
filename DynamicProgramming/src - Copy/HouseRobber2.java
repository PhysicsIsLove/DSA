/**
 * A robber is robbing the houses in a street. The houses are arranged in the street in a circular manner.
 * He cannot rob adjacent houses. What is the maximum money that he can rob.
 * The houses are given in the form of an array and the last house is adjacent with the first one.
 *
 * Make two sub paths ->  one with the first house removed and the other with the last path removed
 */
public class HouseRobber2 {
    public static void main(String[] args) {
        int[] arr = {2,1,4,9};
        int[] arr1 = new int[arr.length - 1];
        int[] arr2 = new int[arr.length - 1];
        for(int i=0; i< arr.length-1; i++){
            arr1[i] = arr[i];
        }
        for(int i=1; i< arr.length; i++){
            arr2[i-1] = arr[i];
        }
        int ans1 = findTheMaxMoneyRobbed(arr1);
        int ans2 = findTheMaxMoneyRobbed(arr2);
        System.out.println("the max sum robbed is "+ Math.max(ans1, ans2));
    }

    public static int findTheMaxMoneyRobbed(int[] arr){
        int prev = arr[0];
        int prev2 = 0;
        for(int i=1; i< arr.length; i++){
            int pick = prev2 + arr[i];
            int notPick = prev;
            int maxAtItsPosition = Math.max(pick, notPick);
            prev2 = prev;
            prev = maxAtItsPosition;
        }
        return prev;
    }
}

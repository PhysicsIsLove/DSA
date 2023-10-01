/**
 * Can either take 1 steps or 2 steps, starting at the 0th step
 */
public class NumberOfWaysToReachTheNthStair {


    public static void main(String[] args) {
        int n = 4;
        int ways = findNumWays(n);
        System.out.println(ways);
    }

    public static int findNumWays(int n){
        if(n == 0 || n == 1){
            return 1;
        }
        return findNumWays(n-1) + findNumWays(n-2);
    }
}

import java.util.Arrays;

/**
 * There are N stairs which the frog needs to jump to. It can jump either 1 or 2 stairs at a time.
 * The heights of each stair is different and the energy spent in jumping from stairA to stairB is the absolute difference of stairA height and stairB height
 * Find the minimum energy it can spend to reach the Nth stair
 */
public class FrogJump {
    public static void main(String[] args) {
        int n = 0;
        int[] heights = {30,10,60,10,60,50};
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        int energy = findMinEnergyUsingMemoization(n, heights, dp);
        System.out.println("Min energy needed using memoization "+ energy);
        int energy2 = findMinEnergyUsingTabulation(n, heights);
        System.out.println("Min energy need using tabulation "+ energy2);
        int energy3 = findMinEnergyUsingTabulationAndSpaceOptimization(n, heights);
        System.out.println("Min energy need using tabulation and space optimization "+ energy3);
    }

    public static int findMinEnergyUsingMemoization(int n, int[] heights, int[] dp){
        if(n == 0){
            return 0;
        }
        if(dp[n] != -1){
            return dp[n];
        }
        int minEnergyWithTwoStepsLeft = Integer.MAX_VALUE;
        int minEnergyWithOneStepLeft = findMinEnergyUsingMemoization(n-1, heights, dp) + Math.abs(heights[n] - heights[n-1]);
        if(n > 1){
            minEnergyWithTwoStepsLeft = findMinEnergyUsingMemoization(n-2, heights, dp) + Math.abs(heights[n] - heights[n-2]);
        }
        dp[n] = Math.min(minEnergyWithTwoStepsLeft, minEnergyWithOneStepLeft);
        return dp[n];
    }

    public static int findMinEnergyUsingTabulation(int n, int[] heights){
        if(n == 0){
            return 0;
        }
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1; i<=n; i++){
            int minEnergyFromLastStep = dp[i-1] + Math.abs(heights[i] - heights[i-1]);
            int minEnergyFromSecondLastStep = Integer.MAX_VALUE;
            if(i > 1){
                minEnergyFromSecondLastStep = dp[i-2] + Math.abs(heights[i] - heights[i-2]);
            }
            dp[i] = Math.min(minEnergyFromLastStep, minEnergyFromSecondLastStep);
        }
        return dp[n];
    }

    public static int findMinEnergyUsingTabulationAndSpaceOptimization(int n, int[] heights){
        if(n == 0){
            return 0;
        }
        int prev = 0;
        int prev2 = 0;
        int current = 0;

        for(int i=1; i<=n; i++){
            int minEnergyFromLastStep = prev + Math.abs(heights[i] - heights[i-1]);
            int minEnergyFromSecondLastStep = Integer.MAX_VALUE;
            if(i > 1){
                minEnergyFromSecondLastStep = prev2 + Math.abs(heights[i] - heights[i-2]);
            }
            current = Math.min(minEnergyFromLastStep, minEnergyFromSecondLastStep);
            prev2 = prev;
            prev = current;
        }
        return current;
    }
}
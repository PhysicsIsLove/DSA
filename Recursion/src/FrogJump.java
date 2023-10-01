/**
 * There are N stairs which the frog needs to jump to. It can jump either 1 or 2 stairs at a time.
 * The heights of each stair is different and the enery spent in jumpting from stairA to stairB is the absolute difference of stairA height and stairB height
 * Find the minimum energy it can spend to reach the Nth stair
 */
public class FrogJump {
    public static void main(String[] args) {
        int n = 5;
        int[] heights = {30,10,60,10,60,50};
        int energy = findMinEnergy(n, heights);
        System.out.println("Min energy needed "+ energy);
    }

    public static int findMinEnergy(int n, int[] heights){
        if(n == 0){
            return 0;
        }
        int minEnergyWithTwoStepsLeft = Integer.MAX_VALUE;
        int minEnergyWithOneStepLeft = findMinEnergy(n-1, heights) + Math.abs(heights[n] - heights[n-1]);
        if(n > 1){
            minEnergyWithTwoStepsLeft = findMinEnergy(n-2, heights) + Math.abs(heights[n] - heights[n-2]);
        }
        return Math.min(minEnergyWithTwoStepsLeft, minEnergyWithOneStepLeft);
    }
}

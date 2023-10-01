/**
 * A Ninja needs to train daily for N days. Each day he can do one of three tasks. Each tassk is associated with some points.
 * he cannot do the same task for consecutive days. Find the maximum amount of points he can collect after the Nth day.
 */
public class NinjasTraining {
    public static void main(String[] args) {
        int[][] arr = {
                {10, 20, 40},
                {45, 60, 20},
                {30, 10, 90},
                {30, 50, 70}
        };

        int[][] arr2 = {
                {10, 20, 40},
                {45, 60, 100},
        };


        int ans = findMaxPoints(arr2, 0, -1, 0, Integer.MIN_VALUE);
        System.out.println("Max points earned " +  ans);
    }

    public static int findMaxPoints(int[][] arr, int day, int prevExercise, int currentPoints, int maxPoints){
        if(day == arr.length){
            if(maxPoints < currentPoints){
                maxPoints = currentPoints;
                return maxPoints;
            }
        }
        int withFirst = currentPoints;
        int withSecond = currentPoints;
        int withThird = currentPoints;
        if(prevExercise != 0){
            withFirst = findMaxPoints(arr, day+1, 0, currentPoints + arr[day][0], maxPoints);
        }
        if(prevExercise != 1){
            withSecond = findMaxPoints(arr, day+1, 1, currentPoints + arr[day][1], maxPoints);
        }
        if(prevExercise != 2){
            withThird = findMaxPoints(arr, day+1, 2, currentPoints + arr[day][2], maxPoints);
        }

        return Math.max(withFirst, Math.max(withSecond, withThird));
    }
}

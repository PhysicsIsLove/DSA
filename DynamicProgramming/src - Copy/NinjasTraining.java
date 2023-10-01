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

        int ans = findMaxPointsUsingRecursion(arr, arr.length-1, -1);
        System.out.println("Using recursion");
        System.out.println(ans);

        System.out.println("Using Memoization");
        int[][] dp = new int[arr.length][3];
        for(int i=0; i< dp.length; i++){
            for(int j=0; j<3; j++){
                dp[i][j] = -1;
            }
        }
        int ans1 = findMaxPointsUsingMemoization(arr, arr.length-1, -1, dp);
        System.out.println(ans1);

        System.out.println("Using Tabulation");
        int ans2 = findMaxPointsUsingTabulation(arr);
        System.out.println(ans2);

        System.out.println("Tabulation plus space optimization");
        int ans3 = findMaxPointsUsingTabulationAndSpaceOptimization(arr);
        System.out.println(ans3);
    }

    public static int findMaxPointsUsingRecursion(int[][] arr, int day, int prevIndex){
        if(day == 0){
            int tempMax = Integer.MIN_VALUE;
            for(int i=0; i< 3; i++){
                if(i != prevIndex){
                    if(arr[day][i] > tempMax){
                        tempMax = arr[day][i];
                    }
                }
            }
            return tempMax;
        }
        int withFirst = 0;
        int withSecond = 0;
        int withThird = 0;
        if(prevIndex != 0){
            withFirst = arr[day][0] + findMaxPointsUsingRecursion(arr, day-1, 0);
        }
        if(prevIndex != 1){
            withSecond = arr[day][1] + findMaxPointsUsingRecursion(arr, day-1, 1);
        }
        if(prevIndex != 2){
            withThird = arr[day][2] + findMaxPointsUsingRecursion(arr, day-1, 2);
        }

        return Math.max(withFirst, Math.max(withSecond, withThird));

    }

    public static int findMaxPointsUsingMemoization(int[][] arr, int day, int prevIndex, int[][] dp){
        if(day == 0){
            int tempMax = Integer.MIN_VALUE;
            for(int i=0; i< 3; i++){
                if(i != prevIndex){
                    if(arr[day][i] > tempMax){
                        tempMax = arr[day][i];
                    }
                }
            }
            return tempMax;
        }

        if(prevIndex >= 0 && prevIndex < 3 && dp[day][prevIndex] != -1){
            return dp[day][prevIndex];
        }

        if(prevIndex != 0){
            dp[day][0] = arr[day][0] + findMaxPointsUsingRecursion(arr, day-1, 0);
        }
        if(prevIndex != 1){
            dp[day][1] = arr[day][1] + findMaxPointsUsingRecursion(arr, day-1, 1);
        }
        if(prevIndex != 2){
            dp[day][2] = arr[day][2] + findMaxPointsUsingRecursion(arr, day-1, 2);
        }

        return Math.max(dp[day][0], Math.max(dp[day][1], dp[day][2]));

    }

    public static int findMaxPointsUsingTabulation(int[][] arr){
        int[][] dp = new int[arr.length][4];
        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];
        dp[0][3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));

        for(int i=1; i< arr.length; i++){
            int day = i;
            for(int j=0; j<4; j++){
                int task = j;
                if(task == 0){
                    dp[day][task] = arr[day][task] + Math.max(dp[day-1][1], dp[day-1][2]);
                }
                if(task == 1){
                    dp[day][task] = arr[day][task] + Math.max(dp[day-1][0], dp[day-1][2]);
                }
                if(task == 2){
                    dp[day][task] = arr[day][task] + Math.max(dp[day-1][1], dp[day-1][0]);
                }
                if(task == 3){
                    dp[day][task] = Math.max(dp[day][0], Math.max(dp[day][1], dp[day][2]));
                }
            }
        }
        return dp[arr.length-1][3];
    }

    public static int findMaxPointsUsingTabulationAndSpaceOptimization(int[][] arr){

        int first = arr[0][0];
        int second = arr[0][1];
        int third = arr[0][2];
        int fourth = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));

        for(int i=1; i< arr.length; i++){
            int day = i;
            int newFirst = 0;
            int newSecond = 0;
            int newThird = 0;
            for(int j=0; j<4; j++){
                int task = j;

                if(task == 0){
                    newFirst = arr[day][task] + Math.max(second, third);
                }
                if(task == 1){
                    newSecond = arr[day][task] + Math.max(first, third);
                }
                if(task == 2){
                    newThird = arr[day][task] + Math.max(second, first);
                }
                if(task == 3){
                    fourth = Math.max(newFirst, Math.max(newSecond, newThird));
                }
            }
            first = newFirst;
            second = newSecond;
            third = newThird;
        }
        return fourth;
    }


}

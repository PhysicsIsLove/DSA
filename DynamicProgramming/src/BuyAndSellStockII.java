import java.util.Arrays;

/**
 * The person can buy and sell multiple times. But before he buys, he must have sold the previously bought stock.
 *
 */
public class BuyAndSellStockII {
    public static void main(String[] args) {
        int[] arr = {78, 90, 3, 1, 56, 78, 34, 55, 12, 3, 4, 8, 67, 45, 23, 100, 7};
        int[] profits = new int[arr.length];
        Arrays.fill(profits, 0);
        for(int i=1; i<arr.length; i++){
            int sellPrice = arr[i];
            for(int j=0; j < i; j++){
                int buyPrice = arr[j];
                if(sellPrice > buyPrice){
                    if(j == 0){
                        profits[i] = Math.max(0 + sellPrice - buyPrice, profits[i]);
                    }else{
                        profits[i] = Math.max(profits[j-1] + sellPrice - buyPrice, profits[i]);
                    }
                } else{
                    profits[i] = profits[i-1];
                }
            }
        }
        int maxProfit = profits[profits.length-1];
        System.out.println("max profit: "+ maxProfit);

        int ans1 = findUsingRecursion(arr, 0, false, 0, 0);
        System.out.println("Answer using recursion "+ ans1);

        int ans2 = findUsingRecursion2(arr, 0, 0, 0, 0);
        System.out.println("Answer using second form of recursion "+ ans2);

        int[][] dp = new int[arr.length][2];
        Arrays.stream(dp).forEach(item -> Arrays.fill(item, -1));

        int ans3 = findUsingMemoization(arr, 0, 0, 0, 0, dp);
        System.out.println("Answer using Memoization "+ ans3);
    }

    public static int findUsingRecursion(int[] arr, int index,  boolean isBought, int priceBought, int currentProfit){
        if(arr.length == index){
            return currentProfit;
        }
        int profit1 = 0;
        int profit2 = 0;
        int profit3 = 0;
        int profit4 = 0;
        if(isBought){
            profit1 = findUsingRecursion(arr, index+1 ,false, 0, currentProfit + arr[index] - priceBought);
            profit2 = findUsingRecursion(arr, index+1 ,true, priceBought, currentProfit);
        } else{
            profit3 = findUsingRecursion(arr, index+1, true, arr[index], currentProfit);
            profit4 = findUsingRecursion(arr, index+1, false ,priceBought, currentProfit);
        }
        return Math.max(Math.max(profit1, profit2), Math.max(profit3, profit4));
    }

    public static int findUsingRecursion2(int[] arr, int index,  int isBought, int priceBought, int currentProfit){
        if(arr.length == index){
            return currentProfit;
        }
        int profit = 0;
        if(isBought == 1){
            profit = Math.max( findUsingRecursion2(arr, index+1 ,0, 0, currentProfit + arr[index] - priceBought),
                    findUsingRecursion2(arr, index+1 ,1, priceBought, currentProfit));
        } else{
            profit = Math.max(findUsingRecursion2(arr, index+1, 1,
                    arr[index], currentProfit), findUsingRecursion2(arr, index+1, 0 ,priceBought, currentProfit));
        }
        return profit;
    }

    // TODO
    public static int findUsingMemoization(int[] arr, int index,  int isBought, int priceBought, int currentProfit, int[][] dp){
        if(arr.length == index){
            return currentProfit;
        }
        if(dp[index][isBought] != -1){
            return dp[index][isBought];
        }
        int profit = 0;
        if(isBought == 1){
            profit = Math.max( findUsingMemoization(arr, index+1 ,0, 0, currentProfit + arr[index] - priceBought, dp),
                    findUsingMemoization(arr, index+1 ,1, priceBought, currentProfit, dp));
        } else{
            profit = Math.max(findUsingMemoization(arr, index+1, 1,
                    arr[index], currentProfit, dp), findUsingMemoization(arr, index+1, 0 ,priceBought, currentProfit, dp));
        }
        dp[index][isBought] = profit;
        return dp[index][isBought];
    }
}

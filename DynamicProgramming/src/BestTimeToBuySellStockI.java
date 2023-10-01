/**
 * Given a list of the stock prices on various days,
 * find the maximum profit that a person can earn if he can at most buy and sell the stock once
 */
public class BestTimeToBuySellStockI {
    public static void main(String[] args) {
        int[] arr = {5,3, 1, 7, 8, 4, 6, 9};
        int maxProfit = 0;
        int min = arr[0];
        for(int i=1; i< arr.length; i++){
            int sellPrice = arr[i];
            if(sellPrice - min > maxProfit){
                maxProfit = sellPrice - min;
            }
            min = Math.min(min, arr[i]);
        }
        System.out.println("max profit " + maxProfit );
    }
}

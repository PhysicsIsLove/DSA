import java.util.Arrays;

public class EvaluateBooleanExpressionToTrue {
    public static void main(String[] args) {
//        String[] expression = {"T", "&", "F", "|", "T", "^", "F", "|", "T"};
        String[] expression = {"T", "|" , "T", "&", "T"};
        int ans = findUsingRecursion(expression, 0, expression.length - 1, true);
        System.out.println("Answer using Recursion " + ans);

        int[][][] dp = new int[expression.length][expression.length][2];
        Arrays.stream(dp).forEach(item -> Arrays.stream(item).forEach(arr -> Arrays.fill(arr, -1)));
        int ans1 = findUsingMemoization(expression, 0, expression.length - 1, true, dp);
        System.out.println("Answer using Memoization " + ans1);

        int ans2 = findUsingTabulation(expression);
        System.out.println("Answer using tabulation "+ ans2);
    }

    public static int findUsingRecursion(String[] expression, int i, int j, boolean isTrue) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            if (isTrue) {
                if (expression[i].equals("T")) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                if (expression[i].equals("T")) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }
        int ways = 0;
        for (int k = i + 1; k <= j - 1; k += 2) {
            int numWaysLeftExpIsFalse = findUsingRecursion(expression, i, k - 1, false);
            int numWaysLeftExpIsTrue = findUsingRecursion(expression, i, k - 1, true);
            int numWaysRightExpIsFalse = findUsingRecursion(expression, k + 1, j, false);
            int numWaysRightExpIsTrue = findUsingRecursion(expression, k + 1, j, true);

            String operand = expression[k];

            if (isTrue) {
                if (operand.equals("|")) {
                    ways += numWaysLeftExpIsTrue * numWaysRightExpIsTrue + numWaysLeftExpIsTrue * numWaysRightExpIsFalse + numWaysLeftExpIsFalse * numWaysRightExpIsTrue;
                } else if (operand.equals("&")) {
                    ways += numWaysLeftExpIsTrue * numWaysRightExpIsTrue;
                } else if (operand.equals("^")) {
                    ways += numWaysLeftExpIsTrue * numWaysRightExpIsFalse + numWaysLeftExpIsFalse * numWaysRightExpIsTrue;
                }
            } else {
                if (operand.equals("|")) {
                    ways += numWaysLeftExpIsFalse * numWaysRightExpIsFalse;
                } else if (operand.equals("&")) {
                    ways += numWaysLeftExpIsFalse * numWaysRightExpIsFalse + numWaysLeftExpIsTrue * numWaysRightExpIsFalse + numWaysLeftExpIsFalse * numWaysRightExpIsTrue;
                } else if (operand.equals("^")) {
                    ways += numWaysLeftExpIsFalse * numWaysRightExpIsFalse + numWaysLeftExpIsTrue * numWaysRightExpIsTrue;
                }
            }
        }
        return ways;
    }

    public static int findUsingMemoization(String[] expression, int i, int j, boolean isTrue, int[][][] dp) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            if (isTrue) {
                if (expression[i].equals("T")) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                if (expression[i].equals("T")) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }
        if (dp[i][j][isTrue ? 1 : 0] != -1) {
            return dp[i][j][isTrue ? 1 : 0];
        }
        int ways = 0;
        for (int k = i + 1; k <= j - 1; k += 2) {
            int numWaysLeftExpIsFalse = findUsingMemoization(expression, i, k - 1, false, dp);
            int numWaysLeftExpIsTrue = findUsingMemoization(expression, i, k - 1, true, dp);
            int numWaysRightExpIsFalse = findUsingMemoization(expression, k + 1, j, false, dp);
            int numWaysRightExpIsTrue = findUsingMemoization(expression, k + 1, j, true, dp);

            String operand = expression[k];

            if (isTrue) {
                if (operand.equals("|")) {
                    ways += numWaysLeftExpIsTrue * numWaysRightExpIsTrue + numWaysLeftExpIsTrue * numWaysRightExpIsFalse + numWaysLeftExpIsFalse * numWaysRightExpIsTrue;
                } else if (operand.equals("&")) {
                    ways += numWaysLeftExpIsTrue * numWaysRightExpIsTrue;
                } else if (operand.equals("^")) {
                    ways += numWaysLeftExpIsTrue * numWaysRightExpIsFalse + numWaysLeftExpIsFalse * numWaysRightExpIsTrue;
                }
            } else {
                if (operand.equals("|")) {
                    ways += numWaysLeftExpIsFalse * numWaysRightExpIsFalse;
                } else if (operand.equals("&")) {
                    ways += numWaysLeftExpIsFalse * numWaysRightExpIsFalse + numWaysLeftExpIsTrue * numWaysRightExpIsFalse + numWaysLeftExpIsFalse * numWaysRightExpIsTrue;
                } else if (operand.equals("^")) {
                    ways += numWaysLeftExpIsFalse * numWaysRightExpIsFalse + numWaysLeftExpIsTrue * numWaysRightExpIsTrue;
                }
            }
        }
        dp[i][j][isTrue ? 1 : 0] = ways;
        return dp[i][j][isTrue ? 1 : 0];
    }

    public static int findUsingTabulation(String[] expression) {
        int[][][] dp = new int[expression.length][expression.length][2];
        for (int i = dp.length-1; i >= 0 ; i -= 2) {
            for (int j = 0; j < dp.length; j += 2) {
                if (i > j) {
                    continue;
                }
                if (i == j) {
                    if (expression[i].equals("T")) {
                        dp[i][j][1] = 1;
                    } else {
                        dp[i][j][1] = 0;
                    }
                    if (expression[i].equals("F")) {
                        dp[i][j][0] = 1;
                    } else {
                        dp[i][j][0] = 0;
                    }
                } else {
                    int numFalseWays = 0;
                    int numTrueWays = 0;
                    for (int k = i + 1; k <= j - 1; k += 2) {
                        int numWaysLeftExpIsFalse = dp[i][k - 1][0];
                        int numWaysLeftExpIsTrue = dp[i][k - 1][1];
                        int numWaysRightExpIsFalse = dp[k + 1][j][0];
                        int numWaysRightExpIsTrue = dp[k + 1][j][1];

                        String operand = expression[k];


                        if (operand.equals("|")) {
                            numTrueWays += numWaysLeftExpIsTrue * numWaysRightExpIsTrue + numWaysLeftExpIsTrue * numWaysRightExpIsFalse + numWaysLeftExpIsFalse * numWaysRightExpIsTrue;
                        } else if (operand.equals("&")) {
                            numTrueWays += numWaysLeftExpIsTrue * numWaysRightExpIsTrue;
                        } else if (operand.equals("^")) {
                            numTrueWays += numWaysLeftExpIsTrue * numWaysRightExpIsFalse + numWaysLeftExpIsFalse * numWaysRightExpIsTrue;
                        }

                        if (operand.equals("|")) {
                            numFalseWays += numWaysLeftExpIsFalse * numWaysRightExpIsFalse;
                        } else if (operand.equals("&")) {
                            numFalseWays += numWaysLeftExpIsFalse * numWaysRightExpIsFalse + numWaysLeftExpIsTrue * numWaysRightExpIsFalse + numWaysLeftExpIsFalse * numWaysRightExpIsTrue;
                        } else if (operand.equals("^")) {
                            numFalseWays += numWaysLeftExpIsFalse * numWaysRightExpIsFalse + numWaysLeftExpIsTrue * numWaysRightExpIsTrue;
                        }

                    }
                    dp[i][j][0] = numFalseWays;
                    dp[i][j][1] = numTrueWays;
                }
            }
        }
        return dp[0][dp.length - 1][1];
    }
}



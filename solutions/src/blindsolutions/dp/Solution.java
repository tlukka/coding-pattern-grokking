package blindsolutions.dp;

public class Solution {
    public static void main(String[] args) {
        Solution sl = new Solution();
        System.out.println(sl.claimStairsWithDp(5));
        System.out.println(sl.claimStairs(5));
        System.out.println(sl.coinExchange(new int[]{1,2,5}, 11));
        System.out.println(sl.coinExchange(new int[]{2}, 3));
        System.out.println(sl.coinExchange(new int[]{1}, 0));
    }

    public int claimStairsWithDp(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int claimStairs(int n) {
        if (n <= 2) return n;
        int first = 1, second = 2;
        for (int i = 3; i <= n; i++) {
            int temp = first + second;
            first = second;
            second = temp;
        }
        return second;
    }

    public int coinExchange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i - coins[j]] != -1)
                    min = Math.min(min, dp[i - coins[j]]);
            }
            dp[i] = (min == Integer.MAX_VALUE) ? -1 : 1 + min;
        }
        return dp[amount];
    }
}

class Solution {
    int[][] dp;
    int[] suffix;

    public int stoneGameII(int[] piles) {
        int n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n + 1];

        // Suffix sum
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return solve(piles, 0, 1);
    }

    private int solve(int[] piles, int i, int M) {

        if (i >= piles.length) {
            return 0;
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int maxStones = 0;

        for (int X = 1; X <= 2 * M && i + X <= piles.length; X++) {

            int opponent = solve(
                piles,
                i + X,
                Math.max(M, X)
            );

            int current = suffix[i] - opponent;

            maxStones = Math.max(maxStones, current);
        }

        dp[i][M] = maxStones;

        return maxStones;
    }
}
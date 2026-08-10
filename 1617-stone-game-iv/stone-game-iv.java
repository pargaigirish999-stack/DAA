class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];

        // dp[i] = true means current player can win with i stones

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j * j <= i; j++) {

                // If after removing j*j stones,
                // opponent is in a losing position
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
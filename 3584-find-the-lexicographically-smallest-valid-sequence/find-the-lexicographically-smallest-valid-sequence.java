class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        // last[j] = word1 me word2[j] ka last possible index
        int[] last = new int[m];

        for (int i = 0; i < m; i++) {
            last[i] = -1;
        }

        int i = n - 1;
        int j = m - 1;

        // Right se matching
        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
            i--;
        }

        int[] ans = new int[m];

        i = 0;
        j = 0;

        boolean mismatchUsed = false;

        // Left se lexicographically smallest answer
        while (i < n && j < m) {

            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;

            } else if (!mismatchUsed &&
                       (j == m - 1 || i < last[j + 1])) {

                ans[j] = i;
                j++;
                mismatchUsed = true;
            }

            i++;
        }

        if (j != m) {
            return new int[0];
        }

        return ans;
    }
}
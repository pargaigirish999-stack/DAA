import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        long[] cost = new long[n - x + 1];

        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        long sumLeft = 0, sumRight = 0;
        int sizeLeft = 0, sizeRight = 0;

        for (int i = 0; i < n; i++) {
            int val = nums[i];

            if (sizeLeft == 0 || val <= left.lastKey()) {
                left.put(val, left.getOrDefault(val, 0) + 1);
                sumLeft += val;
                sizeLeft++;
            } else {
                right.put(val, right.getOrDefault(val, 0) + 1);
                sumRight += val;
                sizeRight++;
            }

            while (sizeLeft > (x + 1) / 2) {
                int v = left.lastKey();
                remove(left, v);
                sumLeft -= v;
                sizeLeft--;

                right.put(v, right.getOrDefault(v, 0) + 1);
                sumRight += v;
                sizeRight++;
            }

            while (sizeLeft < (x + 1) / 2 && sizeRight > 0) {
                int v = right.firstKey();
                remove(right, v);
                sumRight -= v;
                sizeRight--;

                left.put(v, left.getOrDefault(v, 0) + 1);
                sumLeft += v;
                sizeLeft++;
            }

            if (i >= x - 1) {
                int median = left.lastKey();

                cost[i - x + 1] =
                    (long) median * sizeLeft - sumLeft
                    + sumRight - (long) median * sizeRight;

                int old = nums[i - x + 1];

                if (left.containsKey(old)) {
                    remove(left, old);
                    sumLeft -= old;
                    sizeLeft--;
                } else {
                    remove(right, old);
                    sumRight -= old;
                    sizeRight--;
                }

                while (sizeLeft > (x + 1) / 2) {
                    int v = left.lastKey();
                    remove(left, v);
                    sumLeft -= v;
                    sizeLeft--;

                    right.put(v, right.getOrDefault(v, 0) + 1);
                    sumRight += v;
                    sizeRight++;
                }

                while (sizeLeft < (x + 1) / 2 && sizeRight > 0) {
                    int v = right.firstKey();
                    remove(right, v);
                    sumRight -= v;
                    sizeRight--;

                    left.put(v, left.getOrDefault(v, 0) + 1);
                    sumLeft += v;
                    sizeLeft++;
                }
            }
        }

        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][n + 1];

        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], INF);
        }

        Arrays.fill(dp[0], 0);

        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                dp[j][i] = dp[j][i - 1];

                if (i >= x && dp[j - 1][i - x] != INF) {
                    dp[j][i] = Math.min(
                        dp[j][i],
                        dp[j - 1][i - x] + cost[i - x]
                    );
                }
            }
        }

        return dp[k][n];
    }

    private void remove(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);

        if (count == 1) {
            map.remove(val);
        } else {
            map.put(val, count - 1);
        }
    }
}
import java.util.*;

class Solution {

    record Interval(int left, int right, int weight, int index) {}
    record State(long score, List<Integer> list) {}

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        List<Interval> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            ));
        }

        // Sort by starting point
        arr.sort(Comparator.comparingInt(Interval::left));

        State[][] dp = new State[n][5];

        State ans = solve(arr, dp, 0, 4);

        return ans.list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private State solve(List<Interval> arr, State[][] dp,
                        int i, int k) {

        if (i == arr.size() || k == 0) {
            return new State(0, new ArrayList<>());
        }

        if (dp[i][k] != null) {
            return dp[i][k];
        }

        // Option 1: Skip current interval
        State skip = solve(arr, dp, i + 1, k);

        // Option 2: Take current interval
        Interval cur = arr.get(i);

        int next = findNext(arr, i + 1, cur.right);

        State nextState = solve(arr, dp, next, k - 1);

        List<Integer> selected = new ArrayList<>(nextState.list);
        selected.add(cur.index);

        Collections.sort(selected);

        State take = new State(
            cur.weight + nextState.score,
            selected
        );

        // Choose better score
        // If score same, choose lexicographically smaller indices
        if (take.score > skip.score) {
            dp[i][k] = take;
        } 
        else if (take.score < skip.score) {
            dp[i][k] = skip;
        } 
        else {
            dp[i][k] = isSmaller(take.list, skip.list)
                    ? take
                    : skip;
        }

        return dp[i][k];
    }

    // First interval whose start > current end
    private int findNext(List<Interval> arr, int start, int end) {

        int low = start;
        int high = arr.size();

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr.get(mid).left > end) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    // Lexicographical comparison
    private boolean isSmaller(List<Integer> a, List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }
}
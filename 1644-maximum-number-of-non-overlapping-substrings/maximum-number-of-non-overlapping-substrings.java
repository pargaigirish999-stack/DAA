import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Find first and last occurrence
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';

            if (first[ch] == -1) {
                first[ch] = i;
            }

            last[ch] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Create valid intervals
        for (int i = 0; i < 26; i++) {

            if (first[i] == -1)
                continue;

            int l = first[i];
            int r = last[i];

            boolean valid = true;

            for (int j = l; j <= r; j++) {

                int ch = s.charAt(j) - 'a';

                if (first[ch] < l) {
                    valid = false;
                    break;
                }

                r = Math.max(r, last[ch]);
            }

            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> a[1] - b[1]);

        List<String> ans = new ArrayList<>();

        int end = -1;

        for (int[] interval : intervals) {

            if (interval[0] > end) {
                ans.add(s.substring(interval[0], interval[1] + 1));
                end = interval[1];
            }
        }

        return ans;
    }
}
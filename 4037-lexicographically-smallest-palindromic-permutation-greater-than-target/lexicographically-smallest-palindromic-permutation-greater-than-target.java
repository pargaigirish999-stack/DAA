class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int[] freq = new int[26];

        // Count characters
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Check palindrome possibility
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                if (middle != 0) {
                    return "";
                }
                middle = (char) ('a' + i);
                freq[i]--;
            }
        }

        // We only need half of palindrome
        int half = n / 2;

        // Try to keep target's left half same
        int[] cnt = freq.clone();

        for (int i = 0; i < half; i++) {
            cnt[target.charAt(i) - 'a'] -= 2;
        }

        // If target's left half is possible,
        // check whether its palindrome is already > target
        boolean possible = true;

        for (int x : cnt) {
            if (x < 0) {
                possible = false;
                break;
            }
        }

        if (possible) {
            String left = target.substring(0, half);

            StringBuilder right = new StringBuilder(left);
            right.reverse();

            String candidate;

            if (n % 2 == 1) {
                candidate = left + middle + right;
            } else {
                candidate = left + right;
            }

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        // Find the rightmost position where
        // we can increase target's character
        for (int i = half - 1; i >= 0; i--) {

            int ch = target.charAt(i) - 'a';

            // Put back the pair used at position i
            cnt[ch] += 2;

            // Check if current prefix is possible
            boolean ok = true;

            for (int x : cnt) {
                if (x < 0) {
                    ok = false;
                    break;
                }
            }

            if (!ok) {
                continue;
            }

            // Find smallest character greater than target[i]
            for (int next = ch + 1; next < 26; next++) {

                if (cnt[next] >= 2) {

                    cnt[next] -= 2;

                    StringBuilder left = new StringBuilder();

                    // Keep target prefix
                    left.append(target, 0, i);

                    // Increase current character
                    left.append((char) ('a' + next));

                    // Fill remaining half with smallest characters
                    for (int c = 0; c < 26; c++) {
                        int pairs = cnt[c] / 2;

                        while (pairs-- > 0) {
                            left.append((char) ('a' + c));
                        }
                    }

                    String leftPart = left.toString();

                    StringBuilder right = new StringBuilder(leftPart);
                    right.reverse();

                    if (n % 2 == 1) {
                        return leftPart + middle + right;
                    } else {
                        return leftPart + right;
                    }
                }
            }
        }

        return "";
    }
}
import java.util.*;

class Solution {

    int idx = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> result = solve(expression);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    // Parse one expression
    Set<String> solve(String s) {
        Set<String> result = new HashSet<>();

        while (idx < s.length() && s.charAt(idx) != '}') {

            Set<String> current;

            // Expression inside {}
            if (s.charAt(idx) == '{') {
                idx++; // skip '{'

                current = solve(s);

                idx++; // skip '}'
            }

            // Single letter
            else {
                current = new HashSet<>();
                current.add(String.valueOf(s.charAt(idx)));
                idx++;
            }

            // Concatenate current with result
            if (result.isEmpty()) {
                result = current;
            } else {
                Set<String> temp = new HashSet<>();

                for (String a : result) {
                    for (String b : current) {
                        temp.add(a + b);
                    }
                }

                result = temp;
            }

            // Union after comma
            if (idx < s.length() && s.charAt(idx) == ',') {
                idx++;
                
                Set<String> next = solve(s);

                result.addAll(next);
                break;
            }
        }

        return result;
    }
}
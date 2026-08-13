class Solution {

    class Node {
        char left, right;
        int prefix, suffix, best, len;

        Node() {}

        Node(char c) {
            left = right = c;
            prefix = suffix = best = len = 1;
        }
    }

    Node[] tree;
    String s;

    void build(int idx, int l, int r) {
        if (l == r) {
            tree[idx] = new Node(s.charAt(l));
            return;
        }

        int mid = (l + r) / 2;

        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);

        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }

    Node merge(Node a, Node b) {

        Node res = new Node();

        res.len = a.len + b.len;
        res.left = a.left;
        res.right = b.right;

        res.prefix = a.prefix;
        if (a.prefix == a.len && a.right == b.left) {
            res.prefix = a.len + b.prefix;
        }

        res.suffix = b.suffix;
        if (b.suffix == b.len && a.right == b.left) {
            res.suffix = b.len + a.suffix;
        }

        res.best = Math.max(a.best, b.best);

        if (a.right == b.left) {
            res.best = Math.max(res.best, a.suffix + b.prefix);
        }

        return res;
    }

    void update(int idx, int l, int r, int pos, char c) {

        if (l == r) {
            tree[idx] = new Node(c);
            return;
        }

        int mid = (l + r) / 2;

        if (pos <= mid) {
            update(idx * 2, l, mid, pos, c);
        } else {
            update(idx * 2 + 1, mid + 1, r, pos, c);
        }

        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }

    public int[] longestRepeating(String s, String queryCharacters,
                                   int[] queryIndices) {

        this.s = s;

        int n = s.length();

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int q = queryIndices.length;

        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {

            int pos = queryIndices[i];
            char c = queryCharacters.charAt(i);

            update(1, 0, n - 1, pos, c);

            ans[i] = tree[1].best;
        }

        return ans;
    }
}
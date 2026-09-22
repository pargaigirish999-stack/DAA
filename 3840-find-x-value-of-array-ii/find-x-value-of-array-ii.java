class Solution {

    int k;
    int[][] cnt;
    int[] prod;

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.k = k;

        int n = nums.length;

        cnt = new int[4 * n][k];
        prod = new int[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Persistent update
            update(1, 0, n - 1, index, value);

            Node res = query(1, 0, n - 1, start, n - 1);

            ans[q] = res.cnt[x];
        }

        return ans;
    }

    // Build segment tree
    void build(int node, int l, int r, int[] nums) {

        if (l == r) {

            int rem = nums[l] % k;

            prod[node] = rem;
            cnt[node][rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        pull(node);
    }

    // Update one element
    void update(int node, int l, int r, int index, int value) {

        if (l == r) {

            int rem = value % k;

            prod[node] = rem;

            for (int i = 0; i < k; i++) {
                cnt[node][i] = 0;
            }

            cnt[node][rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        pull(node);
    }

    // Merge left and right children
    void pull(int node) {

        int left = node * 2;
        int right = node * 2 + 1;

        prod[node] = (prod[left] * prod[right]) % k;

        for (int i = 0; i < k; i++) {
            cnt[node][i] = cnt[left][i];
        }

        for (int r = 0; r < k; r++) {

            int newRem = (prod[left] * r) % k;

            cnt[node][newRem] += cnt[right][r];
        }
    }

    Node query(int node, int l, int r, int ql, int qr) {

        if (ql <= l && r <= qr) {

            return new Node(prod[node], cnt[node]);
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    Node merge(Node left, Node right) {

        int newProd = (left.prod * right.prod) % k;

        int[] newCnt = new int[k];

        // Prefixes completely inside left
        for (int i = 0; i < k; i++) {
            newCnt[i] += left.cnt[i];
        }

        // Prefixes containing all left + prefix of right
        for (int r = 0; r < k; r++) {

            int rem = (left.prod * r) % k;

            newCnt[rem] += right.cnt[r];
        }

        return new Node(newProd, newCnt);
    }

    static class Node {

        int prod;
        int[] cnt;

        Node(int prod, int[] cnt) {
            this.prod = prod;
            this.cnt = cnt.clone();
        }
    }
}
import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    private List<Integer> pos = new ArrayList<>();
    private List<Integer> digit = new ArrayList<>();
    private long[] pow10;
    private long[] tree;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        // Store positions and values of non-zero digits
        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                pos.add(i);
                digit.add(d);
            }
        }

        int m = digit.size();

        pow10 = new long[m + 1];
        pow10[0] = 1;
        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        tree = new long[Math.max(1, 4 * m)];
        if (m > 0) {
            build(1, 0, m - 1);
        }

        long[] prefix = new long[m + 1];
        for (int i = 0; i < m; i++) {
            prefix[i + 1] = prefix[i] + digit.get(i);
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int left = lowerBound(pos, l);
            int right = upperBound(pos, r) - 1;

            if (left > right) {
                ans[i] = 0;
                continue;
            }

            long x = query(1, 0, m - 1, left, right);
            long sum = prefix[right + 1] - prefix[left];

            ans[i] = (int) ((x * (sum % MOD)) % MOD);
        }

        return ans;
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            tree[node] = digit.get(l);
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        int rightLen = r - mid;
        tree[node] = (tree[node * 2] * pow10[rightLen] + tree[node * 2 + 1]) % MOD;
    }

    private long query(int node, int l, int r, int ql, int qr) {
        if (ql == l && qr == r) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        long leftVal = query(node * 2, l, mid, ql, mid);
        long rightVal = query(node * 2 + 1, mid + 1, r, mid + 1, qr);

        int rightLength = qr - mid;

        return (leftVal * pow10[rightLength] + rightVal) % MOD;
    }

    private int lowerBound(List<Integer> arr, int target) {
        int l = 0, r = arr.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr.get(mid) >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int upperBound(List<Integer> arr, int target) {
        int l = 0, r = arr.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr.get(mid) > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
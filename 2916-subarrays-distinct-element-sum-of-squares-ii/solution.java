// 238 ms | 93.3 MB
class Solution {
    public int sumCounts(int[] nums) {
        int mod = 1_000_000_007;
        int n = nums.length;

        int[] lastSeen = new int[100001];
        java.util.Arrays.fill(lastSeen, -1);

        long answer = 0;
        long currentSum = 0;

        SegmentTree tree = new SegmentTree(n, mod);

        for (int i = 0; i < n; i++) {
            int value = nums[i];

            int previous = lastSeen[value];

            tree.add(previous + 1, i, 1);

            currentSum = tree.getSumSquares();
            answer = (answer + currentSum) % mod;

            lastSeen[value] = i;
        }

        return (int) answer;
    }

    static class SegmentTree {
        int n;
        int mod;
        long[] sum;
        long[] squares;
        long[] lazy;

        SegmentTree(int n, int mod) {
            this.n = n;
            this.mod = mod;

            sum = new long[4 * n];
            squares = new long[4 * n];
            lazy = new long[4 * n];
        }

        void add(int left, int right, long value) {
            if (left > right) {
                return;
            }

            update(1, 0, n - 1, left, right, value);
        }

        void update(int node, int start, int end,
                    int left, int right, long value) {
            if (left <= start && end <= right) {
                apply(node, start, end, value);
                return;
            }

            pushDown(node, start, end);

            int mid = (start + end) / 2;

            if (left <= mid) {
                update(node * 2, start, mid, left, right, value);
            }

            if (right > mid) {
                update(node * 2 + 1, mid + 1, end,
                       left, right, value);
            }

            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % mod;
            squares[node] =
                (squares[node * 2] + squares[node * 2 + 1]) % mod;
        }

        void apply(int node, int start, int end, long value) {
            long length = end - start + 1L;

            squares[node] = (
                squares[node]
                + 2L * value % mod * sum[node] % mod
                + length * value % mod * value % mod
            ) % mod;

            sum[node] = (sum[node] + length * value) % mod;
            lazy[node] = (lazy[node] + value) % mod;
        }

        void pushDown(int node, int start, int end) {
            if (lazy[node] == 0 || start == end) {
                return;
            }

            int mid = (start + end) / 2;

            apply(node * 2, start, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, end, lazy[node]);

            lazy[node] = 0;
        }

        long getSumSquares() {
            return squares[1];
        }
    }
}
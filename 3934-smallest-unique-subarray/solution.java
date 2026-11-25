// 555 ms | 100.6 MB
class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] rank = nums.clone();
        int[] sa = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
        }

        int k = 1;

        while (k < n) {
            long[] keys = new long[n];

            for (int i = 0; i < n; i++) {
                int second = (i + k < n) ? rank[i + k] + 1 : 0;

                keys[i] = ((long) (rank[i] + 1) << 42)
                        | ((long) second << 21)
                        | i;
            }

            Arrays.sort(keys);

            int[] newRank = new int[n];

            for (int i = 0; i < n; i++) {
                sa[i] = (int) (keys[i] & ((1 << 21) - 1));
            }

            int classes = 0;
            newRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int curr = sa[i];
                int prev = sa[i - 1];

                int currSecond = (curr + k < n) ? rank[curr + k] : -1;
                int prevSecond = (prev + k < n) ? rank[prev + k] : -1;

                if (rank[curr] != rank[prev] ||
                    currSecond != prevSecond) {
                    classes++;
                }

                newRank[curr] = classes;
            }

            rank = newRank;

            if (classes == n - 1) {
                break;
            }

            k *= 2;
        }

        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[sa[i]] = i;
        }

        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = pos[i];

            if (r == 0) {
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) {
                h--;
            }
        }

        int ans = n;

        for (int r = 0; r < n; r++) {
            int maxLcp = lcp[r];

            if (r + 1 < n) {
                maxLcp = Math.max(maxLcp, lcp[r + 1]);
            }

            int start = sa[r];

            if (maxLcp < n - start) {
                ans = Math.min(ans, maxLcp + 1);
            }
        }

        return ans;
    }
}
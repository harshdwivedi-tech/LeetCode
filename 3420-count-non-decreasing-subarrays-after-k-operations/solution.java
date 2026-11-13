// 738 ms | 210.9 MB
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
 int n = nums.length;
        int log = 1;

        while ((1 << log) <= n) {
            log++;
        }

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int[] next = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }

            next[i] = (top == -1) ? -1 : stack[top];
            stack[++top] = i;
        }

        int[][] up = new int[log][n];
        long[][] cost = new long[log][n];

        for (int i = 0; i < n; i++) {
            int end = (next[i] == -1) ? n : next[i];

            up[0][i] = next[i];

            cost[0][i] =
                (long) nums[i] * (end - i)
                - (prefix[end] - prefix[i]);
        }

        for (int p = 1; p < log; p++) {
            for (int i = 0; i < n; i++) {
                int mid = up[p - 1][i];

                if (mid == -1) {
                    up[p][i] = -1;
                    cost[p][i] = cost[p - 1][i];
                } else {
                    up[p][i] = up[p - 1][mid];

                    cost[p][i] =
                        cost[p - 1][i] + cost[p - 1][mid];
                }
            }
        }

        long answer = 0;

        for (int right = 0; right < n; right++) {
            int low = 0;
            int high = right;

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (getCost(mid, right, nums, prefix, up, cost) <= k) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            answer += right - low + 1;
        }

        return answer;
    }

    private long getCost(
        int left,
        int right,
        int[] nums,
        long[] prefix,
        int[][] up,
        long[][] cost
    ) {
        long operations = 0;
        int current = left;

        for (int p = up.length - 1; p >= 0; p--) {
            int nextIndex = up[p][current];

            if (nextIndex != -1 && nextIndex <= right) {
                operations += cost[p][current];
                current = nextIndex;
            }
        }
        operations +=
            (long) nums[current] * (right - current + 1)
            - (prefix[right + 1] - prefix[current]);

        return operations;
    }

    private long getCost(int[] nums, int left, int right) {
        long operations = 0;
        int maxValue = nums[left];

        for (int i = left + 1; i <= right; i++) {
            maxValue = Math.max(maxValue, nums[i]);
            operations += maxValue - nums[i];
        }

        return operations;
    }
}
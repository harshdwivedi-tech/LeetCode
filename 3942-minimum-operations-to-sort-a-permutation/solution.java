// 10 ms | 94.3 MB

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        boolean forward = true;
        boolean backward = true;

        for (int i = 1; i < n; i++) {
            int prevForward = (zero + i - 1) % n;
            int currForward = (zero + i) % n;

            if (nums[currForward] != nums[prevForward] + 1) {
                forward = false;
            }

            int prevBackward = (zero - i + 1 + n) % n;
            int currBackward = (zero - i + n) % n;

            if (nums[currBackward] != nums[prevBackward] + 1) {
                backward = false;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (forward) {
            ans = Math.min(ans, zero);
            ans = Math.min(ans, n - zero + 2);
        }

        if (backward) {
            ans = Math.min(ans, zero + 2);
            ans = Math.min(ans, n - zero);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}

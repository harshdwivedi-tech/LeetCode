// 3 ms | 108.7 MB
class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int k = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                if (k == -1) {
                    k = nums[i] & i;
                } else {
                    k = k & nums[i] & i;
                }
            }
        }
        if (k == -1) {
            return 0;
        }

        return k;
    }
}

// 114 ms | 80.2 MB
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int answer = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean possible = true;

            for (int start = 0; start < n; start += k) {
                int drops = 0;

                for (int j = start; j < start + k; j++) {
                    int next = (j + 1 == start + k) ? start : j + 1;

                    if (nums[j] > nums[next]) {
                        drops++;
                    }
                }

                if (drops > 1) {
                    possible = false;
                    break;
                }

                // Check that this block contains the correct sorted elements.
                int[] block = Arrays.copyOfRange(nums, start, start + k);
                int[] target = Arrays.copyOfRange(sorted, start, start + k);

                Arrays.sort(block);

                if (!Arrays.equals(block, target)) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                answer += k;
            }
        }

        return answer;
    }
}
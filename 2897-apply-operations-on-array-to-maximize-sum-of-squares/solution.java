// 66 ms | 80.2 MB
class Solution {
    public int maxSum(List<Integer> nums, int k) {
         int mod = 1_000_000_007;
        int[] bitCount = new int[32];

        for (int num : nums) {
            for (int bit = 0; bit < 32; bit++) {
                if ((num & (1 << bit)) != 0) {
                    bitCount[bit]++;
                }            }
}

        long answer = 0;
        for (int i = 0; i < k; i++) {
            long value = 0;

            for (int bit = 0; bit < 32; bit++) {
                if (bitCount[bit] > 0) {
                    value |= (1L << bit);
                    bitCount[bit]--;
                }
            }

            answer = (answer + value * value) % mod;
        }

        return (int) answer;
    }
}
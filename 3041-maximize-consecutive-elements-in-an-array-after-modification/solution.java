// 73 ms | 74.5 MB
class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        int[] dp = new int[1000003];
        int ans = 1;

        for (int num : nums) {
            dp[num + 1] = dp[num] + 1;
            dp[num] = Math.max(dp[num], dp[num - 1] + 1);

            ans = Math.max(ans, Math.max(dp[num], dp[num + 1]));
        }

        return ans;
    }
}
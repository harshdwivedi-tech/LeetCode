// 43 ms | 47.2 MB
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];

        for(int i = 1; i < n; i++){
            dp[i] = n;
        }

        dp[0] = 0;

        for(int i = 0; i < n; i++){

                for(int j = 1; j <= nums[i]; j++){
                    int next = i + j;

                    if(next < n){
                        dp[next] = Math.min(dp[next], dp[i] + 1);
                    }
                }
        } 
        return dp[n-1];
    }
}
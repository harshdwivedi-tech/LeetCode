// 66 ms | 169.2 MB
class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] combinations;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        combinations = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            combinations[i][0] = 1;
            combinations[i][i] = 1;

            for (int j = 1; j < i; j++) {
                combinations[i][j] =
                    (combinations[i - 1][j - 1]
                    + combinations[i - 1][j]) % MOD;
            }
        }

        List<Integer> values = new ArrayList<>();
        for (int num : nums) {
            values.add(num);
        }

        return (int) ((countWays(values) - 1 + MOD) % MOD);
    }

    private long countWays(List<Integer> nums) {
        int n = nums.size();

        if (n <= 2) {
            return 1;
        }
        int root = nums.get(0);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        
        for (int i = 1; i < n; i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }

        long leftWays = countWays(left);
        long rightWays = countWays(right);

        long waysToArrange = combinations[n - 1][left.size()];

        return (((waysToArrange * leftWays) % MOD) * rightWays) % MOD;
    }
}

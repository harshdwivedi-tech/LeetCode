// 7 ms | 81.4 MB
class Solution {
    public int numOfSubarrays(int[] arr) {
                int mod = 1000000007;

        int prefixSum = 0;
        int evenCount = 1;
        int oddCount = 0;

        int answer = 0;

        for (int num : arr) {

            prefixSum += num;

            if (prefixSum % 2 == 0) {

                answer = (answer + oddCount) % mod;
                evenCount++;

            } else {

                answer = (answer + evenCount) % mod;
                oddCount++;
            }
        }

        return answer;
    }
}
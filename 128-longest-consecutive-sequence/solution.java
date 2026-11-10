// 38 ms | 95.1 MB
class Solution {
    public int longestConsecutive(int[] nums) {
          HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int maxLength = 0;
        for (int num : set) {

            if (!set.contains(num - 1)) {

                int currentNum = num;
                int count = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    count++;
                }

                maxLength = Math.max(maxLength, count);
            }
        }

        return maxLength;
    }
}
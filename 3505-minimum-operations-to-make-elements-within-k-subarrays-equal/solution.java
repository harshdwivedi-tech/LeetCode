// 1020 ms | 148.7 MB
import java.util.*;

class Solution {

    class MedianCost {

        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;

        void add(int num) {

            if (left.isEmpty() || num <= left.lastKey()) {
                left.put(num, left.getOrDefault(num, 0) + 1);
                leftSize++;
                leftSum += num;
            } else {
                right.put(num, right.getOrDefault(num, 0) + 1);
                rightSize++;
                rightSum += num;
            }

            balance();
        }

        void remove(int num) {

            if (left.containsKey(num)) {
                removeFromMap(left, num);
                leftSize--;
                leftSum -= num;
            } else {
                removeFromMap(right, num);
                rightSize--;
                rightSum -= num;
            }

            balance();
        }

        void balance() {

            while (leftSize > rightSize + 1) {

                int num = left.lastKey();

                removeFromMap(left, num);
                leftSize--;
                leftSum -= num;

                right.put(num, right.getOrDefault(num, 0) + 1);
                rightSize++;
                rightSum += num;
            }

            while (leftSize < rightSize) {

                int num = right.firstKey();

                removeFromMap(right, num);
                rightSize--;
                rightSum -= num;

                left.put(num, left.getOrDefault(num, 0) + 1);
                leftSize++;
                leftSum += num;
            }
        }

        void removeFromMap(TreeMap<Integer, Integer> map, int num) {

            int count = map.get(num);

            if (count == 1) {
                map.remove(num);
            } else {
                map.put(num, count - 1);
            }
        }

        long getCost() {

            int median = left.lastKey();

            long leftCost = (long) median * leftSize - leftSum;
            long rightCost = rightSum - (long) median * rightSize;

            return leftCost + rightCost;
        }

    } // MedianCost class ends here

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        MedianCost window = new MedianCost();

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        cost[0] = window.getCost();

        for (int i = x; i < n; i++) {

            window.remove(nums[i - x]);
            window.add(nums[i]);

            cost[i - x + 1] = window.getCost();
        }

        long INF = Long.MAX_VALUE / 2;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                curr[i] = curr[i - 1];

                if (i >= x && prev[i - x] != INF) {

                    long newCost = prev[i - x] + cost[i - x];

                    curr[i] = Math.min(curr[i], newCost);
                }
            }

            prev = curr;
            curr = new long[n + 1];
        }

        return prev[n];
    }
}
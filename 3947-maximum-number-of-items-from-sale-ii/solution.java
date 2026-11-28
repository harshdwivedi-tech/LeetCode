// 47 ms | 272.6 MB
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;

        int[] freq = new int[n + 1];

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            freq[factor]++;
            maxFactor = Math.max(maxFactor, factor);
            minPrice = Math.min(minPrice, price);
        }

        for (int i = 1; i <= maxFactor; i++) {
            for (int j = i * 2; j <= maxFactor; j += i) {
                freq[i] += freq[j];
            }
        }

        TreeMap<Integer, Long> bonus = new TreeMap<>();

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            int gain = freq[factor] - 1;

            if (gain > 0 && (long) price < 2L * minPrice) {
                bonus.put(price, bonus.getOrDefault(price, 0L) + gain);
            }
        }

        long answer = 0;
        long remaining = budget;

        for (Map.Entry<Integer, Long> entry : bonus.entrySet()) {
            int price = entry.getKey();
            long count = entry.getValue();

            long buy = Math.min(count, remaining / price);

            answer += buy * 2;
            remaining -= buy * price;
        }

        answer += remaining / minPrice;

        return (int) answer;
    }
}
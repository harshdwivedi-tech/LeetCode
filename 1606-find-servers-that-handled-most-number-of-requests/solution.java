// 129 ms | 100.9 MB
class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {

            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int[] server = busy.poll();
                available.add(server[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            int serverId = i % k;

            Integer selected = available.ceiling(serverId);

            if (selected == null) {
                selected = available.first();
            }

            available.remove(selected);

            count[selected]++;

            int endTime = arrival[i] + load[i];

            busy.offer(new int[]{endTime, selected});
        }

        int maxRequests = 0;

        for (int requests : count) {
            maxRequests = Math.max(maxRequests, requests);
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}
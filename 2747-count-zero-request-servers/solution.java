// 49 ms | 149.9 MB
class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        int[][] sortedQueries = new int[queries.length][2];

        for (int i = 0; i < queries.length; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(sortedQueries, (a, b) -> a[0] - b[0]);

        int[] answer = new int[queries.length];

        Map<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;

        for (int i = 0; i < queries.length; i++) {

            int currentTime = sortedQueries[i][0];

            int startTime = currentTime - x;

            while (right < logs.length && logs[right][1] <= currentTime) {

                int serverId = logs[right][0];

                map.put(serverId, map.getOrDefault(serverId, 0) + 1);

                right++;
            }

            while (left < right && logs[left][1] < startTime) {

                int serverId = logs[left][0];

                map.put(serverId, map.get(serverId) - 1);

                if (map.get(serverId) == 0) {
                    map.remove(serverId);
                }

                left++;
            }

            answer[sortedQueries[i][1]] = n - map.size();
        }

        return answer;
    }
}

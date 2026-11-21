// 195 ms | 296.7 MB
class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
    int n = parent.length;

        List<List<Integer>> tree = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            tree.get(parent[i]).add(i);
        }

        int[] newParent = parent.clone();
        int[] lastSeen = new int[26];

        Arrays.fill(lastSeen, -1);

        dfs(0, tree, s, lastSeen, newParent);

        List<List<Integer>> updatedTree = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            updatedTree.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            updatedTree.get(newParent[i]).add(i);
        }

        int[] answer = new int[n];

        calculateSize(0, updatedTree, answer);

        return answer;
    }

    private void dfs(int node, List<List<Integer>> tree,
                     String s, int[] lastSeen, int[] newParent) {

        int ch = s.charAt(node) - 'a';
        int previous = lastSeen[ch];

        if (previous != -1) {
            newParent[node] = previous;
        }

        lastSeen[ch] = node;

        for (int child : tree.get(node)) {
            dfs(child, tree, s, lastSeen, newParent);
        }

        lastSeen[ch] = previous;
    }

    private int calculateSize(int node, List<List<Integer>> tree,
                              int[] answer) {

        int size = 1;

        for (int child : tree.get(node)) {
            size += calculateSize(child, tree, answer);
        }

        answer[node] = size;

        return size;
    }
}
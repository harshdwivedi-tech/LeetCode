// 183 ms | 200.1 MB
class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] size = new int[n];
        int[] heavy = new int[n];
        int[] head = new int[n];
        int[] pos = new int[n];
        int[] order = new int[n];

        Arrays.fill(parent, -1);
        Arrays.fill(heavy, -1);

        int[] stack = new int[n];
        int top = 0;
        int count = 0;

        stack[top++] = 0;

        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for (int v : graph.get(u)) {
                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            size[u] = 1;
            int largest = 0;

            for (int v : graph.get(u)) {
                if (parent[v] == u) {
                    size[u] += size[v];

                    if (size[v] > largest) {
                        largest = size[v];
                        heavy[u] = v;
                    }
                }
            }
        }

        int[] chainNodes = new int[n];
        int[] chainHeads = new int[n];
        top = 0;
        int timer = 0;

        chainNodes[top] = 0;
        chainHeads[top++] = 0;

        while (top > 0) {
            int start = chainNodes[--top];
            int chainHead = chainHeads[top];

            int u = start;

            while (u != -1) {
                head[u] = chainHead;
                pos[u] = timer++;

                for (int v : graph.get(u)) {
                    if (parent[v] == u && v != heavy[u]) {
                        chainNodes[top] = v;
                        chainHeads[top++] = v;
                    }
                }

                u = heavy[u];
            }
        }

        int[] base = new int[n];
        char[] chars = s.toCharArray();

        for (int i = 0; i < n; i++) {
            base[pos[i]] = 1 << (chars[i] - 'a');
        }

        SegmentTree seg = new SegmentTree(base);
        List<Boolean> result = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");
            int u = Integer.parseInt(parts[1]);

            if (parts[0].equals("update")) {
                char c = parts[2].charAt(0);
                chars[u] = c;
                seg.update(pos[u], 1 << (c - 'a'));
            } else {
                int v = Integer.parseInt(parts[2]);
                int mask = 0;

                while (head[u] != head[v]) {
                    if (depth[head[u]] < depth[head[v]]) {
                        int temp = u;
                        u = v;
                        v = temp;
                    }

                    mask ^= seg.query(pos[head[u]], pos[u]);
                    u = parent[head[u]];
                }

                if (depth[u] > depth[v]) {
                    int temp = u;
                    u = v;
                    v = temp;
                }

                mask ^= seg.query(pos[u], pos[v]);

                result.add((mask & (mask - 1)) == 0);
            }
        }

        return result;
    }

    class SegmentTree {
        int[] tree;
        int size;

        SegmentTree(int[] arr) {
            size = arr.length;
            tree = new int[4 * size];
            build(1, 0, size - 1, arr);
        }

        void build(int node, int left, int right, int[] arr) {
            if (left == right) {
                tree[node] = arr[left];
                return;
            }

            int mid = left + (right - left) / 2;

            build(node * 2, left, mid, arr);
            build(node * 2 + 1, mid + 1, right, arr);

            tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }

        void update(int index, int value) {
            update(1, 0, size - 1, index, value);
        }

        void update(int node, int left, int right, int index, int value) {
            if (left == right) {
                tree[node] = value;
                return;
            }

            int mid = left + (right - left) / 2;

            if (index <= mid) {
                update(node * 2, left, mid, index, value);
            } else {
                update(node * 2 + 1, mid + 1, right, index, value);
            }

            tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }

        int query(int ql, int qr) {
            return query(1, 0, size - 1, ql, qr);
        }

        int query(int node, int left, int right, int ql, int qr) {
            if (ql <= left && right <= qr) {
                return tree[node];
            }

            if (right < ql || left > qr) {
                return 0;
            }

            int mid = left + (right - left) / 2;

            return query(node * 2, left, mid, ql, qr)
                    ^ query(node * 2 + 1, mid + 1, right, ql, qr);
        }
    }
}

// 15 ms | 58.3 MB
class LRUCache {

      class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    HashMap<Integer, Node> map;
    Node head, tail;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();

        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);

        remove(node);
        add(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;

            remove(node);
            add(node);
        } else {
            Node node = new Node(key, value);

            map.put(key, node);
            add(node);

            if (map.size() > capacity) {
                Node lru = head.next;
                remove(lru);
                map.remove(lru.key);
            }
        }
    }

    private void add(Node node) {
        Node last = tail.prev;

        last.next = node;
        node.prev = last;

        node.next = tail;
        tail.prev = node;
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}


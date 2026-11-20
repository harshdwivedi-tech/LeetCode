// 7 ms | 45.8 MB
public class Solution {
    public boolean hasCycle(ListNode head){
                HashSet<ListNode> set = new HashSet<>();

        ListNode current = head;

        while (current != null) {
            if (set.contains(current)) {
                return true;
            }

            set.add(current);
            current = current.next;
        }
        return false;
    }
}
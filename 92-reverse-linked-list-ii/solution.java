// 0 ms | 42.5 MB
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
                ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        ListNode current = prev.next;
        ListNode next = null;
        ListNode last = prev.next;

        for (int i = left; i <= right; i++) {
            next = current.next;
            current.next = prev.next;
            prev.next = current;
            current = next;
        }

        last.next = current;

        return dummy.next;
    }
}
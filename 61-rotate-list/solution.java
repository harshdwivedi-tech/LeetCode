// 0 ms | 43.7 MB
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
                if (head == null || head.next == null) {
            return head;
        }

        int length = 1;
        ListNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
            length++;
        }

        k = k % length;

        if (k == 0) {
            return head;
        }

        temp.next = head;

        int steps = length - k;
        ListNode newLast = head;

        for (int i = 1; i < steps; i++) {
            newLast = newLast.next;
        }

        ListNode newHead = newLast.next;
        newLast.next = null;

        return newHead;
    }
}
// 0 ms | 42.4 MB
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
     
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        int length = 0;
        ListNode temp = head;

        while (temp != null) {
            length++;
            temp = temp.next;
        }

        int position = length - n;

        temp = dummy;

        for (int i = 0; i < position; i++) {
            temp = temp.next;
        }

        temp.next = temp.next.next;

        return dummy.next;   
    }
}
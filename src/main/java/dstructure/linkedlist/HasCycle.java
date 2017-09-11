package dstructure.linkedlist;

/**
 * Created by Polylanger on 9/11/2017.
 */
public class HasCycle {

    public static void main(String[] args) {
        HasCycle hasCycle = new HasCycle();
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        listNode.next = listNode1;
        hasCycle.hasCycle(listNode);

    }

    /**
     * @param head: The first node of linked list.
     * @return: True if it has a cycle, or false
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

}

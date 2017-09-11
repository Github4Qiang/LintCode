package dstructure.linkedlist;

/**
 * Created by Polylanger on 9/11/2017.
 */
public class ReorderList {

    public static void main(String[] args) {
        ReorderList reorderList = new ReorderList();
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        listNode.next = listNode1;
        ListNode listNode2 = new ListNode(3);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(4);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(5);
        listNode3.next = listNode4;
        reorderList.reorderList(listNode);
    }

    private ListNode findPreMidNode(ListNode head) {
        ListNode slow = head, fast = head, prev = slow;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return prev;
    }

    private ListNode reverseNode(ListNode head) {
        if (head == null) return null;
        ListNode cur = head.next;
        head.next = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = head;
            head = cur;
            cur = temp;
        }
        return head;
    }

    /**
     * @param head: The head of linked list.
     * @return: nothing
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode preMidNode = findPreMidNode(head);
        ListNode reverseList = reverseNode(preMidNode.next);
        preMidNode.next = null;

        ListNode last = null, prev = null;
        preMidNode = head;
        while (preMidNode != null) {
            last = reverseList.next;
            reverseList.next = preMidNode.next;
            preMidNode.next = reverseList;
            preMidNode = reverseList.next;
            prev = reverseList;
            reverseList = last;
        }

        if (prev != null && last != null) {
            prev.next = last;
        }
    }

}

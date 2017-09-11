package dstructure.linkedlist;

/**
 * Created by Polylanger on 9/11/2017.
 * http://www.lintcode.com/zh-cn/problem/rotate-list/
 */
public class RotateList {

    public static void main(String[] args) {
        RotateList rotateList = new RotateList();
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        listNode.next = listNode1;
        ListNode listNode2 = new ListNode(3);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(2);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(1);
        listNode3.next = listNode4;
        rotateList.rotateRight(listNode, 1);
    }

    /**
     * @param head: the List
     * @param k:    rotate to the right k places
     * @return: the list after rotation
     */
    public ListNode rotateRight(ListNode head, int k) {
        // write your code here
        if (head == null)
            return null;

        while (k > 0) {
            k = head.next == null ? k - 1 : k;

            ListNode prev = head, next = head;
            while (next.next != null) {
                next = next.next;
                if (--k < 0) {
                    prev = prev.next;
                }
            }

            next.next = head;
            head = prev.next;
            prev.next = null;
        }
        return head;
    }
}
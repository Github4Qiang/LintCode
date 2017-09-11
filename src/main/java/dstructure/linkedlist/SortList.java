package dstructure.linkedlist;

/**
 * Created by Polylanger on 9/11/2017.
 */
public class SortList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(2);
        ListNode listNode1 = new ListNode(-1);
        listNode.next = listNode1;
        ListNode listNode2 = new ListNode(0);
        listNode1.next = listNode2;
        SortList sortList = new SortList();
        sortList.sortList(listNode);
    }

    /**
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list, using constant space complexity.
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        // write your code here
        ListNode preMidNode = findPreMidNode(head);
        ListNode midNode = preMidNode.next;
        preMidNode.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(midNode);
        return merge(left, right);
    }

    private ListNode findPreMidNode(ListNode head) {
        ListNode fast = head, slow = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return prev;
    }

    private ListNode merge(ListNode list1, ListNode list2) {

        ListNode head = null, raw_head = null;
        while (list1 != null && list2 != null) {
            if (head == null) {
                if (list1.val > list2.val) {
                    head = list2;
                    list2 = list2.next;
                } else {
                    head = list1;
                    list1 = list1.next;
                }
                raw_head = head;
            } else {
                if (list1.val > list2.val) {
                    head.next = list2;
                    list2 = list2.next;
                } else {
                    head.next = list1;
                    list1 = list1.next;
                }
                head = head.next;
            }
        }

        if (list1 != null) {
            head.next = list1;
        } else {
            head.next = list2;
        }
        return raw_head;
    }

}

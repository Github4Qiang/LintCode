package dstructure.linkedlist;

/**
 * Created by Polylanger on 9/11/2017.
 */
public class SortedListToBST {

    public static void main(String[] args) {
        SortedListToBST listToBST = new SortedListToBST();
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        listNode.next = listNode1;
        ListNode listNode2 = new ListNode(3);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(4);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(5);
        listNode3.next = listNode4;
        TreeNode treeNode = listToBST.sortedListToBST(listNode);
        return;
    }

    // 返回中点的前一个节点
    private ListNode findMidNode(ListNode head) {
        ListNode slow = head, fast = head, prev = slow;
        while (fast != null && fast.next != null && fast.next.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next != null ? fast.next.next : fast.next;
        }
        return prev;
    }

    /**
     * @param head: The first node of linked list.
     * @return: a tree node
     */
    public TreeNode sortedListToBST(ListNode head) {
        TreeNode root, left, right;
        if (head == null) return null;
        else if (head.next == null) {
            return new TreeNode(head.val);
        } else if (head.next.next == null) {
            root = new TreeNode(head.next.val);
            left = new TreeNode(head.val);
            root.left = left;
            return root;
        }

        ListNode lTail = findMidNode(head);
        ListNode mNode = lTail.next;
        ListNode rHead = mNode.next;
        lTail.next = null;
        mNode.next = null;
        root = new TreeNode(mNode.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(rHead);
        return root;
    }
}

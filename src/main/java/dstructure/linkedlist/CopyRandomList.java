package dstructure.linkedlist;

/**
 * Created by Polylanger on 9/11/2017.
 */
public class CopyRandomList {

    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;

        RandomListNode cur = head;
        // copy next node
        while (cur != null) {
            // insert current node
            RandomListNode curDupl = new RandomListNode(cur.label);
            curDupl.next = cur.next;
            cur.next = curDupl;
            cur = cur.next.next;
        }

        // copy random node
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // split duplicate list
        cur = head;
        RandomListNode dupHead = head.next, duplCur = dupHead;
        while (duplCur != null && duplCur.next != null) {
            cur.next = duplCur.next;
            cur = cur.next;
            duplCur.next = cur.next;
            duplCur = duplCur.next;
        }
        return dupHead;
    }
}

package io.github.zxbetter.leetcode;

/**
 * 两个数相加
 *
 * @author zxbetter
 */
public class Problem2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l = l1, r = l2, result = new ListNode(0), r1 = result;
        int a;

        while (l != null || r != null) {
            if (l != null) {
                r1.val += l.val;
            }

            if (r != null) {
                r1.val += r.val;
            }

            if (r1.val >= 10) {
                r1.val -= 10;
                a = 1;
            } else {
                a = 0;
            }

            if (l != null) {
                l = l.next;
            }

            if (r != null) {
                r = r.next;
            }

            if (l != null || r != null || a != 0) {
                r1 = (r1.next = new ListNode(a));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode listNode = new Problem2().addTwoNumbers(l1, l2);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

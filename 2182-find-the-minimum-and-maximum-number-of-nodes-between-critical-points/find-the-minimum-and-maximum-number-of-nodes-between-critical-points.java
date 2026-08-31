/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int first = -1;
        int prevCritical = -1;

        int min = Integer.MAX_VALUE;
        int max = -1;

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        while (curr.next != null) {

            if ((prev.val < curr.val && curr.val > curr.next.val) ||
                (prev.val > curr.val && curr.val < curr.next.val)) {

                // First critical point
                if (first == -1) {
                    first = index;
                }

                // Distance from previous critical point
                if (prevCritical != -1) {
                    min = Math.min(min, index - prevCritical);
                }

                prevCritical = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        // Fewer than 2 critical points
        if (min == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }

        // Maximum distance = last critical - first critical
        max = prevCritical - first;

        return new int[]{min, max};
    }
}
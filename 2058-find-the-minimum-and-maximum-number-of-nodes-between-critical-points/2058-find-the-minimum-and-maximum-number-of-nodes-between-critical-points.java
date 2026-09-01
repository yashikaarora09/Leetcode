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
        
        int first = -1;       // position of first critical point
        int prevCritical = -1; // position of previous critical point
        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;

        int pos = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        while (curr != null && curr.next != null) {
            
            // Check if curr is a critical point
            if ((curr.val > prev.val && curr.val > curr.next.val) ||
                (curr.val < prev.val && curr.val < curr.next.val)) {
                
                // First critical point
                if (first == -1) {
                    first = pos;
                } 
                // We already have a previous critical point
                else {
                    int distance = pos - prevCritical;
                    minDistance = Math.min(minDistance, distance);
                }

                // Update previous critical point
                prevCritical = pos;

                // Maximum distance is between first and current
                maxDistance = Math.max(maxDistance, pos - first);
            }

            prev = curr;
            curr = curr.next;
            pos++;
        }

        // Fewer than 2 critical points
        if (minDistance == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }

        return new int[]{minDistance, maxDistance};
    }
}
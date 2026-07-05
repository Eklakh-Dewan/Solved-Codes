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
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Dummy node helps handle insertions at the beginning of the list
        ListNode dummy = new ListNode(0);
        ListNode current = head;
        
        while (current != null) {
            // Store the next node to process
            ListNode nextNode = current.next;
            
            // Start from the dummy to find the correct position
            ListNode prev = dummy;
            while (prev.next != null && prev.next.val < current.val) {
                prev = prev.next;
            }
            
            // Insert current node between prev and prev.next
            current.next = prev.next;
            prev.next = current;
            
            // Move to the next node in the original list
            current = nextNode;
        }
        
        return dummy.next;
    }
}
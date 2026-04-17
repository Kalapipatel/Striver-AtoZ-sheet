/*
Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]
 

Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
*/

class Solution {
    public int length(ListNode head){
        int len = 0;
        ListNode curr = head;

        while(curr != null){
            curr = curr.next;
            len++;
        }

        return len;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = length(head);
        int k = len - n + 1;

        if(len == 1) return null;
        if(len == n) return head.next;

        ListNode temp = head;
        ListNode prev = null;
        int cnt = 1;

        while(temp != null){
            if(cnt == k){
                prev.next = prev.next.next; 
            }

            cnt++;
            prev = temp;
            temp = temp.next;
        }

        return head;
    }
}

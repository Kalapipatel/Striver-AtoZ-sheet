/*
Given the head of a linked list, determine whether the list contains a loop. If a loop is present, return the number of nodes in the loop, otherwise return 0.

Note: Internally, pos(1 based index) is used to denote the position of the node that tail's next pointer is connected to. If pos = 0, it means the last 
node points to null, indicating there is no loop. Note that pos is not passed as a parameter.

Examples:

Input: pos = 2, [1-2-3-4-5] 5 linked to 2
Output: 4
Explanation: There exists a loop in the linked list and the length of the loop is 4.

Input: pos = 3, [25,14,19,33,10] 10->19
Output: 3
Explanation: The loop is from 19 to 10. So length of loop is 19 → 33 → 10 = 3.

Input: pos = 0,  [1,2,3,4,5-null]
Output: 0
Explanation: There is no loop.
Constraints:
1 ≤ number of nodes ≤ 105
1 ≤ node->data ≤ 104
0 ≤ pos < number of nodes
*/


class Solution {
    public int lengthOfLoop(Node head) {
        // code here
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) break;
        }
        
        if (fast == null || fast.next == null) return 0;

        // this section is to find the start node
        // while(head != slow){
        //     head = head.next;
        //     slow = slow.next;
        // }
      // There is no need of this section. because we just need a node inside of loop and then we will traverse from that node until that same node arrives.
      // because loops exits the same node will come and in this way wa cant starts counter from slow till again we will reach slow.
        
        Node curr = slow.next;
        int cnt = 1;
        
        while(curr != slow){
            curr = curr.next;
            cnt++;
        }

        return cnt;
    }
}

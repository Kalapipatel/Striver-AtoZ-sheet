/*
Given the head of a linked list where nodes can contain values 0s, 1s, and 2s only. Your task is to rearrange the list so that all 0s appear at the beginning, followed by all 1s, and all 2s are placed at the end.

Examples:

Input: head = 1 → 2 → 2 → 1 → 2 → 0 → 2 → 2
   
Output: 0 → 1 → 1 → 2 → 2 → 2 → 2 → 2
Explanation: All the 0s are segregated to the left end of the linked list, 2s to the right end of the list, and 1s in between. The final list will be:
   
Input: head = 2 → 2 → 0 → 1
   
Output: 0 → 1 → 2 → 2
Explanation: After arranging all the 0s, 1s and 2s in the given format, the output will be:
   
Constraints:
1 ≤ no. of nodes ≤ 106
0 ≤ node->data ≤ 2
*/

/*
class Node {
    int data;
    Node next;

    Node(int d)
    {
        data = d;
        next = null;
    }
}*/

class Solution {
    public Node segregate(Node head) {
        int zero = 0, one = 0, two = 0;
        
        Node curr = head;
        while(curr != null){
            if(curr.data == 0) zero++;
            else if(curr.data == 1) one++;
            else two++;
            
            curr = curr.next;
        }
        
        curr = head;
        while(zero > 0){
            curr.data = 0;
            curr = curr.next;
            zero--;
        }
        
        while(one > 0){
            curr.data = 1;
            curr = curr.next;
            one--;
        }
        
        while(two > 0){
            curr.data = 2;
            curr = curr.next;
            two--;
        }
        
        return head;
    }
}

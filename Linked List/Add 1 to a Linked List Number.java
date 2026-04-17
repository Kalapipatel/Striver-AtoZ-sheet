/*
You are given the head of a linked list where each node contains a single digit. The digits together represent a number formed by concatenating the node values in order. Add 1 to this number and return the head of the modified linked list.

Examples :
Input: Head: 4->5->6
Output: 457
Explanation: 4->5->6 represents 456 and when 1 is added it becomes 457. 

Input: Head: 1->2->9
Output: 130

Input: Head: 1 9 9 2 9
Output: 19920

Input: Head: 9->9->9
Output: 1000
 
Explanation:  1->2->3 represents 123 and when 1 is added it becomes 124. 
Constraints:
1 <= len(list) <= 105
0 <= list[i] <= 9
*/


class Solution {
    public void fun(Node head, boolean carry[]){
        if(head.next == null){
            if(head.data < 9){
                head.data = head.data + 1;
                carry[1] = false;
                return;
            }
            
            head.data = 0;
            carry[0] = true;

            return;
        }
        
        fun(head.next, carry);
        
        if(head.data != 9) carry[1] = false; // for nine
        
        if(carry[0] == true){
            if(head.data == 9){
                head.data = 0;
            }
            else{
                head.data = head.data + 1;
                carry[0] = false;
            }
            
        }
    }
    
    public Node addOne(Node head) {
        boolean carry[] = new boolean[2];
        carry[1] = true;       // remains true if all node = 9, if any node.data != 9 then it become false
        
        fun(head, carry);
        
        if(carry[1] == true){
            Node temp = new Node(1);
            temp.next = head;
            return temp;
        }
        
        return head;
    }
}



// -------------------------- iterative code

import java.util.*;

class Solution {
    public Node addOne(Node head) {
        Node curr = head;
        Node prev = null;
        
        boolean nine = true;
        while(curr.next != null){
            if(curr.data != 9) nine = false;
            curr = curr.next;
        }
        
        if(curr.data != 9) nine = false; // check at the last node
        
        if(curr.data < 9){
            curr.data = curr.data + 1;
            return head;
        }
        
        if(nine){
            curr = new Node(1);
            Node temp = curr;
            curr.next = head; // link to head
            curr = curr.next; // curr  = head, otherwise 1 will get overwritten
            
            while(curr != null){
                curr.data = 0;
                curr = curr.next;
            }
            
            return temp;
        }
        
        prev = null;
        curr = head;
        Node next;

        while(curr != null){    // reverse a LL
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        Node newHead = prev;
        
        curr = newHead;
        
        while(curr.data == 9){
            curr.data = 0;
            curr = curr.next;
        }
        
        curr.data = curr.data + 1;
        
        prev = null;
        curr = newHead;
        Node next2;
        
        while(curr != null){
            next2 = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next2;
        }
        
        return head;
    }
}

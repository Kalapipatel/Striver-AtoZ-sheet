/*
Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

Do not modify the linked list.

 

Example 1:


Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
Example 2:


Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
Example 3:


Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
 

Constraints:

The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.
 

Follow up: Can you solve it using O(1) (i.e. constant) memory?
*/


public class Solution {
    public ListNode detectCycle(ListNode head) {
        // Map<ListNode, Integer> map = new HashMap<>();
        // ListNode curr = head;
        // int idx = 0;

        // while(curr != null){
        //     if(map.containsKey(curr)) return curr;

        //     map.put(curr, idx);
        //     idx++;
        //     curr =curr.next;
        // }

        // return null;

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) break;
        }
        
        if (fast == null || fast.next == null) return null;

        while(head != slow){
            head = head.next;
            slow = slow.next;
        }

        return slow;
    }
}

/*
We have to solve this question with O(1) space but it's good idea to have multipe idea in real interview, so let me share 3 ways to sove this quesiton. The first solution meets O(1) space.

1 -> 2 -> 3 -> 4 -> 5 -> 6
          |______________|

distance A = 1 2 3
B = 3 4 5
C = 5 6 3
head: 1
starting node of cycle: 3
Solution 1
We have to find starting node of cycle, so the first point of algorithm is

⭐️ Points

Check if we have a cycle in the linked list.

No wonder.

How can we detect cycle in the linked list?
We use the same idea of Linked List Cycle.



We use two pointers. One is slow pointer moving once everytime. The other is fast pointer moving

ce everytime.

fast pointer is always ahead of slow pointer, so if they meet each other at some point, we are sure there is a cycle in the linked list.

Let's see one by one with the example above.

slow = 1
fast = 1
Move once.

slow = 2
fast = 3
They are different. Let's continue.

slow = 3
fast = 5
They are different. Let's continue.

slow = 4
fast = 3
They are different. Let's continue.

slow = 5
fast = 5
The are pointing the same node. We are sure we have a cycle in the list.

⭐️ Points

How come we are sure we have the cycle?

That's because if we don't have the cycle in the list, we have null at some point. In that case, fast pointer will reach null first. But it goes nowhere from null. That means the linked list doesn't have the cycle.

But this case above, fast pointer go around the cycle, then come back to node 3. The fast pointer caught up with the slow pointer from behind.

Now slow and fast are at node 5. If one of pointers moves distance C, we can return node 3 as a result.

But problem is solution code never know how long distance C is, so we have to prove that one of pointers move exact distance C.

To prove that, let's think about distance of slow and distance of fast.

distance of `slow` is A + B.
distance of `fast` is A + B + C + B.
Since slow pointer move once and fast pointer move twice, formula below is valid.

2(A + B) = A + B + C + B
Let's simplify the formula.

2(A + B) = A + B + C + B
= 2A + 2B = A + 2B + C

Remove 2B
2A + 2B = A + 2B + C
↓
2A = A + C

Remove A
2A = A + C
↓
A = C
Actually, distance A and distance C are the same. That's why if one of pointers goes back to head node and slow poniter and fast pointer move once everytime, We will reach starting node of the cycle, because distance A and distance C are the same.

If they meet each other at some point, that means that node is end of distance A and distance C. We can move distance C exactly.

Let's update fast with head.

slow = 5
fast = 1
Let's move one by one.

slow = 6
fast = 2
↓
slow = 3
fast = 3
They meet at node 3, that means node 3 is starting node of the cycle.

return fast or slow


Complexity
Time complexity: O(n)
Space complexity: O(1)
*/

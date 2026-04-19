/* 
Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

For example, the following two linked lists begin to intersect at node c1:


The test cases are generated such that there are no cycles anywhere in the entire linked structure.

Note that the linked lists must retain their original structure after the function returns.

Custom Judge:

The inputs to the judge are given as follows (your program is not given these inputs):

intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
listA - The first linked list.
listB - The second linked list.
skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.

Example 1:


Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
- Note that the intersected node's value is not 1 because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are different node references. In other words, they point to two different locations in memory, while the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.
Example 2:


Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Intersected at '2'
Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
Example 3:


Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: No intersection
Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.
 

Constraints:

The number of nodes of listA is in the m.
The number of nodes of listB is in the n.
1 <= m, n <= 3 * 104
1 <= Node.val <= 105
0 <= skipA <= m
0 <= skipB <= n
intersectVal is 0 if listA and listB do not intersect.
intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
*/

// using hashsmap

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        Map<ListNode, Integer> map = new HashMap<>();

        ListNode curr = headA;

        while(curr != null){
            map.put(curr, 1);
            curr = curr.next;
        }

        curr = headB;
        while(curr != null){
            if(map.containsKey(curr)){
                break;
            }
            curr = curr.next;
        }
        
        return curr;
    }
}

// optmial approach
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode lista = headA;
        ListNode listb = headB;

        while (lista != listb) {
            lista = (lista != null) ? lista.next : headB;
            listb = (listb != null) ? listb.next : headA;
        }

        // Basically it run's simultaneously
        return lista;        
    }
}
/*
Approach
Let's think about the question with a small example.

A: 1 → 8
B: 1 → 8
In this case, we can find intersection node(=8) very easily.
How about this.

A: 4 → 1 → 8
B: 6 → 1 → 8
This case is also easy. Just move one by one, then find 8 from A and B at the same time.

How about this.

A: 4 → 1 → 8
B: 5 → 6 → 1 → 8
In this case, if we move one by one we can't find 8 at the same time.

◽️ Question

How can we make the two lists the same length?

My strategy is to connect both lists with each other.

A: 4 → 1 → 8 → 5 → 6 → 1 → 8
B: 5 → 6 → 1 → 8 → 4 → 1 → 8
This is based on the same principle as 3 + 2 = 5 and 2 + 3 = 5.

⭐️ Points

When we reach end of lists, go back to head of other list, so that we can find intersection at some point.

Let's think about whole lists.

                           B→→→→→→→→→→→→
A: 4 → 1 → 8 → 4 → 5 → n → 5 → 6 → 1 → 8
B: 5 → 6 → 1 → 8 → 4 → 5 → n → 4 → 1 → 8
                               A→→→→→→→→
n is null
return node 8
Let's see null case quickly!

null.png

                   B→→→→→→→→
A: 2 → 6 → 4 → n → 1 → 5 → n
B: 1 → 5 → n → 2 → 6 → 4 → n
               A→→→→→→→→→→→→
n is null
We are pointing null at the same time.

return null
*/

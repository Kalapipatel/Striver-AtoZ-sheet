/*
Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

 

Example 1:


Input: head = [1,2,2,1]
Output: true
Example 2:


Input: head = [1,2]
Output: false
 

Constraints:

The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9
 

Follow up: Could you do it in O(n) time and O(1) space?
*/



Every Linked List Problem, if you can't do it in LinkedList format,
copy the data in list and now your question would become for an 
array, solve it.
Currently code is in java only, 
but will be uploaded in different languages soon.
Reverse Linked List ( Yesterday's Solution )
Approach ( Using List )
To Check if the Given Linked List is palindrome or not , we can copy the data in list and then check if the list if palindrome or not.

Complexity
Time complexity: O(N)
Space complexity: O(N)
Code
class Solution {
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList();
        while(head != null) {
            list.add(head.val);
            head = head.next;
        }
        
        int left = 0;
        int right = list.size()-1;
        while(left < right && list.get(left) == list.get(right)) {
            left++;
            right--;
        }
        return left >= right;
    }
}
Approach#2 ( Using Stack )
First push all data in stack, and then traverse linked list and keep popping element from stack one by one, so stack will give you element from last.

Code
class Solution {
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack();
        ListNode curr = head;
        while(curr != null) {
            stack.push(curr.val);
            curr = curr.next;
        }
        curr = head;
        while(curr != null && curr.val == stack.pop()) {
            curr = curr.next;
        }
        return curr == null;
    }
}
Approach#3 ( Using Recursion )
As you know, if any problem you can do using stack only then you can use recursion as well ( mostly ).

So we will have one global pointer as curr we will move this pointer once we reach the end and recursion will start from end of the list.

Code
class Solution {
    ListNode curr;
    public boolean isPalindrome(ListNode head) {
        curr = head;
        return solve(head);
    }

    public boolean solve(ListNode head) {
        if(head == null) return true;
        boolean ans = solve(head.next) && head.val == curr.val;
        curr = curr.next;
        return ans;
    }
}
Interview : Can you come up with a solution with O(1) space ?
You: Sure. ( I already knew that )

Approach#4
The main idea to check palindrome is , if the first and last elements are same or not and then check for second and second last.

So I am thinking what if I create new linked list which would be reversed of the original linked list.

And then I can compare each element one by one.

But do I need the whole linked list to reverse and compare ?
No, I just need the second half of linked list to be reversed.

Now the question is how can I get the second half ?
There are two approach

Count all nodes first and then move to n/2th node, this would be middle node.
Use two pointer one will move with the speed of 1 and one with the speed of 2, so when the fast pointer reaches the end, slow pointer would be at mid.
After getting middle node, reverse the list after that node.
and then compare each element.

Code
class Solution {

    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rev = reverse(slow.next); // reverse second list
        slow.next = null;
        while(rev != null) {
            if(head.val != rev.val) {
                return false;
            }
            head = head.next;
            rev = rev.next;
        }
        return true;
    }
}

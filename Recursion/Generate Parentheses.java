/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
 

Constraints:

1 <= n <= 8
*/

class Solution {
    public void fun(int left, int right, List<String> ans, String s, int n){
        if(left == right && left + right == 2*n){
            ans.add(s);
            return;
        }

        if(left < n){
            fun(left+1, right, ans, s + "(", n);
        }

        if(right < left){
            fun(left, right+1, ans, s + ")", n);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        fun(0, 0, ans, "", n);

        return ans;
    }
}

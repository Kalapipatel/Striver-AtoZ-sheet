/*
Given a string s of length n, find all the possible non-empty subsequences of the string s in lexicographically-sorted order.

Example 1:

Input : 
s = "abc"
Output: 
a ab abc ac b bc c
Explanation : 
There are a total 7 number of subsequences possible for the given string, and they are mentioned above in lexicographically sorted order.
Example 2:

Input: 
s = "aa"
Output: 
a a aa
Explanation : 
There are a total 3 number of subsequences possible for the given string, and they are mentioned above in lexicographically sorted order.
Your Task:
You don't need to read input or print anything. Your task is to complete the function AllPossibleStrings() which takes a string s as the input parameter and returns a list of all possible subsequences (non-empty) that can be formed from s in lexicographically sorted order.

Expected Time Complexity: O( n*2n  )
Expected Space Complexity: O( n * 2n )

Constraints: 
1 <= n <= 16
s constitutes of lower case english alphabets
*/

// User function Template for Java

class Solution {
    public void fun(int idx, int n, String s, List<String> list, StringBuilder sb){
        if(idx == n){
            if(sb.length() != 0){
                list.add(sb.toString());
            }
            return;
        }

        sb.append(s.charAt(idx));
        fun(idx+1, n, s, list, sb);
        sb.deleteCharAt(sb.length() - 1);
        
        fun(idx+1, n, s, list, sb);
    }
    
    public List<String> AllPossibleStrings(String s) {
        // Code here
        List<String> ans = new ArrayList<>();
        fun(0, s.length(), s, ans, new StringBuilder());
        
        Collections.sort(ans);
        
        return ans;
    }
}

/*
Given a string s, find the length of the longest substring without duplicate characters.

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 

Constraints:

0 <= s.length <= 105
s consists of English letters, digits, symbols and spaces.
*/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if(n == 0) return 0;

        int arr[] = new int[256];
        Arrays.fill(arr, -1);

        int l = 0, maxi = 1;
        for(int r=0; r<n; r++){
            char ch = s.charAt(r);

            if(arr[ch] != -1){
                int pos = arr[ch];
                if(pos >= l){
                    l = pos + 1;
                }
            }

            arr[ch] = r;
            maxi = Math.max(maxi, r-l+1);
        }

        return maxi;
    }
}

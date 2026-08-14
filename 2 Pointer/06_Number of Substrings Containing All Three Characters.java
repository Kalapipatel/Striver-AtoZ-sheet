/*
Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.

Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 
Example 2:

Input: s = "aaacb"
Output: 3
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb". 
Example 3:

Input: s = "abc"
Output: 1
 

Constraints:

3 <= s.length <= 5 x 104
s only consists of 'a', 'b' or 'c' characters.
*/

class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int arr[] = new int[3];
        int l=0, cnt=0;

        for(int r=0; r<n; r++){
            char ch = s.charAt(r);
            arr[ch - 'a']++;

            while(arr[0]>0 && arr[1]>0 && arr[2]>0){
                cnt += 1 + (n - r - 1); 

                arr[s.charAt(l) - 'a']--;
                l++;

            }
        }
        
        return cnt;
    }
}

// cimpler solution
class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int arr[] = {-1, -1, -1};
        int cnt=0;

        for(int i=0; i<n; i++){
            char ch = s.charAt(i);
            arr[ch - 'a'] = i;

            if(arr[0] != -1 && arr[1] != -1 && arr[2] != -1){
                cnt += 1 + Math.min(arr[0], Math.min(arr[2], arr[1]));
            }
        }
        
        return cnt;
    }
}

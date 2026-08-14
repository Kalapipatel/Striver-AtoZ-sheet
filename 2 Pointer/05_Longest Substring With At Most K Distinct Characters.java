/*
You are given a string s consisting only lowercase alphabets and an integer k. Your task is to find the length of the longest substring that contains exactly k distinct characters.

Note : If no such substring exists, return -1. 

Examples:

Input: s = "aabacbebebe", k = 3
Output: 7
Explanation: The longest substring with exactly 3 distinct characters is "cbebebe", which includes 'c', 'b', and 'e'.
Input: s = "aaaa", k = 2
Output: -1
Explanation: There's no substring with 2 distinct characters.
Input: s = "aabaaab", k = 2
Output: 7
Explanation: The entire string "aabaaab" has exactly 2 unique characters 'a' and 'b', making it the longest valid substring.
Constraints:
1 ≤ s.size() ≤ 105
1 ≤ k ≤ 26
*/

class Solution {
    public int longestKSubstr(String s, int k) {
        int n = s.length();
        
        int l = 0;
        int ml = -1;

        Map<Character, Integer> map = new HashMap<>();

        for (int r = 0; r < n; r++) {
            char ch = s.charAt(r);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            if (map.size() > k) { // you can use while as well instead of if
                char leftChar = s.charAt(l);

                map.put(leftChar, map.get(leftChar) - 1);

                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }

                l++;
            }

            if (map.size() == k) {
                ml = Math.max(ml, r - l + 1);
            }
        }

        return ml;
    }
}

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = "2"
Output: ["a","b","c"]
 
Constraints:

1 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
*/

class Solution {
    public String letters(char ch){
        if(ch == '2') return "abc";
        else if(ch == '3') return "def";
        else if(ch == '4') return "ghi";
        else if(ch == '5') return "jkl";
        else if(ch == '6') return "mno";
        else if(ch == '7') return "pqrs";
        else if(ch == '8') return "tuv";
        else return "wxyz";
    }

    public void fun(int idx, List<String> list, List<String> ans, StringBuilder sb){
        if(idx == list.size()){
            ans.add(sb.toString());
            return;
        }

        for(char letter : list.get(idx).toCharArray()){
            sb.append(letter);
            fun(idx+1, list, ans, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        List<String> list = new ArrayList<>();
        List<String> ans = new ArrayList<>();

        for(int i=0; i<n; i++){
            char ch = digits.charAt(i);
            list.add(letters(ch));
        }

        fun(0, list, ans, new StringBuilder());

        return ans;
    }
}

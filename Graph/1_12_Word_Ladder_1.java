/*
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
*/

class Solution {

    class pair{
        String word;
        int steps;

        pair(String w, int s){
            this.word = w;
            this.steps = s;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<pair> q = new LinkedList<>();
        q.add(new pair(beginWord, 1));
        Set<String> set = new HashSet<>();

        for(String x : wordList){
            set.add(x);
        }

        while(!q.isEmpty()){
            String word = q.peek().word;
            int steps = q.peek().steps;
            q.remove();

            if(word.equals(endWord)) return steps;

            for(int i=0; i<word.length(); i++){
                for(char ch='a'; ch<='z'; ch++){
                    char replacedArr[] = word.toCharArray();
                    replacedArr[i] = ch;
                    String replacedWord = new String(replacedArr);

                    if(set.contains(replacedWord)){
                        set.remove(replacedWord);
                        q.add(new pair(replacedWord, steps + 1));
                    }
                }
            }
        }

        return 0;
    }
}

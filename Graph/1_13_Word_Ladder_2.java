class Solution {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Queue<List<String>> q = new LinkedList<>();
        List<List<String>> ans = new ArrayList<>();
        List<String> list = new ArrayList<>();
        List<String> usedOnLevel = new ArrayList<>();
        Set<String> set = new HashSet<>();

        for(String x : wordList) set.add(x);

        list.add(beginWord);
        q.add(list);

        int level = 0;
        while(!q.isEmpty()){
            List<String> curr = q.remove();

            if(curr.size() > level){
                level++;

                for(String x : usedOnLevel) set.remove(x);
            }

            String word = curr.get(curr.size()-1);
            if(word.equals(endWord)){
                if(ans.size() == 0) ans.add(curr);
                else if(ans.get(0).size() == curr.size()) ans.add(curr);
            }

            for(int i=0; i<word.length(); i++){
                char[] replacedArr = word.toCharArray();
                for(char ch='a'; ch<='z'; ch++){
                    replacedArr[i] = ch;
                    String replacedWord = new String(replacedArr);

                    if(set.contains(replacedWord)){
                        curr.add(replacedWord);

                        q.add(new ArrayList<>(curr));
                        usedOnLevel.add(replacedWord);

                        curr.remove(curr.size()-1);
                    }
                }
            }
        }

        return ans;
    }
}

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class GfG{

    public static String findOrder(String[] words) {
        
        // Adjacency list
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        
        // In-degree of each character
        int[] inDegree = new int[26];
        
        // Tracks which characters are present
        boolean[] exists = new boolean[26];

        // Initialize graph
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }

        // Mark existing characters
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                exists[ch - 'a'] = true;
            }
        }

        // Build the graph from adjacent words
        for (int i = 0; i + 1 < words.length; ++i) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int len = Math.min(w1.length(), w2.length());
            int j = 0;

            while (j < len && w1.charAt(j) == w2.charAt(j)) ++j;

            if (j < len) {
                int u = w1.charAt(j) - 'a';
                int v = w2.charAt(j) - 'a';
                graph.get(u).add(v);
                inDegree[v]++;
            } else if (w1.length() > w2.length()) {
                // Invalid input 
                return "";
            }
        }

        // Topological sort 
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < 26; ++i) {
            if (exists[i] && inDegree[i] == 0) {
                q.offer(i);
            }
        }

        String result = "";
        while (!q.isEmpty()) {
            int u = q.poll();
            result += (char)(u + 'a');

            for (int v : graph.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    q.offer(v);
                }
            }
        }

        // Check, there was a cycle or not
        for (int i = 0; i < 26; ++i) {
            if (exists[i] && inDegree[i] != 0) {
                return "";
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] words = {"baa", "abcd", "abca", "cab", "cad"};
        String order = findOrder(words);

        System.out.print(order);
    }
}

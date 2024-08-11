// Time Complexity : O(N*L) where N = No. of words, L = avg. length of the word
// Space Complexity : O(N*L)
// Did this code successfully run on Leetcode : YES
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

/**
 * We use Trie to store all the words and then perform backtracking to find the longest word
 * We start from the root and traverse the Trie, if we find a word, we check if it is longer than the current result
 * If it is, we update the result and then perform backtracking to find the next longest word
 */


public class Problem2 {
    private static class TrieNode {
        public TrieNode[] children;
        public boolean isEnd;
        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    private TrieNode root;
    private String res;
    public String longestWord(String[] words) {
        this.root = new TrieNode();
        this.res = "";
        for(String word: words) { //TC - O(N*l)
            this.insert(word);
        }

        backtrack(root, new StringBuilder());
        return res;
    }

    private void insert(String word) {
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if(curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
        }
        curr.isEnd = true;
    }

    private void backtrack(TrieNode curr, StringBuilder sb) {
        //base
        if(sb.length() > res.length()) {
            res = sb.toString();
        }
        //logic
        for(int i = 0; i < 26; i++) {
            if(curr.children[i] != null && curr.children[i].isEnd) {
                int len = sb.length();
                //action
                sb.append((char) (i + 97));

                //recurse
                backtrack(curr.children[i], sb);

                //backtrack
                sb.setLength(len);

            }
        }

    }
}

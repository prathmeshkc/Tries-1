// Time Complexity : O(w*l) where w = No. of words, L = avg. length of the word
// Space Complexity : O(N*L)
// Did this code successfully run on Leetcode : YES
// Any problem you faced while coding this : No


import java.util.List;

// Your code here along with comments explaining your approach

/**
 * We use Trie to store all the words in the dictionary. For each word in the sentence,
 * we traverse the Trie and iterate over the whole word. We first check if the character
 * is present in the Trie. If it is, we move the curr to that node and check if it is the end of the word.
 * If it is, we return the word. If not, we continue traversing the Trie.
 * If we reach the end of the word iteration without finding the word, we return the word itself.
 */

public class Problem3 {
    private static class TrieNode {
        TrieNode[] children;
        boolean isEnd;
        String word;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    private TrieNode root;

    public String replaceWords(List<String> dictionary, String sentence) {
        this.root = new TrieNode();
        String[] words = sentence.split(" ");

        for (String word : dictionary) {
            insert(word);
        }

        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            sb.append(findRoot(root, word)).append(" ");
        }

        return sb.toString().trim();
    }

    private void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
        }
        curr.isEnd = true;
        curr.word = word;
    }

    private String findRoot(TrieNode curr, String word) {
        TrieNode crawler = curr;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int idx = c - 'a';
            if (crawler.children[idx] == null) return word;

            crawler = crawler.children[idx];
            if (crawler.isEnd) {
                return crawler.word; //if we don't have word property in the TrieNode, we can take the word.substring(0, i+1);
            }
        }
        return word;
    }
}

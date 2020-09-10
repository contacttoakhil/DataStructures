package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

/***
 *
 * We will use trie, we have another implementation of Trie (LC208ImplementTrie) also. We can use that also.
 */
public class LC212WordSearchII {

    private TrieNode root = new TrieNode();
    private List<String> res = new ArrayList<>();
    private char[][] board = null;

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        this.board = board;
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[0].length; c++)
                dfs (r, c, root);
        return res;
    }

    public void dfs(int r, int c, TrieNode node) {
        if(invalid(r,c,node)) return;
        char ch = board[r][c];
        node = node.children[ch - 'a'];
        if (node.word != null) {   // found one
            res.add(node.word);
            node.word = null;     // de-duplicate
        }
        board[r][c] = '#';
        dfs(r - 1, c ,node);
        dfs(r, c - 1, node);
        dfs(r + 1, c, node);
        dfs(r, c + 1, node);
        board[r][c] = ch;
    }

    private TrieNode buildTrie(String[] words) {
        for (String word : words) {
            TrieNode node = root;
            for(char c: word.toCharArray()){
                if(node.children[c-'a']==null){
                    node.children[c-'a']= new TrieNode();
                }
                node = node.children[c-'a'];
            }
            node.word = word;
        }
        return root;
    }

    private boolean invalid(int r, int c, TrieNode node) {
        int rows = board.length-1;
        int cols = board[0].length-1;
        if(r<0 || r> rows || c<0 || c>cols || board[r][c] == '#' || node.children[board[r][c] - 'a'] == null)
            return true;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        LC212WordSearchII wordSearchII = new LC212WordSearchII();
        System.out.println(wordSearchII.findWords(board, new String[]{"oath","pea","eat","rain"}));
    }
}
class TrieNode {
    public String word;
    public TrieNode[] children = new TrieNode[26];
}
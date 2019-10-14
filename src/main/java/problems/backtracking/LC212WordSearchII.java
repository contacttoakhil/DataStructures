package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

/***
 *
 * We will use trie, we have another implementation of Trie (LC208ImplementTrie) also. We can use that also.
 */
public class LC212WordSearchII {

    private TrieNode root;
    private List<String> res = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {
        if(board == null || board.length == 0)
            return res;
        root = new TrieNode();
        for(String word : words)
            insert(word);
        StringBuilder sb = new StringBuilder();  // try assemble word
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                search(sb, root, board, i, j);
            }
        }
        return res;
    }

    private void insert(String word) {
        TrieNode node = root;
        for(char c: word.toCharArray()){
            if(node.children[c-'a']==null){
                node.children[c-'a']= new TrieNode();
            }
            node = node.children[c-'a'];
        }
        node.isWord = true;
    }

    private void search(StringBuilder sb, TrieNode node, char[][] board, int r, int c) {
        if(invalidCoordinates(board, r, c) || board[r][c] == '#')
            return;
        char temp = board[r][c];
        TrieNode curRoot = node.children[temp - 'a'];    // set node here for DFS
        if(curRoot == null)
            return;
        sb.append(temp);
        if(curRoot.isWord == true) {
            curRoot.isWord = false;
            res.add(sb.toString());
        }
        board[r][c] = '#';          // mark visited cell to '#'
        search(sb, curRoot, board, r + 1, c);
        search(sb, curRoot, board, r - 1, c);
        search(sb, curRoot, board, r, c + 1);
        search(sb, curRoot, board, r, c - 1);
        sb.setLength(sb.length() - 1);
        board[r][c] = temp;            // backtracking
    }

    private boolean invalidCoordinates(char[][] board, int r, int c) {
        int rows = board.length;
        int cols = board[0].length;
        return (r<0 || r>=rows || c<0 || c>=cols) ? true : false;
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
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26];
}
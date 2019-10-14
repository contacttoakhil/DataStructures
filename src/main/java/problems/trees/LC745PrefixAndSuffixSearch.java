package main.java.problems.trees;

/***
 * Given many words, words[i] has weight i.
 *
 * Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix). It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.
 *
 * Examples:
 *
 * Input:
 * WordFilter(["apple"])
 * WordFilter.f("a", "e") // returns 0
 * WordFilter.f("b", "") // returns -1
 *
 *
 * Note:
 *
 * words has length in range [1, 15000].
 * For each test case, up to words.length queries WordFilter.f may be made.
 * words[i] has length in range [1, 10].
 * prefix, suffix have lengths in range [0, 10].
 * words[i] and prefix, suffix queries consist of lowercase letters only.
 */
public class LC745PrefixAndSuffixSearch {
    TrieNodePS root;
    public LC745PrefixAndSuffixSearch(String[] words) {
        root = new TrieNodePS();
        for (int weight = 0; weight < words.length; weight++) {
            String word = words[weight] + "{";
            for (int i = 0; i < word.length(); i++) {
                TrieNodePS cur = root;
                cur.weight = weight;
                // add "apple{apple", "pple{apple", "ple{apple", "le{apple", "e{apple", "{apple" into the Trie Tree
                for (int j = i; j < 2 * word.length() - 1; j++) {
                    int k = word.charAt(j % word.length()) - 'a';
                    if (cur.children[k] == null)
                        cur.children[k] = new TrieNodePS();
                    cur = cur.children[k];
                    cur.weight = weight;
                }
            }
        }
    }
    public int f(String prefix, String suffix) {
        TrieNodePS cur = root;
        for (char c: (suffix + '{' + prefix).toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                return -1;
            }
            cur = cur.children[c - 'a'];
        }
        return cur.weight;
    }
}
class TrieNodePS {
    TrieNodePS[] children;
    int weight;
    public TrieNodePS() {
        children = new TrieNodePS[27]; // 'a' - 'z' and '{'. 'z' and '{' are neighbours in ASCII table
        weight = 0;
    }
}
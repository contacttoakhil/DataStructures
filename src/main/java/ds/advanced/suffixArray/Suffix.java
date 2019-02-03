package main.java.ds.advanced.suffixArray;

public class Suffix implements Comparable<Suffix> {

    private String text;
    private int index;

    public Suffix(String text, int index) {
        this.text = text;
        this.index = index;
    }

    public int length() {
        return text.length() - index;
    }

    public char charAt(int idx) {
        return text.charAt(index + idx);
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(Suffix that) {
        if (this == that) return 0;
        for (int i = 0; i < Math.min(this.length(), that.length()); i++) {
            if (this.charAt(i) < that.charAt(i)) return -1;
            if (this.charAt(i) > that.charAt(i)) return +1;
        }
        return this.length() - that.length();
    }

    @Override
    public String toString() {
        return text.substring(index);
    }
}

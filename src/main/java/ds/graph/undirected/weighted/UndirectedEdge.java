package main.java.ds.graph.undirected.weighted;

public class UndirectedEdge implements Comparable<UndirectedEdge>{
    private final int either;
    private final int other;
    private final double weight;

    public UndirectedEdge(int either, int or, double weight) {
        this.either = either;
        this.other = or;
        this.weight = weight;
    }

    public UndirectedEdge(int either, int other) {
        this(either, other, 1);
    }

    public int either() {
        return either;
    }

    public int other(int i) {
        if      (i == either) return other;
        else if (i == other) return either;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        return either +"---(" + weight + ")---" + other;
    }

    @Override
    public int compareTo(UndirectedEdge that) {
        return Double.compare(this.weight, that.weight);
    }
}

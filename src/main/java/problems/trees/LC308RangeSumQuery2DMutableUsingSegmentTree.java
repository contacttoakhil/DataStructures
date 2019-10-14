package main.java.problems.trees;

public class LC308RangeSumQuery2DMutableUsingSegmentTree {

    private SegTreeNode[] rows;
    private int[][] m;

    public LC308RangeSumQuery2DMutableUsingSegmentTree(int[][] matrix) {
        m = matrix;
        rows = new SegTreeNode[matrix.length];
        for (int i=0; i<matrix.length; ++i)
            rows[i] = buildTree(matrix[i], 0, matrix[i].length-1);
    }

    private SegTreeNode buildTree(int[] row, int s, int e){
        if (s> e)
            return null;
        SegTreeNode n = new SegTreeNode(s, e);
        if (s == e){
            n.sum = row[s];
            return n;
        }
        int mid = s + (e-s)/2;
        n.left = buildTree(row, s, mid);
        n.right = buildTree(row, mid+1, e);
        if (n.left != null)
            n.sum += n.left.sum;
        if (n.right != null)
            n.sum += n.right.sum;
        return n;
    }
    private void updateImpl(SegTreeNode n, int pos,int delta){
        if (n == null)
            return;
        if(pos >= n.start && pos <= n.end){
            n.sum += delta;
            updateImpl(n.left, pos, delta);
            updateImpl(n.right, pos, delta);
        }
    }
    public void update(int row, int col, int val) {
        updateImpl(rows[row], col, val-m[row][col]);
        m[row][col] = val;
    }
    private int query(SegTreeNode n, int s, int e){
        if(n == null || n.start > e || n.end<s)
            return 0;
        if (n.start >= s && n.end <=e)
            return n.sum;
        return query(n.left, s, e) + query(n.right, s, e);
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i=row1; i<=row2; ++i)
            sum += query(rows[i], col1, col2);
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3,0,1,4,2},
                {5,6,3,2,1},
                {1,2,0,1,5},
                {4,1,0,1,7},
                {1,0,3,0,5}
        };
        LC308RangeSumQuery2DMutableUsingSegmentTree rangeSumQuery2DMutable = new LC308RangeSumQuery2DMutableUsingSegmentTree(matrix);
        System.out.println(rangeSumQuery2DMutable.sumRegion(2,1,4,3));      //8
        rangeSumQuery2DMutable.update(3, 2, 2);
        System.out.println(rangeSumQuery2DMutable.sumRegion(2,1,4,3));      // 10
    }
}
class SegTreeNode{
    public SegTreeNode left, right;
    public int start, end, sum;
    public SegTreeNode(int s, int e){
        start = s;
        end = e;
        sum = 0;
    }
}

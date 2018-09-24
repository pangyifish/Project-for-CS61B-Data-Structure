package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int [][] grid;
    WeightedQuickUnionUF w;
    private int numOpen = 0;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        this.N = N;
        if (N < 0) throw new IllegalArgumentException("N is below zero!");
        grid = new int [N][N];

        w = new WeightedQuickUnionUF(N * N);


        for(int i = 0; i < N; i++ ) {
            for (int j =0; j < N; j++) {
                grid[i][j] = 0 ;
            }
        }
    }


    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 | row > N -1 | col < 0 | col > N -1) throw new IndexOutOfBoundsException();
        if (!isOpen(row, col)) {
            grid[row][col] = 1;
            numOpen += 1;
            if (row + 1 <= N - 1 && isOpen(row + 1, col)) w.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            if (row - 1 >= 0 && isOpen(row - 1, col)) w.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            if (col + 1 <= N - 1 && isOpen(row, col + 1)) w.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            if (col - 1 >= 0 && isOpen(row, col - 1)) w.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
    }


    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 | row > N -1 | col < 0 | col > N -1) throw new IndexOutOfBoundsException();
        return (grid[row][col] == 1);
    }


    private int xyTo1D(int row, int col){
        return row * N + col;
    }


    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 | row > N - 1 | col < 0 | col > N - 1) throw new IndexOutOfBoundsException();
        if (row == 0 && isOpen(0, col)) return true;
        if (row > 0) {
            for (int i = 0; i < N; i++) {
                if (w.connected(xyTo1D(row, col), xyTo1D(0, i))) return true;
            }

        } return false;
    }

    // number of open sites
    public int numberOfOpenSites()  {
        return numOpen;
    }


    // does the system percolate?
    public boolean percolates()  {
        for (int i = 0; i < N; i ++) {
            if (isFull(N - 1, i)) return true;
        }return false;
    }

    // use for unit testing (not required)
    public static void main(String[] args) {

    }


}
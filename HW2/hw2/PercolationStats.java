package hw2;


import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    int N;
    int t;
    double [] open;


    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        t = T;
        this.N = N;
        if (N <= 0 | T <= 0) throw new IllegalArgumentException("N or T is below 0.");
        double[] open = new double[T];
        for(int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while(!p.percolates()) {
                int xy = StdRandom.uniform(0, N * N - 1);
                p.open(xy    / N, xy % N);
            }
            int op = p.numberOfOpenSites();
            open[i] = op;
        }
        this.open = open;
    }


    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(open)/(N*N);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(open);
    }


    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean()-1.96*stddev()/Math.sqrt(t);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean()+1.96*stddev()/Math.sqrt(t);
    }

    public static void main(String[] args) {
        PercolationFactory p = new PercolationFactory();
        PercolationStats a = new PercolationStats(20, 30, p);
        System.out.println(a.mean());
    }
}
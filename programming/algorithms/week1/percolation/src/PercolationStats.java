import java.util.List;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] count;
    private int times;

    public PercolationStats(int n, int trails) {
        if (n <= 0 || trails <= 0) {
            throw new IllegalArgumentException();
        }

        times = trails;
        count = new double[trails];
        int maxIndex = n * n - 1;

        for (int i = 0; i < trails; i++) {
            Percolation percolation = new Percolation(n);
            boolean[] indexUsed = new boolean[n * n];
            int countPerRound = 0;
            while (!percolation.percolates()) {
                int index = StdRandom.uniform(maxIndex + 1);
                if (!indexUsed[index]) {
                    indexUsed[index] = true;
                    int row = index / n + 1;
                    int col = index % n + 1;
                    countPerRound++;
                    percolation.open(row, col);
                }
            }
            count[i] = countPerRound * 1.0 / (n * n);
        }
        
    }

    public double mean() {
        return StdStats.mean(count);
    }

    public double stddev() {
        return StdStats.stddev(count);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(times);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(times);
    }

    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
        System.out.println(String.format("mean = %s", stats.mean()));
        System.out.println(String.format("stddev = %s", stats.stddev()));
        System.out.println(String.format("95%% confidence interval = [%s, %s]", stats.confidenceLo(), stats.confidenceHi()));
    }
}

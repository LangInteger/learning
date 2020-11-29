import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Creat a UnionFind with size n * n - 1 + 2
 * 
 *   - 0 - n*n-1 positions represent every cell in the n*n grid
 *   - n*n position represents a cell connnetced to first row of cells initially
 *   - n*n+1 position represents a cell connected to last row of cells initially
 *
 * To say if the whole grid is percolated, just by checking is connected(n * n, n * n + 1) == true
 */
public class Percolation {

    private int size;
    private boolean[] cellStatus;
    private int openCellCount = 0;
    private int header;
    private int tailer;
    private WeightedQuickUnionUF unionFind;
    private WeightedQuickUnionUF unionFindNonBotoom;
    private int unionFindNonBotoomSize;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        
        size = n;
        cellStatus = new boolean[n * n + 2];
        // Mark all cells as not open
        for (int i = 0; i < cellStatus.length; i++) {
            cellStatus[i] = false;
        }
        cellStatus[n * n] = true;
        cellStatus[n * n + 1] = true;

        header = n * n;
        tailer = n * n + 1;

        unionFind = new WeightedQuickUnionUF(n * n + 2);
        unionFindNonBotoomSize = n * n + 1;
        unionFindNonBotoom = new WeightedQuickUnionUF(unionFindNonBotoomSize);
    }

    private int locationToIndex(int row, int col) {
        return (row - 1) * size + (col - 1);
    }

    private int[] getNeighbors(int row, int col) {
        int[] neighbors = new int[4];
        if (row != 1) {
            neighbors[0] = locationToIndex(row - 1, col);
        } else {
            // Cells of first line is neighbor of header
            neighbors[0] = header;
        }
        if (row != size) {
            neighbors[1] = locationToIndex(row + 1, col);
        } else {
            // Cells of last line is neighbor of tailer
            neighbors[1] = tailer;
        }
        if (col != 1) {
            neighbors[2] = locationToIndex(row, col - 1);
        } else {
            neighbors[2] = -1;
        }
        if (col != size) {
            neighbors[3] = locationToIndex(row, col + 1);
        } else {
            neighbors[3] = -1;
        }
        return neighbors;
    }

    private void checkParams(int row, int col) {
        if (row < 1 || row > size || col < 1 || col > size) {
            throw new IllegalArgumentException();
        }
    }

    public void open(int row, int col) {
        checkParams(row, col);
        int index = locationToIndex(row, col);
        if (!cellStatus[index]) {
            openCellCount = openCellCount + 1;
            cellStatus[index] = true;
            int[] neighbors = getNeighbors(row, col);
            for (int i = 0; i < neighbors.length; i++) {
                int candidate = neighbors[i];
                if (candidate != -1 && cellStatus[candidate]) {
                    unionFind.union(candidate, index);
                    if (candidate < unionFindNonBotoomSize) {
                        unionFindNonBotoom.union(candidate, index);
                    }
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        checkParams(row, col);
        int index = locationToIndex(row, col);
        return cellStatus[index];
    }

    public boolean isFull(int row, int col) {
        checkParams(row, col);
        int index = locationToIndex(row, col);
        return unionFindNonBotoom.find(index) == unionFindNonBotoom.find(header);
    }

    public int numberOfOpenSites() {
        return openCellCount;
    }

    public boolean percolates() {
        return unionFind.find(header) == unionFind.find(tailer);
    }
}

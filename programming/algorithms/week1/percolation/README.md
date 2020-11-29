# Percolation

The `Percolation.java` models the status of a n-by-n grid of sites. Each site is either open or blocked. A full site that can be connected to an open site in the top row via open sites. If there is a full site in the bottom row, we say the system percolates. 

The `PercolationStats.java` models the `Monte Carlo simulation`, which is designed to estimate the percolation shreshold by playing independent `trails` times process of finding the shreshold of an `n*n` grid, and gives the mean, standard deviation, 95% confidence interval as result.

For more information, refer to [the official percolation specification](https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php)

## Compile & Run

The whole `programming/algorithms` is managed by [mill](https://github.com/lihaoyi/mill), but you still can compile and run above java code manually.


```shell
# currently at algorithms/week1
cd percolation/src
javac -cp "../../../lib/*" Percolation.java PercolationStats.java PercolationVisualizer.java
java -cp "../../../lib/*:." PercolationStats 200 100
java -cp "../../../lib/*:." PercolationVisualizer ./resources/eagle25.txt

```

/**
 * Modify quick-union to avoid tall trees
 * Keep track of size of each tree (number of objects)
 * 
 * Find: takes time proportional to depth of p and q
 * Union: takes constant time, given root
 * 
 * Depth of any node x is at most lgN(log 2 based N)
 * - The size of the tree containing x at least doubles since |T2| >= |T1|
 * - Size of tree containing x can double at most lgN times.
 * - Because if you start with one and double lgN times, you get N and there's only N ndoes in the tree
 */
public class SpecificCanonicalElementWeightedQuickUnion {
    private int[] id;
    private int[] sz;
    private int[] maxValueOfComponent;

    public SpecificCanonicalElementWeightedQuickUnion(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        for (int i = 0; i < sz.length; i++) {
            sz[i] = 1;
        }
        for (int i = 0; i < maxValueOfComponent.length; i++) {
            maxValueOfComponent[i] = id[i];
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            // for path compression
            id[i] = id[id[i]];

            i = id[i];
        }
        return i;
    }
    
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);

        if (pRoot == qRoot) {
            return;
        }

        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];

            if (maxValueOfComponent[qRoot] < maxValueOfComponent[pRoot]) {
                maxValueOfComponent[qRoot] = maxValueOfComponent[pRoot];
            }
        } else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];

            if (maxValueOfComponent[pRoot] < maxValueOfComponent[qRoot]) {
                maxValueOfComponent[pRoot] = maxValueOfComponent[qRoot];
            }
        }
    }

    public int find(int i) {
        int root = root(i);
        return maxValueOfComponent[root];
    }
}
public class QuickFind {
    private int[] id;

    public QuickFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    /**
     * Find is a O(1) operation
     */
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    /**
     * Union is a O(N) operation
     * Union is too expensive
     * Union takes quadratic N array accesses to process a sequence of N union commands on N objects
     */
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i ++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }
}
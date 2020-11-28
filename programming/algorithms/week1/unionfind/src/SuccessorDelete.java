public class SuccessorDelete {

    private int[] ids;
    private int[] successor;
    private int[] predecessor;

    public SuccessorDelete(int n) {
        ids = new int[n];
        successor = new int[n];
        predecessor = new int[n];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
        }
        for (int i = 0; i < successor.length; i++) {
            successor[i] = i + 1;   
        }
        for (int i = 0; i < predecessor.length; i++) {
            predecessor[i] = i - 1;
        }
    }

    public void delete(int i) {
        if (ids[i] == -1) {
            return;
        } else {
            ids[i] = -1;
            successor[predecessor[i]] = successor[i];
            predecessor[successor[i]] = predecessor[i];
        }
    }

    public int find(int i) {
        return successor[i];
    }
    
}

        
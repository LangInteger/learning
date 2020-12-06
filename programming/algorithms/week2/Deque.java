import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *      last          first
 * x  x null     null  x
 * 0  1  2  ...  n-2  n-1
 */
public class Deque<Item> implements Iterable<Item> {

    private static final int INIT_CAPACITY = 8;

    private Item[] q;
    private int size;
    private int first;
    private int last;


    public Deque() {
        q = (Item[]) new Object[INIT_CAPACITY];
        size = 0;
        first = INIT_CAPACITY - 1;
        last = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int capacity) {
        assert capacity >= size;
        Item[] copy = (Item[]) new Object[capacity];
        
        if (first < last) {
            for (int i = first; i <= last; i++) {
                copy[i] = q[i];
            }
        } else {
            for (int i = 0; i < last; i++) {
                copy[i] = q[i];
            }
            for (int i = first; i < q.length; i++) {
                int index = capacity - q.length + i;
                copy[index] = q[i];
            }
            first = first + capacity - q.length;
        }
        q = copy;
    }

    public void addFirst(Item item) {
        assertItemNotNull(item);
        if (size == q.length) {
            resize(2 * q.length);
        }

        int newFirst = (first + q.length - 1) % q.length;
        q[newFirst] = item;
        first = newFirst;
        size++;
    }

    public void addLast(Item item) {
        assertItemNotNull(item);
        if (size == q.length) {
            resize(2 * q.length);
        }

        q[last] = item;
        last = (last + 1) % q.length;
        size++;
    }

    public Item removeFirst() {
        assertDequeNotEmpty();
        Item item = q[first];
        q[first] = null;

        size--;
        first = (first + 1) % q.length;

        if (size > 0 && size == q.length / 4) {
            resize(q.length / 2);
        }

        return item;
    }

    public Item removeLast() {
        assertDequeNotEmpty();

        int trueLast = (last + q.length - 1) % q.length;
        Item item = q[trueLast];
        q[trueLast] = null;

        size--;
        last = trueLast;

        if (size > 0 && size == q.length / 4) {
            resize(q.length / 2);
        }

        return item;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        public boolean hasNext() {
            return i < size;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = q[(i + first) % q.length];
            return item;
        }
    }

    private void assertItemNotNull(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    private void assertDequeNotEmpty() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
    }

    private String print() {
        String result = "";
        for (Item item : this) {
            result = result + item.toString();
        }
        return result;
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque();

        deque.addFirst("A");
        deque.addFirst("B");
        deque.addFirst("C");
        deque.addLast("D");
        deque.addLast("E");

        System.out.println(String.format("Is deque empty. Estimate: false Reality: %s", deque.isEmpty()));
        System.out.println(String.format("Deque content. Estimate: DCBAE Reality: %s", deque.print()));
    }
}
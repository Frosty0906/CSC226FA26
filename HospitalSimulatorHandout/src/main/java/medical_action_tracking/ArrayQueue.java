package medical_action_tracking;

// EXTENSION: queue implementation comparison
// second queue that uses an array instead of linked nodes, same methods as LinkedQueue
// it's "circular" because when front or rear hit the end of the array they wrap back to index 0
public class ArrayQueue<T> {
    // how many spots the array starts with
    private static final int STARTING_CAPACITY = 4;

    // the array that holds the queue items
    private T[] items;
    // index of the item that comes out next
    private int front;
    // index of the next open spot where a new item goes
    private int rear;
    // how many items are in the queue right now
    private int size;

    // java won't let you do new T[], so I make an Object array and cast it to T[]
    // the SuppressWarnings just hides the yellow warning about that cast, it doesn't change anything
    // had to look up the syntax for this 
    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        items = (T[]) new Object[STARTING_CAPACITY];
        front = 0;
        rear = 0;
        size = 0;
    }

    public void enqueue(T item) {
        // rejects null the same way LinkedQueue does
        if (item == null) {
            throw new IllegalArgumentException("Null items are not allowed.");
        }

        // if every spot is full, make the array bigger first
        if (size == items.length) {
            resize();
        }

        // puts the item in the open spot at rear
        items[rear] = item;

        // moves rear to the next spot (wraps to 0 if it's at the end)
        rear = nextIndex(rear);

        // increase size bc the queue has a new item
        size++;
    }

    public T dequeue() {
        // nothing to remove if it's empty
        if (isEmpty()) {
            return null;
        }

        // grabs the item at the front
        T data = items[front];

        // clears that spot so the old item isn't left sitting in the array
        items[front] = null;

        // moves front to the next item (wraps to 0 if it's at the end)
        front = nextIndex(front);

        // decrease size bc an item left
        size--;

        return data;
    }

    public T peekFront() {
        // nothing to look at if it's empty
        if (isEmpty()) {
            return null;
        }
        // just returns the front item without removing it
        return items[front];
    }

    public boolean isEmpty() {
        // size is already being tracked so if it's 0 the queue is empty
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        // same format as LinkedQueue: [] or [a, b, c]
        StringBuilder result = new StringBuilder("[");

        // starts at the front since that's where the queue starts
        int index = front;

        // visits each item once, in queue order
        for (int i = 0; i < size; i++) {
            result.append(items[index]);

            // adds a comma and space after every item except the last one
            if (i < size - 1) {
                result.append(", ");
            }

            // moves to the next item (wraps to 0 if it's at the end)
            index = nextIndex(index);
        }

        result.append("]");
        return result.toString();
    }

    // makes a new array twice as big and copies everything over in queue order
    // same SuppressWarnings reason as the constructor
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] bigger = (T[]) new Object[items.length * 2];

        // starts at the front of the old array since that's where the queue starts
        int oldIndex = front;

        // copies each item into the new array starting at index 0
        for (int i = 0; i < size; i++) {
            bigger[i] = items[oldIndex];

            // moves to the next item in the old array (wraps to 0 if it's at the end)
            oldIndex = nextIndex(oldIndex);
        }

        // swaps the old array out for the new bigger one
        items = bigger;

        // the queue starts at index 0 in the new array now
        front = 0;

        // rear is the next open spot, which is right after the last item copied
        rear = size;
    }

    // helper that gives the next index in the array
    // if it's at the last spot, it wraps back around to 0, the circular thing 
    private int nextIndex(int index) {
        index++;
        if (index == items.length) {
            index = 0;
        }
        return index;
    }
}

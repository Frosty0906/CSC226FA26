package medical_action_tracking;

public class LinkedStack<T> {
    private class Node {
        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
        }
    }

    private Node top;
    private int size;

    public void push(T item) {
        // TODO: Reject null items, then link a new node at the top and update size.
    }

    public T pop() {
        // TODO: Remove and return the top item, or return null when empty.
        return null;
    }

    public T peek() {
        // TODO: Return the top item without removing it, or null when empty.
        return null;
    }

    public boolean isEmpty() {
        // TODO: Determine whether the stack contains any items.
        return false;
    }

    public int size() {
        // TODO: Return the number of stacked items.
        return 0;
    }

    @Override
    public String toString() {
        // TODO: Build [top, next, ...] by traversing the stack without changing it.
        return "[]";
    }
}

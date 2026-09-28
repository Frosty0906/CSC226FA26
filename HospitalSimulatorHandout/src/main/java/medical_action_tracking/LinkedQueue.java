package medical_action_tracking;

public class LinkedQueue<T> {
    private class Node {
        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
        }
    }

    private Node front;
    private Node rear;
    private int size;

    public LinkedQueue() {
        // TODO: Initialize an empty queue.
    }

    public void enqueue(T item) {
        // TODO: Add a node at the rear. Update both references when the queue is empty.
    }

    public T dequeue() {
        // TODO: Remove and return the front item, or return null if empty.
        // TODO: When removing the last item, make both front and rear null.
        return null;
    }

    public T peekFront() {
        // TODO: Return the front item without removing it, or null if empty.
        return null;
    }

    public boolean isEmpty() {
        // TODO: Determine whether the queue contains any items.
        return false;
    }

    public int size() {
        // TODO: Return the number of queued items.
        return 0;
    }

    @Override
    public String toString() {
        // TODO: Recursively build a string from front to rear without changing the queue.
        return "[]";
    }

    private void appendNodesRecursively(Node current, StringBuilder result) {
        // TODO: Add the current node's data, then recursively visit current.next.
        // TODO: Stop at the null-node base case.
    }
}

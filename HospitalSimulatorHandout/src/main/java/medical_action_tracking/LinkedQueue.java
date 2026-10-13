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
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(T item) {
        // TODO: Add a node at the rear. Update both references when the queue is empty.

        // rejects null so it passes the test case 
        if (item == null) {
            throw new IllegalArgumentException("Null items are not allowed.");
        }

        Node newNode = new Node(item);
        if (isEmpty()) {
            // if the queue is empty, both front and rear should point to the new node
            front = newNode;
            rear = newNode;
        } else {
            // if the queue is not empty, add the new node to the end and update rear
            rear.next = newNode;
            rear = newNode;
        }
        // increase size bc the queue has a new item
        size++;
    }

    public T dequeue() {
        // TODO: Remove and return the front item, or return null if empty.
        // TODO: When removing the last item, make both front and rear null.
        if (!isEmpty()) {
            T data = front.data;
            front = front.next;
            size--;
            if (isEmpty()) {
                rear = null;
                front = null;
            }
            return data;
        }
        return null;
    }

    public T peekFront() {
        // TODO: Return the front item without removing it, or null if empty.
        if (!isEmpty()) {
            return front.data;
        }
        return null;
    }

    public boolean isEmpty() {
        // TODO: Determine whether the queue contains any items.
        if (front == null) {
            return true;
        }
        return false;
    }

    public int size() {
        // TODO: Return the number of queued items.
        // size is already being tracked so i just returned it here 
        return size;
    }

    @Override
    public String toString() {
        // TODO: Recursively build a string from front to rear without changing the queue.

        // used a stringbuilder to build the string and started the recursive function with the front node and the stringbuilder
        StringBuilder result = new StringBuilder("[");

        // used appendNodesRecursively to build the string representation of the queue since it is a recursive function that will traverse the queue and append the data of each node to the stringbuilder
        appendNodesRecursively(front, result);
        result.append("]");
        return result.toString();

    }

    private void appendNodesRecursively(Node current, StringBuilder result) {
        // TODO: Add the current node's data, then recursively visit current.next.
        // TODO: Stop at the null-node base case.

        // base case: if the current node is null, return 
        if (current == null) {
            return;
        }
        // recursivly appends the data of the current node to the stringbuilder and if the next node is not null, it appends a comma and space before calling the function again with the next node
        result.append(current.data);
        if (current.next != null) {
            result.append(", ");
        }
        appendNodesRecursively(current.next, result);
    }
}

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
        if (item == null) {
            throw new IllegalArgumentException("Null items are not allowed.");
        }
        // make a new node with item 
        Node newNode = new Node(item);
        // new node's next points at the stacks current top node
        newNode.next = top;
        // now the new node is the top of the stack
        top = newNode;
        // increase size bc the stack has a new item
        size++;
    }

    public T pop() {
        // TODO: Remove and return the top item, or return null when empty.
        if (!isEmpty()) {
            // store the data of the top node to return it
            T data = top.data;
            // update the top to point to the next node in the stack
            top = top.next;
            // decrease size bc the stack has one less item
            size--;
            return data;
        }
        return null;
    }

    public T peek() {
        // TODO: Return the top item without removing it, or null when empty.
        if (!isEmpty()) {
            return top.data;
        }
        return null;
    }

    public boolean isEmpty() {
        // TODO: Determine whether the stack contains any items.
        if (top == null) {
            return true;
        }
        return false;
    }

    public int size() {
        // TODO: Return the number of stacked items.
        // size is already being tracked so i just returned it here
        return size;
    }

    @Override
    public String toString() {
        // TODO: Build [top, next, ...] by traversing the stack without changing it.

        // used a stringbuilder to build the string representation of the stack
        StringBuilder result = new StringBuilder("[");

        // sets current node to the top 
        Node current = top;

    
        while (current != null) {
            // appends data of curent node to the stringbuilder and if the next node is not null, it appends a comma and space before moving to the next node
            result.append(current.data);
            if (current.next != null) {
                result.append(", ");
            }
            current = current.next;
        }
        result.append("]");
        return result.toString();
    }
}

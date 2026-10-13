package week5;

public class LinkedQueue<T> implements QueueInterface<T> {
    private LLNode<T> front;
    private LLNode<T> rear;

    @Override
    public void enqueue(T element) {
        LLNode<T> newNode = new LLNode<>(element);
        if (isEmpty()) {
            // makes the front and the rear reference point to the new node
            front = newNode;
            rear = newNode;
        } else {
            // updates the rear node's next reference to point to the new node
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Q is mpty");
            return null;
        }
        else {
            T removedElement = front.getInfo();
            front = front.getNext();
            if (front == null) {
                rear = null;
            }
            return removedElement;
        }
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }
}
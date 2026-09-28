package week5;

public class LinkedQueue<T> implements QueueInterface<T> {
    private LLNode<T> front;
    private LLNode<T> rear;

    @Override
    public void enqueue(T element) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public T dequeue() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
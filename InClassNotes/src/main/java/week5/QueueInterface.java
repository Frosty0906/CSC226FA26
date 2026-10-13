package week5;

public interface QueueInterface<T> {
    void enqueue(T element);
    // adds element to the rear of this queue.
    T dequeue();
    //removes front element from this queue and returns it.
    boolean isFull();
    // Returns true if this queue is full; otherwise, returns false.
    boolean isEmpty();
    // returns false if que is empty
}
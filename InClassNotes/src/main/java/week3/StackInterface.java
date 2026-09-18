package week3;

public interface StackInterface<T> {
    //places element at the top of the stack
    void push(T element);
    //removes element from the top of the stack
    void pop();
    //returns element at the top of the stack
    T top();
    boolean isFull();
    boolean isEmpty();
}
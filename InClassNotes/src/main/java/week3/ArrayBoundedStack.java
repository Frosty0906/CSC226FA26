package week3;

public class ArrayBoundedStack<T> implements StackInterface<T> {
    private final int DEFAULTCAP = 100;
    private T[] elements;
    private int topIndex=-1;

    public ArrayBoundedStack(){
        elements = (T[]) new Object[DEFAULTCAP];
    }
    public ArrayBoundedStack(int maxSize){
        elements = (T[]) new Object[maxSize];
    }
    public boolean isEmpty()
    {
        //TODO: how do we determine if the stack is empty? Implement this method
        return false;
    }

    public boolean isFull()
    {
        //TODO: How do we determine if the stack is full? Implement this method
        return false;
    }
    
    public void push(T element)
    {
       // TODO: How do we add an element to the stack? Implement this method
    }
    
    public void pop()
    {
        // How do we remove an element from the stack? Implement this method
    }
    
    public T top()
    {
      // How do we return the top element of the stack without removing it? Implement this method
      return null;   
    }
}
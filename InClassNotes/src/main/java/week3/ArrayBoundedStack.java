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
        // default value of top index is -1 so if it's -1 then it's empty
        if (topIndex == -1) {
            return true;
        }
        return false;
    }

    public boolean isFull()
    {
        //TODO: How do we determine if the stack is full? Implement this method
        // if it's the top index is the same as the length of elements (-1 since it starts at 0) then it's full
        if (topIndex == elements.length - 1) {
            return true;
        }
        return false;
    }
    
    public void push(T element)
    {
        // TODO: How do we add an element to the stack? Implement this method
        if (!isFull()) {
            topIndex++;
            elements[topIndex] = element;
        }
    }
    
    public void pop()
    {
        // How do we remove an element from the stack? Implement this method
        if (!isEmpty()) {
            elements[topIndex] = null; 
            topIndex--;
        }
    }
    
    public T top()
    {
      // How do we return the top element of the stack without removing it? Implement this method
      if (!isEmpty()) {
          return elements[topIndex];
      }
      return null;   
    }
}
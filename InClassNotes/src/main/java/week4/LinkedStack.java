package week4;
import week3.StackInterface;

public class LinkedStack<T> implements StackInterface<T> {
    private LLNode<T> top;

    public LinkedStack(){
        this.top=null;
    }

    public void push(T element){
        //Push an element to the top of the stack
        LLNode<T> newNode = new LLNode<>(element);
        newNode.setNext(top);
        top = newNode;

    }
    public void pop(){
        //remove an element from the top of the stack
        //note: what preconditions do we care about?
        if (!isEmpty()) {
            top = top.getNext();
        }
    }
    public T top(){
        //return the data in the element from the top of the stack
        //note: what preconditions do we care about?
        if (isEmpty()) {
            return null; // or throw an exception
        }
        return top.getInfo();   
    }

    public boolean isEmpty(){
        if (top == null) {
            return true;
        }
        return false;
    }
    public boolean isFull(){
        if (top == null) {
            return false;
        }
        return false;
    }
}
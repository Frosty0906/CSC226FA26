package week4;

public class LLNode<T> {
    T info;
    LLNode<T> next;

    public LLNode(T info){
        this.info = info;
        this.next = null;
    }

    public void setNext(LLNode<T> next){
        //set the next node in the chain
        this.next = next;
    }
    public LLNode<T> getNext(){
       return next; 
    }
    public void setInfo(T info){
        //set the nodes info
        this.info = info;
    }
    public T getInfo(){
        return info;
    }
}
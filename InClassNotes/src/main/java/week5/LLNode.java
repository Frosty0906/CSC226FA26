package week5;

public class LLNode<T> {
    private T info;
    private LLNode<T> next;

    public LLNode(T info) {
        this.info = info;
    }

    public void setNext(LLNode<T> next) {
        this.next = next;
    }

    public LLNode<T> getNext() {
        return next;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public T getInfo() {
        return info;
    }
}
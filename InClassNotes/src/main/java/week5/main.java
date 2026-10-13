package week5;

public class main {
    public static void main(String[] args) {
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);

        // see if full
        System.out.println(queue.isFull());

        // dequeue
        System.out.println(queue.dequeue()); 
        System.out.println(queue.dequeue()); 
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        // is empty
        System.out.println(queue.isEmpty()); 

        // dequeue the last element 
        System.out.println(queue.dequeue()); 

        // check empty again
        System.out.println(queue.isEmpty()); 

        // dq from an empty queue
        System.out.println(queue.dequeue());

        // try and see if full
        System.out.println(queue.isFull());
    }
}
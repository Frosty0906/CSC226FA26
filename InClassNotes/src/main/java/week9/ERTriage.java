package week9;

class Patient {
    String name;
    int severity;

    Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
    }

    @Override
    public String toString() {
        return name + "(" + severity + ")";
    }
}

class HeapPQ {
    private Patient[] heap;
    private int lastIndex = -1;

    HeapPQ(int capacity) {
        heap = new Patient[capacity];
    }

    boolean isEmpty() {
        return lastIndex == -1;
    }

    private void swap(int a, int b) {
        Patient temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    // TODO Round 1: insert p in the next open slot, then restore the max-heap.
    void enqueue(Patient p) {
        if (lastIndex == heap.length -1) {
            throw new IllegalStateException("it is full");
        }
        lastIndex ++;
        heap[lastIndex] = p;
        reheapUp(lastIndex);
    }

    // TODO Round 1: swap upward while a patient's severity is greater than its parent's.
    // Parent of i: (i - 1) / 2
    private void reheapUp(int i) {
        while (i>0) {
            int parent = (i - 1) /2;
            if(heap[i].severity <= heap[parent].severity) {
                break;
            }

            swap(i, parent);
            i = parent;
            

        }
    }

    // TODO Round 2: save the root, move the last patient to the root, shrink, and restore the heap.
    Patient dequeue() {
        if (!isEmpty()) {
            Patient root = heap[0];
            heap[0] = heap[lastIndex];
            heap[lastIndex] = null;
            lastIndex--;
            
            if (!isEmpty()) {
            reheapDown(0);
            }
    
            return root;
        }

        return null;

    }

    // TODO Round 2: swap down with the larger child while the max-heap property is broken.
    // Children of i: 2 * i + 1 and 2 * i + 2
    private void reheapDown(int i) {
        while (true) {
            int left = 2*i+1;
            int right = 2*i+2;
            int largest = i;

            if (left <= lastIndex && heap[left].severity > heap[largest].severity) {
                largest = left;
            }

            if (right <= lastIndex && heap[right].severity > heap[largest].severity) {
                largest = right;
            }

            if (largest == i) {
                break;
            }

            swap(i, largest);
            i = largest;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i <= lastIndex; i++) {
            result.append(heap[i]);
            if (i < lastIndex) {
                result.append(", ");
            }
        }
        return result.append("]").toString();
    }
}

/**
 * Week 9 team activity: ER triage with a max-heap priority queue.
 * Work in pairs: one person drives while the other traces the heap and checks
 * the result. Switch roles after Round 1.
 */
public class ERTriage {
    public static void main(String[] args) {
        HeapPQ er = new HeapPQ(20);
        String[] names = {"Ana", "Ben", "Cruz", "Dee", "Eli", "Fay", "Gus", "Hal"};
        int[] severities = {3, 7, 5, 9, 2, 8, 6, 1};

        for (int i = 0; i < names.length; i++) {
            er.enqueue(new Patient(names[i], severities[i]));
        }

        System.out.println("CHECKPOINT 1: " + er);
        System.out.println("Treating: " + er.dequeue());
        System.out.println("Treating: " + er.dequeue());
        System.out.println("Ambulance arrives: Ivy(10)");
        er.enqueue(new Patient("Ivy", 10));

        while (!er.isEmpty()) {
            System.out.println("Treating: " + er.dequeue());
        }
    }
}

package week4;

import java.util.ArrayList;

public class LinkedListExamples {
    public static void main(String[] args){
        // Create a sample linked list for testing
        LLNode<String> head = new LLNode<>("First");
        head.setNext(new LLNode<>("Second"));
        head.getNext().setNext(new LLNode<>("Third"));
        head.getNext().getNext().setNext(new LLNode<>("Fourth"));
        
        // Test your functions here
        System.out.println("Original List:");
        displayWithPositions(head);

        System.out.println("\nRemoving 'Second':");
        head = removeElement(head, "Second");
        displayWithPositions(head);

        System.out.println("\nRemoving all elements:");
        head = removeAllElements(head);
        displayWithPositions(head);

        System.out.println("\nCopying list:");
        head = new LLNode<>("First");
        head.setNext(new LLNode<>("Second"));
        LLNode<String> copiedList = copyList(head);
        displayWithPositions(copiedList);

        System.out.println("\nChecking if 'First' exists in the list:");
        boolean exists = contains(head, "First");
        System.out.println("Contains 'First': " + exists);

        System.out.println("\nConverting list to ArrayList:");
        ArrayList<String> arrayList = toArrayList(head);
        System.out.println(arrayList);

        System.out.println("\nGetting element at position 1:");
        String elementAtPosition = getElementAt(head, 1);
        System.out.println("Element at position 1: " + elementAtPosition);
    }
    
    
    /**
     * 2. Program to display both elements and their position in a linked list
     * Shows: Position 0: "First", Position 1: "Second", etc.
     */
    public static <T> void displayWithPositions(LLNode<T> head) {
        // TODO: Implement this function
        // hint: Use a counter variable and traverse the list   
        int ctr = 0;
        LLNode<T> current = head;
        // last node's next is null so that is the stopping condition
        while (current != null) {
            System.out.println("Position " + ctr + ": " + current.getInfo());
            current = current.getNext();
            ctr++;
        }
    }
    
    /**
     * 3. Program to remove a specified element from a linked list
     * Returns the new head of the list (important if first element is removed)
     */
    public static <T> LLNode<T> removeElement(LLNode<T> head, T target) {
        // TODO: Implement this function
        // Handle special case: removing the first element
        // For other elements: find the node before the target
        if (head == null) {
            return null; // empty list
        }
        if (head.getInfo().equals(target)) {
            return head.getNext(); 
        }

        LLNode<T> current = head;

        while (current.getNext() != null) {
            if (current.getNext().getInfo().equals(target)) {

                current.setNext(current.getNext().getNext());
                return head; // return the new head
            }
            current = current.getNext();

        }
        
        return head; // element not found
    }
    
    /**
     * 4. Program to remove all elements from a linked list
     * Returns null (empty list)
     */
    public static <T> LLNode<T> removeAllElements(LLNode<T> head) {
        // loop through the list and set each node's next to null
        LLNode<T> current = head;
        while (current != null) {
            LLNode<T> nextNode = current.getNext();
            current.setNext(null); // disconnect the node
            current = nextNode;
        }
        return null; // empty list
    }
    
    /**
     * 5. Program to copy a linked list to another linked list
     * Creates a completely new list with the same values
     */
    public static <T> LLNode<T> copyList(LLNode<T> original) {
        // TODO: Implement this function
        // Create new nodes for each element in the original list
        if (original == null) {
            return null; // empty list
        }
        LLNode<T> newHead = new LLNode<>(original.getInfo());
        LLNode<T> currentOriginal = original.getNext();
        LLNode<T> currentNew = newHead;

        while (currentOriginal != null) {
            currentNew.setNext(new LLNode<>(currentOriginal.getInfo()));
            currentOriginal = currentOriginal.getNext();
            currentNew = currentNew.getNext();
        }

        return newHead;
    }
    
    /**
     * 6. Program to check if a particular element exists in a linked list
     * Returns true if found, false otherwise
     */
    public static <T> boolean contains(LLNode<T> head, T target) {
        // TODO: Implement this function
        // Traverse the list and compare each element with target
        LLNode<T> current = head;
        while (current != null) {
            if (current.getInfo().equals(target)) {
                return true; // found
            }
            current = current.getNext();
        }
        return false; // not found
    }
    
    /**
     * Helper function to calculate the length of a linked list
     * Useful for other operations
     */
    public static <T> int getLength(LLNode<T> head) {
        // TODO: Implement this helper function
        int length = 0;
        LLNode<T> current = head;
        while (current != null) {
            length++;
            current = current.getNext();
        }
        return length;
    }

    /**
     * 7. Program to convert a linked list to an array list
     * Returns an ArrayList containing all elements in the same order
     */
    public static <T> ArrayList<T> toArrayList(LLNode<T> head) {
        // TODO: Implement this function
        // Create ArrayList and add each element from the linked list

        // make the array list to hold the elements
        ArrayList<T> arrayList = new ArrayList<>();


        LLNode<T> current = head;
        while (current != null) {
            arrayList.add(current.getInfo());
            current = current.getNext();
        }
        return arrayList;
    }
        
    /**
     * Helper function to get the element at a specific position
     * Returns null if position is out of bounds
     */
    public static <T> T getElementAt(LLNode<T> head, int position) {
        // TODO: Implement this helper function
        if (position < 0) {
            return null; // invalid position
        }

        LLNode<T> current = head;
        int ctr = 0;
        while (current != null) {
            if (ctr == position) {
                return current.getInfo();
            }
            current = current.getNext();
            ctr++;
        }
        return null; // position out of bounds
    }
}

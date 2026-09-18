package week3;

public class Main {
    public static void main(String[] args) {
        ArrayBoundedStack<String> stack = new ArrayBoundedStack<>(3);

        System.out.println("Empty at start: " + stack.isEmpty());

        stack.push("A");
        stack.push("B");
        stack.push("C");

        System.out.println("Top is: " + stack.top());
        System.out.println("Empty: " + stack.isEmpty());
        System.out.println("Full: " + stack.isFull());

        stack.pop();
        System.out.println("Top after pop: " + stack.top());

        stack.pop();
        stack.pop();
        System.out.println("Empty after all are popped: " + stack.isEmpty());
        System.out.println("Top on empty: " + stack.top());
    }
}
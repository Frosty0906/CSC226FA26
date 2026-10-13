package week4;

public class main {
    public static void main(String[] args) {
        System.out.println(Recursion.countHi("XHIZXXZHI"));
        System.out.println(Recursion.countHi("XHI"));
        System.out.println(Recursion.countHi("XHIXHI"));

        LLNode<String> node1 = new LLNode<>("A");
        LLNode<String> node2 = new LLNode<>("B");
        LLNode<String> node3 = new LLNode<>("C");
        node1.setNext(node2);
        node2.setNext(node3);
        Recursion.iterativePrinter(node1);

        Recursion.recursivePrinter(node1);
    }
}

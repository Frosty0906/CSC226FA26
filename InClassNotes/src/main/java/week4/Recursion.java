package week4;


public class Recursion {
    public int fib(int n){
        if (n <= 1) {
            return n;
        }
        int answer = fib(n - 1) + fib(n-2);
        return answer;
    }

    public static Integer countHi(String value){
        if (value.length() < 2) {
            return 0; // not long enough to contain hi
        }
        if (value.substring(0, 2).equalsIgnoreCase("hi")) {
            return 1 + countHi(value.substring(1));
        }
        return countHi(value.substring(1));
    }

    public static <T> void iterativePrinter(LLNode<T> node){
        while(node !=null){
            if(node.getInfo()!=null){
                System.out.println(node.getInfo());
            }
            node=node.getNext();
        }
    }

    public static <T> void recursivePrinter(LLNode<T> node){
        System.out.println(node.getInfo());
        recursivePrinter(node.getNext());
    }

    public static <T> int recursiveCounter(LLNode<T> node, int counter){
        if(node == null){
            return counter;
        }
        counter++;
        return recursiveCounter(node.getNext(), counter);
    }

}
    
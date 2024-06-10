import java.util.*;

public class Stack {

    private Node top;

    class Node {

        int info;
        Node link;

        Node(int info, Node link) {
            this.info = info;
            this.link = link;
        }

    }

    public void push(int item) {
        top = new Node(item, top);
    }

    public int pop() {
        if (top == null) {
            System.out.println("We are cooked");
            throw new EmptyStackException();
        } else {
            int value = top.info;
            top = top.link;
            return value;
        }
    }

    public int peek() {
        if (top == null) {
            System.out.println("We are cooked");
            throw new EmptyStackException();
        } else {
            return top.info;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }
}
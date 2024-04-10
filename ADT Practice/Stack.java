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
            throw new EmptyStackException();
        } else {
            int removedElement = top.info;
            top = top.link;
            return removedElement;
        }
    }

    public int peek() {
        if (top == null) {
            throw new EmptyStackException();
        } else {
            return top.info;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void print() {
        String stringList = "[";
        for (Node current = top; current != null; current = current.link) {
            stringList += current.info;
            if (current.link != null) {
                stringList += ", ";
            }
        }
        stringList += "]";
        if (stringList.equals("[]")) {
            System.out.println("The stack is empty!");
        }
        System.out.println(stringList);
    }
}
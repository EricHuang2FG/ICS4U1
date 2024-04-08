import java.util.*;

public class Stack {

    private Node top;

    class Node {

        int element;
        Node link;

        Node(int element, Node link) {
            this.element = element;
            this.link = link;
        }
    }

    public void push(int item) {
        if (top == null) {
            top = new Node(item, null);
        } else {
            top = new Node(item, top);
        }
    }

    public int pop() {
        if (top == null) {
            throw new EmptyStackException();
        } else {
            int removedElement = top.element;
            top = top.link;
            return removedElement;
        }
    }

    public int peek() {
        if (top == null) {
            throw new EmptyStackException();
        }
        return top.element;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void print() {
        if (top == null) {
            System.out.println("The stack is empty!");
            return;
        }
        String stack = "[";
        for (Node temp = top; temp != null; temp = temp.link) {
            if (temp.link == null) {
                stack += temp.element + "]";
            } else {
                stack += temp.element + ", ";
            }
        }
        System.out.println(stack);
    }
}
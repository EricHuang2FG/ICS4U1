public class Queue {

    private Node front;
    private Node rear;

    class Node {

        int info;
        Node link;

        Node(int info, Node link) {
            this.info = info;
            this.link = link;
        }
    }


    public void enqueue(int item) {
        Node newNode = new Node(item, null);
        if (front == null && rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.link = newNode;
            rear = newNode;
        }
    }

    public int dequeue() {
        if (front == null && rear == null) {
            System.out.println("The queue is empty!");
            return 0;
        } else if (front.link == null) {
            int removedElement = front.info;
            front = null;
            rear = null;
            return removedElement;
        } else {
            int removedElement = front.info;
            front = front.link;
            return removedElement;
        }
    }

    public int peek() {
        if (front == null && rear == null) {
            System.out.println("The queue is empty!");
            return 0;
        } else {
            return front.info;
        }
    }

    public boolean isEmpty() {
        return (front == null && rear == null);
    }

    public void print() {
        String stringList = "[";
        for (Node current = front; current != null; current = current.link) {
            stringList += current.info;
            if (current.link != null) {
                stringList += ", ";
            }
        }
        stringList += "]";
        if (stringList.equals("[]")) {
            System.out.println("The queue is empty!");
        }
        System.out.println(stringList);
    }
}
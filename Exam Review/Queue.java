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
            System.out.println("Empty queue!");
            return 0;
        } else if (front == rear) {
            int value = front.info;
            front = null;
            rear = null;
            return value;
        } else {
            int value = front.info;
            front = front.link;
            return value;
        }
    }

    public int peek() {
        if (front == null && rear == null) {
            System.out.println("Empty queue!");
            return 0;
        } else {
            return front.info;
        }
    }

    public boolean isEmpty() {
        return (front == null && rear == null);
    }

}
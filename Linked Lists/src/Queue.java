public class Queue {

    private Node front, rear;

    class Node {
        
        int element;
        Node link;

        Node(int element, Node link) {
            this.element = element;
            this.link = link;
        }
    }

    public void enqueue(int item) {
        Node newElement = new Node(item, null);
        if (front == null && rear == null) {
            front = newElement;
            rear = newElement;
        } else {
            rear.link = newElement;
            rear = newElement;
        }
    }

    public String dequeue() {
        if (front == null && rear == null) {
            return "The queue is empty!";
        } else if (front.link == null) {
            int removedElement = front.element;
            front = null;
            rear = null;
            return "" + removedElement;
        } else {
            int removedElement = front.element;
            front = front.link;
            return "" + removedElement;
        }
    }

    public boolean isEmpty() {
        return (front == null && rear == null);
    }

    public String peek() {
        if (front == null && rear == null) {
            return "The queue is empty!";
        } else {
            return "" + front.element;
        }
    }

    public void print() {
        if (front == null && rear == null) {
            System.out.println("The queue is empty!");
            return;
        }
        String queue = "[";
        for (Node temp = front; temp != null; temp = temp.link) {
            if (temp.link == null) {
                queue += temp.element + "]";
            } else {
                queue += temp.element + ", ";
            }
        }
        System.out.println(queue);
    }
}
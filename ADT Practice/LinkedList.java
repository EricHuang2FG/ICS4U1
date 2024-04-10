public class LinkedList {

    private Node head;

    class Node {

        int element;
        Node link;

        Node(int element, Node link) {
            this.element = element;
            this.link = link;
        }
    }

    public void addAtFront(int item) {
        head = new Node(item, head);
    }

    public void addAtRear(int item) {
        if (head == null) {
            head = new Node(item, null);
        } else {
            for (Node current = head; current != null; current = current.link) {
                if (current.link == null) {
                    current.link = new Node(item, null);
                    break;
                }
            }
        }
    }

    public void deleteFirst() {
        if (head == null) {
            System.out.println("The list is empty!");
        } else {
            head = head.link;
        }
    }

    public void deleteLast() {
        if (head == null) {
            System.out.println("The list is empty!");
        } else if (head.link == null) {
            head = null;
        } else {
            Node previous = null;
            for (Node current = head; current != null; current = current.link) {
                if (current.link == null) {
                    previous.link = null;
                    break;
                }
                previous = current;
            }
        }
    }

    public void deleteAll(int item) {
        LinkedList newList = new LinkedList();
        for (Node current = head; current != null; current = current.link) {
            if (current.element != item) {
                newList.addAtRear(current.element);
            }
        }
        this.head = newList.head;
    }

    public void insert(int item) {
        if (head == null) {
            head = new Node(item, null);
        } else {
            Node previous = null;
            for (Node current = head; current != null; current = current.link) {
                if (current.link == null && item > current.element) {
                    current.link = new Node(item, null);
                    break;
                }
                if (item <= current.element) {
                    Node newNode = new Node(item, current);
                    if (current == head) {
                        head = newNode;
                    } else {
                        previous.link = newNode;
                    }
                    break;
                }
                previous = current;
            }
        }
    }

    public void print() {
        String stringList = "[";
        for (Node current = head; current != null; current = current.link) {
            stringList += current.element;
            if (current.link != null) {
                stringList += ", ";
            }
        }
        stringList += "]";
        if (stringList.equals("[]")) {
            System.out.println("The list is empty!");
        }
        System.out.println(stringList);
    }
}
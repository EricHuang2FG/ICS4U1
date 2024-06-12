public class List {

    private Node head;

    class Node {

        int info;
        Node link;

        Node(int info, Node link) {
            this.info = info;
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
            for (Node temp = head; temp != null; temp = temp.link) {
                if (temp.link == null) {
                    temp.link = new Node(item, null);
                    break;
                }
            }
        }
    }

    public void deleteFirst() {
        if (head == null) {
            System.out.println("Empty list, can't delete.");
        } else {
            head = head.link;
        }
    }

    public void deleteLast() {
        if (head == null) {
            System.out.println("Empty list, can't delete.");
        } else if (head.link == null) {
            head = null;
        } else {
            for (Node temp = head; temp != null; temp = temp.link) {
                if (temp.link.link == null) {
                    temp.link = null;
                    break;
                }
            }
        }
    }

    public void deleteAll(int item) {
        List newList = new List();
        for (Node temp = head; temp != null; temp = temp.link) {
            if (temp.info != item) {
                newList.addAtRear(temp.info);
            }
        }
        this.head = newList.head;
    }

    public void delete(int item) {
        if (head == null) {
            System.out.println("Can't delete, the list is empty");
        } else {
            Node previous = null;
            for (Node current = head; current != null; current = current.link) {
                if (current.info == item) {
                    if (current == head) {
                        head = head.link;
                        break;
                    } else {
                        previous.link = current.link;
                        break;
                    }
                }
                previous = current;
            }
        }
    }

    public void insert(int item) {
        if (head == null) {
            head = new Node(item, null);
        } else {
            Node previous = null;
            for (Node current = head; current != null; current = current.link) {
                if (item >= current.info && current.link == null) {
                    current.link = new Node(item, null);
                    break;
                } else if (item <= current.info) {
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
        String ans = "[";
        for (Node temp = head; temp != null; temp = temp.link) {
            ans += temp.info;
            if (temp.link != null) {
                ans += ", ";
            }
        }
        ans += "]";
        System.out.println(ans);
    }

}
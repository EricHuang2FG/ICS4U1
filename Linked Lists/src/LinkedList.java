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

    public void insertFirst(int item) {
        head = new Node(item, null);
    }

    public void insertSecond(int item) {
        head.link = new Node(item, null);
    }

    public void insertThird(int item) {
        head.link.link = new Node(item, null);
    }

    public void insertAtRear(int item) {
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
            System.out.println("The list is empty!!!!!!");
        } else {
            head = head.link;
        }
    }

    public void deleteLast() {
        if (head == null) {
            System.out.println("The list is empty");
        } else {
            for (Node temp = head; temp != null; temp = temp.link) {
                if (temp.link.link == null) {
                    temp.link = null;
                }
            }
        }
    }

    public int sum() {
        int sum = 0;
        for (Node temp = head; temp != null; temp = temp.link) {
            sum += temp.element;
        }
        return sum;
    }

    public boolean contains(int item) {
        for (Node temp = head; temp != null; temp = temp.link) {
            if (temp.element == item) {
                return true;
            }
        }
        return false;
    }

    public void deleteAll(int item) {
        for (Node temp = head; temp != null; temp = temp.link) {
            if (temp == head && temp.element == item) {
                // finish tomorrow
            }
            if (temp.link == null) {
                break;
            }
            if (temp.link.element == item) {
                temp.link = temp.link.link;
            }
        }
    }

    public String toString() {
        String str = "";
        for (Node temp = head; temp != null; temp = temp.link) {
            if (temp.link == null) {
                str += temp.element;
            } else {
                str += temp.element + "//";
            }
        }
        return str;
    }

    public void print() {
        if (head == null) {
            System.out.println("The list is empty!");
            return;
        }
        String list = "[";
        for (Node temp = head; temp != null; temp = temp.link) {
            if (temp.link == null) {
                list += temp.element + "]";
            } else {
                list += temp.element + ", ";
            }
        }
        System.out.println(list);
    }
}
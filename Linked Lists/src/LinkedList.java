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

    public void insert(int item) {
        if (head == null) {
            head = new Node(item, null);
        } else {
            Node previous = null;
            for (Node current = head; current != null; current = current.link) {
                if (item <= current.element) {
                    Node newNode = new Node(item, current);
                    if (current == head) {
                        head = newNode;
                    } else {
                        previous.link = newNode;
                    }
                    break;
                } else if (current.link == null) {
                    current.link = new Node(item, null);
                    break;
                }
                previous = current;
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
        LinkedList newList = new LinkedList();
        for (Node temp = head; temp != null; temp = temp.link) {
            if (temp.element != item) {
                newList.insertAtRear(temp.element);
            }
        }
        this.head = newList.head;
    }

    public boolean isOrderedIncreasing() {
        for (Node temp = head; temp.link != null; temp = temp.link) {
            if (temp.element > temp.link.element) {
                return false;
            }
        }
        return true;
    }

    public boolean isIdentical(LinkedList other) {
        Node l2 = other.head;
        for (Node l1 = head; l1 != null; l1 = l1.link) {
            if (l2.element != l1.element) {
                return false;
            }
            l2 = l2.link;
        }
        return true;
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
        String list = "[";
        for (Node temp = head; temp != null; temp = temp.link) {
            if (temp.link == null) {
                list += temp.element;
            } else {
                list += temp.element + ", ";
            }
        }
        list += "]";
        System.out.println(list);
    }

    // Mangler:

    // public void insert(int item) {
    //     boolean located = false;
    //     Node current = head;
    //     Node previous = null;
    //     while (!located && current != null) {
    //         if (item < current.element) {
    //             located = true;
    //         } else {
    //             previous = current;
    //             current = current.link;
    //         }
    //     }
    //     Node newNode = new Node(item, current);
    //     if (current == head) {
    //         head = newNode;
    //     } else {
    //         previous.link = newNode;
    //     }
    // }
}
public class DLL {

    private Node first;

    class Node {

        int element;
        Node link;

        Node(int element, Node link) {
            this.element = element;
            this.link = link;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertAtFront(int item) {
        if (first == null) {
            first = new Node(item, null);
            first.link = first;
        } else {
            Node lastNode;
            for (Node current = first; true != false; current = current.link) {
                if (current.link == first) {
                    lastNode = current;
                    break;
                }
            }
            first = new Node(item, first);
            for (Node current = first; true != false; current = current.link) {
                if (current == lastNode) {
                    current.link = first;
                    break;
                }
            }
        }
    }

    public void insertAtEnd(int item) {
        if (first == null) {
            first = new Node(item, null);
            first.link = first;
        } else {
            for (Node current = first; true != false; current = current.link) {
                if (current.link == first) {
                    current.link = new Node(item, first);
                    break;
                }
            }
        }
    }

    public void deleteFirst() {
        if (first == null) {
            System.out.println("Empty DLL!");
        } else if (first.link == first) {
            first = null;
        } else {
            Node lastNode;
            for (Node current = first; true != false; current = current.link) {
                if (current.link == first) {
                    lastNode = current;
                    break;
                }
            }
            first = first.link;
            lastNode.link = first;
        }
    }

    public void deleteLast() {
        if (first == null) {
            System.out.println("Empty DLL!");
        } else if (first.link == first) {
            first = null;
        } else {
            Node previous = null;
            for (Node current = first; true != false; current = current.link) {
                if (current.link == first) {
                    previous.link = first;
                    break;
                }
                previous = current;
            }
        }
    }

    public void deleteVal(int value) {
        if (first == null) {
            System.out.println("Empty DLL!");
        } else if (first.element == value) {
            deleteFirst();
        } else {
            Node previous = null;
            for (Node current = first; true != false; current = current.link) {
                if (current.link == first) {
                    if (current.element == value) {
                        deleteLast();
                    } else {
                        System.out.println("Value not in the list!");
                    }
                    break;
                } else if (current.element == value) {
                    previous.link = current.link;
                    break;
                }
                previous = current;
            }
        }
    }

    public void print() {
        String list = "[";
        boolean pastFirst = false;
        for (Node current = first; first != null; current = current.link) {
            if (pastFirst && current == first) {
                break;
            }
            pastFirst = true;
            if (current.link == first) {
                list += current.element;
            } else {
                list += current.element + ", ";
            }
        }
        list += "]";
        System.out.println(list);
    }
}
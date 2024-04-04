public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insertAtRear(1);
        list.insertAtRear(2);
        list.insertAtRear(3);
        list.insertAtRear(4);
        list.insertAtRear(5);
        list.deleteAll(2);
        System.out.println(list.sum());
        System.out.println(list.toString());
        list.print();
    }
}
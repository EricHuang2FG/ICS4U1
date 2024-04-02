public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insertFirst(1);
        list.insertSecond(2);
        list.insertThird(3);
        list.insertAtRear(4);
        System.out.println(list.sum());
        System.out.println(list.toString());
        list.print();
    }
}
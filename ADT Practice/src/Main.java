public class Main {

    public static void main(String[] args) {
        LinkedList list1 = new LinkedList();
        list1.addAtRear(5);
        list1.addAtRear(6);
        list1.addAtRear(8);
        list1.insert(7);
        list1.insert(4);
        list1.delete(5);
        list1.print();

        Queue queue1 = new Queue();
        queue1.enqueue(5);
        queue1.enqueue(200);
        queue1.dequeue();
        System.out.println(queue1.peek());
        queue1.print();

        Stack stack1 = new Stack();
        stack1.push(5);
        stack1.push(4);
        System.out.println(stack1.isEmpty());
        stack1.print();

        DLL dll1 = new DLL();
        dll1.insertAtFront(6);
        dll1.insertAtFront(5);
        dll1.insertAtFront(4);
        dll1.deleteLast();
        dll1.writeToFile("../res/dllOut.txt");
    }
}
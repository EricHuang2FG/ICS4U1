public class Main {

    public static void main(String[] args) {

        LinkedList list1 = new LinkedList();
        LinkedList list2 = new LinkedList();
        list1.insertAtRear(1);
        list1.insertAtRear(2);
        list1.insertAtRear(3);
        list1.insertAtRear(4);
        list1.insertAtRear(5);
        list1.insertAtRear(5);
        list1.insertAtRear(8);
        list2.insertAtRear(1);
        list2.insertAtRear(2);
        list2.insertAtRear(3);
        list2.insertAtRear(4);
        list2.insertAtRear(5);
        list2.insertAtRear(6);
        System.out.println(list1.sum());
        System.out.println(list1.toString());
        System.out.println(list1.isOrderedIncreasing());
        System.out.println(list1.isIdentical(list2));
        list1.insert(6);
        list1.insert(0);
        list1.insert(9);
        list1.insert(7);
        list1.print();

        Queue queue1 = new Queue();
        Queue queue2 = new Queue();
        queue1.enqueue(10);
        queue1.enqueue(11);
        queue1.enqueue(12);
        queue1.dequeue();
        queue1.print();
        System.out.println(queue2.peek());
        System.out.println(queue2.isEmpty());

        Stack stack1 = new Stack();
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        System.out.println(stack1.pop());
        stack1.print();

        System.out.println(evaluate("4 2 + 3 5 1 - * +"));
    }

    public static int evaluate(String expression) {
        String[] parts = expression.split(" ");
        Stack stack = new Stack();
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].equals("+") || parts[i].equals("-") || parts[i].equals("*") || parts[i].equals("/")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                int result;
                if (parts[i].equals("+")) {
                    result = num2 + num1;
                } else if (parts[i].equals("-")) {
                    result = num2 - num1;
                } else if (parts[i].equals("*")) {
                    result = num2 * num1;
                } else {
                    result = num2 / num1;
                }
                stack.push(result);
            } else {
                stack.push(Integer.valueOf(parts[i]));
            }
        }
        return stack.peek();
    }
}
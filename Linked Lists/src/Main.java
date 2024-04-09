import java.io.IOException;
import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        LinkedList list1 = new LinkedList();
        LinkedList list2 = new LinkedList();
        LinkedList list3 = new LinkedList();
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
        list3.insert(4);
        list3.insert(6);
        list3.print();

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

        DLL dll1 = new DLL();
        DLL dll2 = new DLL();
        dll1.insertAtFront(5);
        dll1.insertAtFront(6);
        dll1.insertAtFront(7);
        dll1.insertAtEnd(4);
        dll1.print();
        dll2.insertAtFront(100);
        dll2.print();

        file01();
        file02();
        file03();
    }

    public static void file01() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a message: ");
        String message = sc.nextLine();

        try {
            FileWriter fw = new FileWriter("res\\file01.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(message);
            pw.close();
        } catch (IOException e) {
            System.out.println("File writing error!");
        }

        try {
            FileReader fr = new FileReader("res\\file01.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("File reading error!");
        }
    }

    public static void file02() {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        try {
            FileWriter fw = new FileWriter("res\\file02.txt");
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < 100; i++) {
                int num = rand.nextInt(1, 11);
                pw.println(num);
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("File writing error!");
        }

        System.out.print("How many values would you like to read? Must be less than 101 and greater than -1: ");
        int numToRead = sc.nextInt();

        try {
            FileReader fw = new FileReader("res\\file02.txt");
            BufferedReader br = new BufferedReader(fw);
            int count = 1;
            String line;
            while ((line = br.readLine()) != null && count <= numToRead) {
                System.out.println(line);
                count++;
            }
        } catch (IOException e) {
            System.out.println("File reading error!");
        }
    }

    public static void file03() {
        String info = "";

        try {
            FileReader fr = new FileReader("res\\file03.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            int count = 1;
            while ((line = br.readLine()) != null) {
                info += line + ", ";
                if (count % 3 == 0) {
                    System.out.println(info.substring(0, info.length() - 2));
                }
                count++;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("File reading error!!!!!!");
        }
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

    // Mangler:

    // public int evaluatePostFix(String str) {
    //     Stack s = new Stack();
    //     String[] expressionParts = str.split(" ");
    //     for (int i = 0; i < expressionParts.length; i++) {
    //         if (expressionParts[i].equals("+")) {
    //             int a = s.pop();
    //             int b = s.pop();
    //             s.push(b + a);
    //         } else if (expressionParts[i].equals("-")) {
    //             int a = s.pop();
    //             int b = s.pop();
    //             s.push(b - a);
    //         } else if (expressionParts[i].equals("*")) {
    //             int a = s.pop();
    //             int b = s.pop();
    //             s.push(b * a);
    //         } else if (expressionParts[i].equals("/")) {
    //             int a = s.pop();
    //             int b = s.pop();
    //             s.push(b / a);
    //         } else {
    //             s.push(Integer.parseInt(expressionParts[i]));
    //         }
    //     }
    //     return s.pop();
    // }
}
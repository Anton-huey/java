public class Lab2 {
    public static void main(String[] args) {

        System.out.println("===Stack===");
        Stack stack = new Stack();
        stack.push(new Node(10));
        stack.push(new Node(20));
        stack.push(new Node(30));
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop().data);
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop().data);
        System.out.println("Pop: " + stack.pop().data);
        System.out.println("Is empty: " + stack.isEmpty());

        System.out.println("\n===Queue===");
        Queue queue = new Queue();
        queue.enqueue(new Node(10));
        queue.enqueue(new Node(20));
        queue.enqueue(new Node(30));
        System.out.println("Peek: " + queue.peek());
        System.out.println("Dequeue: " + queue.dequeue().data);
        System.out.println("Peek: " + queue.peek());
        System.out.println("Dequeue: " + queue.dequeue().data);
        System.out.println("Dequeue: " + queue.dequeue().data);
        System.out.println("Is empty: " + queue.isEmpty());
    }
}

// Node class and constructor
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// LinkedList class with methods
class LinkedList {
    private Node head;
    private Node tail;

    LinkedList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }

    LinkedList() {
        this.head = null;
        this.tail = null;
    }

    boolean contains(int key) {
        Node searcher = head;
        while (searcher != null) {
            if (searcher.data == key) {
                return true;
            }
            searcher = searcher.next;            
        }
        return false;
    }

    boolean delete(int key) {
        if (head == null) {
            return false;
        }    
        else if (head == tail && head.data == key) {
            head = null;
            tail = null;
            return true;
        }
        else if (head == tail) {
            return false;
        }

        Node searcher = head;
        Node previous = null;
        while (searcher != null) {
            if (searcher.data == key && searcher == head) {
                head = head.next;
                return true;
            }
            else if (previous == null) {
                previous = searcher;
                searcher = searcher.next;
            }
            else if (searcher.data == key && searcher == tail) {
                tail = previous;
                tail.next = null;
                return true;
            }
            else if (searcher.data == key) {
                previous.next = searcher.next;
                return true;
            }
            else {
                previous = searcher;
                searcher = searcher.next;
            }
        }
        return false;
    }

    void print() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    void clear() {
        head = null;
        tail = null;
    }

    int size() {
        Node current = head;
        int counter = 0;
        while (current != null) {
            current = current.next;
            counter++;
        }
        return counter;
    }

    void addFirst(Node node) {
        if (head == null && tail == null) {
            head = node;
            tail = node;
        }
        else {
            node.next = head;
            head = node;
        }
    }

    void addLast(Node node) {
        if (head == null && tail == null) {
            head = node;
            tail = node;
        }
        else {
            tail.next = node;
            tail = node;
        }
    }

    Node removeFirst() {
        if (head == null && tail == null) {
            return null;
        }
        else if (head == tail) {
            Node first = head;
            head = null;
            tail = null;
            return first;
        }
        else {
            Node first = head;
            head = head.next;
            first.next = null;
            return first;
        }
    }

    Node removeLast() {
        if (head == null && tail == null) {
            return null;
        }
        else if (head == tail) {
            Node last = tail;
            head = null;
            tail = null;
            return last;
        }
        else {
            Node last = tail;
            Node current = head;

            while (current.next != tail) {
                current = current.next;
            }

            tail = current;
            tail.next = null;
            return last;
        }
    }
}

class Stack {
    private LinkedList list;

    Stack() {
        list = new LinkedList();
    }

    void push(Node node) {
        list.addFirst(node);
    }

    Node pop() {
        return list.removeFirst();
    }

    int peek() {
        Node first = list.removeFirst();
        list.addFirst(first);
        return first.data;
    }

    boolean isEmpty() {
        return list.size() == 0;
    }
}

class Queue {
    private LinkedList list;

    Queue () {
        list = new LinkedList();
    }

    void enqueue(Node node) {
        list.addLast(node);
    }

    Node dequeue() {
        return list.removeFirst();
    }

    int peek() {
        Node first = list.removeFirst();
        list.addFirst(first);
        return first.data;
    }

    boolean isEmpty() {
        return list.size() == 0;
    }
}
package _02_linked_list.swea12303;

class Node {
    
    int data;
    Node next;
    
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class UserSolution {
    
    private final static int MAX_NODE = 10000;
    
    private final Node[] node = new Node[MAX_NODE];
    private int nodeCnt = 0;
    private Node head;
    
    private Node getNode(int data) {
        node[nodeCnt] = new Node(data);
        return node[nodeCnt++];
    }
    
    void init() {
        nodeCnt = 0;
        head = getNode(-1);
    }
    
    void addNode2Head(int data) {
        Node current = getNode(data);
        current.next = head.next;
        head.next = current;
    }
    
    void addNode2Tail(int data) {
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        
        current.next = getNode(data);
    }
    
    void addNode2Num(int data, int num) {
        Node current = head;
        for (int i = 1; i < num; i++) {
            current = current.next;
        }
        
        Node newNode = getNode(data);
        newNode.next = current.next;
        current.next = newNode;
    }
    
    void removeNode(int data) {
        Node current = head;
        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next;
                break;
            }
            current = current.next;
        }
    }
    
    int getList(int[] output) {
        int count = 0;
        
        Node current = head;
        while (current.next != null) {
            output[count++] = current.next.data;
            current = current.next;
        }
        
        return count;
    }
}
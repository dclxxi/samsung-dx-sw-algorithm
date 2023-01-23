package _02_linked_list.swea12304;

class Node {
    
    int data;
    Node prev;
    Node next;
    
    Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class UserSolution {
    
    private final static int MAX_NODE = 10000;
    
    private final Node[] node = new Node[MAX_NODE];
    private int nodeCnt = 0;
    private Node head, tail;
    
    private Node getNode(int data) {
        node[nodeCnt] = new Node(data);
        return node[nodeCnt++];
    }
    
    void init() {
        nodeCnt = 0;
        head = getNode(-1);
        tail = getNode(-1);
        
        head.next = tail;
        tail.prev = head;
    }
    
    void addNode2Head(int data) {
        Node newNode = getNode(data);
        head.next.prev = newNode;
        newNode.next = head.next;
        head.next = newNode;
        newNode.prev = head;
    }
    
    void addNode2Tail(int data) {
        Node newNode = getNode(data);
        tail.prev.next = newNode;
        newNode.prev = tail.prev;
        tail.prev = newNode;
        newNode.next = tail;
    }
    
    void addNode2Num(int data, int num) {
        Node current = head;
        for (int i = 1; i < num; i++) {
            current = current.next;
        }
        
        Node newNode = getNode(data);
        current.next.prev = newNode;
        newNode.next = current.next;
        current.next = newNode;
        newNode.prev = current;
    }
    
    int findNode(int data) {
        int index = 1;
        
        Node current = head.next;
        while (current.next != null) {
            if (current.data == data) {
                return index;
            }
            
            current = current.next;
            index++;
        }
        
        return 0;
    }
    
    void removeNode(int data) {
        Node current = head.next;
        while (current.next != null) {
            if (current.data == data) {
                current.next.prev = current.prev;
                current.prev.next = current.next;
                break;
            }
            
            current = current.next;
        }
    }
    
    int getList(int[] output) {
        int count = 0;
        
        Node current = head;
        while (current.next != tail) {
            output[count++] = current.next.data;
            current = current.next;
        }
        
        return count;
    }
    
    int getReversedList(int[] output) {
        int count = 0;
        
        Node current = tail;
        while (current.prev != head) {
            output[count++] = current.prev.data;
            current = current.prev;
        }
        
        return count;
    }
}

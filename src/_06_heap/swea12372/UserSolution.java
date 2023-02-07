package _06_heap.swea12372;

public class UserSolution {
    
    private final User[] maxHeap = new User[100001];
    private int size;
    
    void init() {
        size = 0;
    }
    
    void addUser(int uID, int income) {
        maxHeap[++size] = new User(uID, income);
        
        int now = size;
        while (now > 0) {
            int parent = now >> 1;
            
            if (parent == 0) {
                break;
            }
            
            if (maxHeap[now].income < maxHeap[parent].income) {
                break;
            }
            
            if (maxHeap[now].income == maxHeap[parent].income && maxHeap[now].uID > maxHeap[parent].uID) {
                break;
            }
            
            swap(now, parent);
            now = parent;
        }
    }
    
    private void swap(int a, int b) {
        User user = maxHeap[b];
        maxHeap[b] = maxHeap[a];
        maxHeap[a] = user;
    }
    
    int getTop10(int[] result) {
        int count = size < 10 ? size : 10;
        User[] popUsers = new User[count];
        
        for (int i = 0; i < count; i++) {
            popUsers[i] = pop();
        }
        
        for (int i = 0; i < count; i++) {
            result[i] = popUsers[i].uID;
            addUser(popUsers[i].uID, popUsers[i].income);
        }
        
        return count;
    }
    
    private User pop() {
        User user = maxHeap[1];
        maxHeap[1] = maxHeap[size--];
        
        int now = 1;
        while (true) {
            int left = now << 1;
            int right = now << 1 | 1;
            
            if (left > size) {
                break;
            }
            
            if (left == size || isMatch(left, right)) {
                if (isMatch(now, left)) {
                    break;
                }
                
                swap(now, left);
                now = left;
            } else {
                if (isMatch(now, right)) {
                    break;
                }
                
                swap(now, right);
                now = right;
            }
        }
        
        return user;
    }
    
    private boolean isMatch(int a, int b) {
        if (maxHeap[a].income > maxHeap[b].income) {
            return true;
        }
        
        return maxHeap[a].income == maxHeap[b].income && maxHeap[a].uID < maxHeap[b].uID;
    }
}

class User {
    
    int uID;
    int income;
    
    User(int uID, int income) {
        this.uID = uID;
        this.income = income;
    }
}

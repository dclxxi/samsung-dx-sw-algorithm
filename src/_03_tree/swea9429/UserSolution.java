package _03_tree.swea9429;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class UserSolution {
    
    private Directory directory;
    
    void init(int n) {
        directory = new Directory();
    }
    
    void cmd_mkdir(char[] path, char[] name) {
        directory.addNode(directory.getNode(path), new Node(name));
    }
    
    void cmd_rm(char[] path) {
        Node directoryNode = directory.getNode(path);
        Node parent = directoryNode.parent;
        
        parent.subs.remove(directoryNode);
        
        int count = directoryNode.count;
        while (parent != null) {
            parent.count -= count;
            parent = parent.parent;
        }
    }
    
    void cmd_cp(char[] srcPath, char[] dstPath) {
        Node src = directory.getNode(srcPath);
        Node dst = directory.getNode(dstPath);
        
        Node newDir = new Node(src.name);
        directory.copyNode(src, newDir);
        directory.addNode(dst, newDir);
    }
    
    void cmd_mv(char[] srcPath, char[] dstPath) {
        Node src = directory.getNode(srcPath);
        Node dst = directory.getNode(dstPath);
        
        Node parent = src.parent;
        parent.subs.remove(src);
        
        int count = src.count;
        while (parent != null) {
            parent.count -= count;
            parent = parent.parent;
        }
        
        directory.addNode(dst, src);
    }
    
    int cmd_find(char[] path) {
        return directory.getNode(path).count - 1;
    }
    
    private String charArrayToString(char[] array) {
        return String.valueOf(array, 0, array.length - 1);
    }
    
    class Node {
        
        String name;
        int count;
        Node parent;
        List<Node> subs = new ArrayList<>();
        
        Node(String name) {
            this.name = name;
            this.count = 1;
        }
        
        Node(char[] name) {
            this.name = charArrayToString(name);
            this.count = 1;
        }
        
        Node(String name, int count, Node parent) {
            this.name = name;
            this.count = count;
            this.parent = parent;
        }
    }
    
    class Directory {
        
        private final Node root = new Node("/");
        
        Node getNode(char[] path) {
            String directoryPath = charArrayToString(path);
            
            if (directoryPath.equals("/")) {
                return root;
            }
            
            Node node = root;
            StringTokenizer st = new StringTokenizer(directoryPath, "/");
            while (st.hasMoreTokens()) {
                String directoryName = st.nextToken();
                
                for (Node sub : node.subs) {
                    if (sub.name.equals(directoryName)) {
                        return sub;
                    }
                }
            }
            
            return node;
        }
        
        void addNode(Node parent, Node sub) {
            parent.subs.add(sub);
            sub.parent = parent;
            
            int count = sub.count;
            while (parent != null) {
                parent.count += count;
                parent = parent.parent;
            }
        }
        
        void copyNode(Node src, Node newNode) {
            newNode.count = src.count;
            
            for (Node sub : src.subs) {
                Node copySub = new Node(sub.name, sub.count, newNode);
                
                newNode.subs.add(copySub);
                copyNode(sub, copySub);
            }
        }
    }
}

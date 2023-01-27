package _03_tree.swea1248;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    
    private static final Node[] nodes = new Node[10001];
    private static final boolean[] visit = new boolean[10001];
    
    private static int size;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            
            for (int vertex = 1; vertex <= V; vertex++) {
                nodes[vertex] = new Node();
                visit[vertex] = false;
            }
            
            st = new StringTokenizer(br.readLine(), " ");
            for (int edge = 0; edge < E; edge++) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                
                if (nodes[parent].leftChild == 0) {
                    nodes[parent].leftChild = child;
                } else {
                    nodes[parent].rightChild = child;
                }
                nodes[child].parent = parent;
            }
            
            int commonAncestor;
            while (true) {
                if (left != 1) {
                    left = nodes[left].parent;
                    
                    if (visit[left]) {
                        commonAncestor = left;
                        break;
                    }
                    
                    visit[left] = true;
                }
                
                if (right != 1) {
                    right = nodes[right].parent;
                    
                    if (visit[right]) {
                        commonAncestor = right;
                        break;
                    }
                    
                    visit[right] = true;
                }
            }
            
            size = 0;
            setTreeSize(nodes[commonAncestor]);
            
            sb.append("#").append(testCase).append(" ").append(commonAncestor).append(" ").append(size).append("\n");
        }
        System.out.println(sb);
    }
    
    private static void setTreeSize(Node node) {
        size++;
        
        if (nodes[node.leftChild] != null) {
            setTreeSize(nodes[node.leftChild]);
        }
        
        if (nodes[node.rightChild] != null) {
            setTreeSize(nodes[node.rightChild]);
        }
    }
    
    static class Node {
        
        int parent, leftChild, rightChild;
    }
}

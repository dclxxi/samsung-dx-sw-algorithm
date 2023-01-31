package _04_graph.swea1855;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    
    private static final Node[] nodes = new Node[100001];
    private static final int[][] parents = new int[100001][18];
    private static final Queue<Integer> queue = new LinkedList<>();
    
    private static int height;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            height = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
            
            nodes[1] = new Node();
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int child = 2; child <= N; child++) {
                int parent = Integer.parseInt(st.nextToken());
                
                nodes[parent].child.add(child);
                nodes[child] = new Node(parent);
                parents[child][0] = parent;
            }
            
            for (int ancestor = 1; ancestor < height; ancestor++) {
                for (int node = 2; node <= N; node++) {
                    parents[node][ancestor] = parents[parents[node][ancestor - 1]][ancestor - 1];
                }
            }
            
            sb.append("#").append(testCase).append(" ").append(getCount()).append("\n");
        }
        System.out.print(sb);
    }
    
    private static long getCount() {
        for (int child : nodes[1].child) {
            queue.offer(child);
        }
        
        long count = 0;
        int prev = 1;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            Node node = nodes[now];
            
            count += nodes[prev].depth + node.depth - nodes[getLCA(now, prev)].depth * 2L;
            
            for (int child : node.child) {
                queue.offer(child);
            }
            
            prev = now;
        }
        return count;
    }
    
    private static int getLCA(int nodeA, int nodeB) {
        int depthA = nodes[nodeA].depth;
        int depthB = nodes[nodeB].depth;
        
        int depthGap;
        if (depthA < depthB) {
            int temp = nodeA;
            nodeA = nodeB;
            nodeB = temp;
            
            depthGap = depthB - depthA;
        } else {
            depthGap = depthA - depthB;
        }
        
        for (int ancestor = height - 1; ancestor >= 0; ancestor--) {
            if ((1 << ancestor) <= depthGap) {
                nodeA = parents[nodeA][ancestor];
            }
        }
        
        if (nodeA == nodeB) {
            return nodeA;
        }
        
        for (int ancestor = height - 1; ancestor >= 0; ancestor--) {
            if (parents[nodeA][ancestor] != parents[nodeB][ancestor]) {
                nodeA = parents[nodeA][ancestor];
                nodeB = parents[nodeB][ancestor];
            }
        }
        
        return parents[nodeA][0];
    }
    
    static class Node {
        
        int parent, depth;
        List<Integer> child = new ArrayList<>();
        
        private Node() {
            this.parent = 0;
            this.depth = 0;
        }
        
        private Node(int parent) {
            this.parent = parent;
            this.depth = nodes[parent].depth + 1;
        }
    }
}

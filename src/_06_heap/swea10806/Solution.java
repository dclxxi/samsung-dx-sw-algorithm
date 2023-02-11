package _06_heap.swea10806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    
    private static final int[] A = new int[10];
    private static int N, K;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());
            
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
                min = Math.min(A[i], min);
            }
            
            K = Integer.parseInt(br.readLine());
            
            sb.append("#").append(testCase).append(" ").append(getCount()).append("\n");
        }
        System.out.println(sb);
    }
    
    private static int getCount() {
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(0, K));
        
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            
            if (node.remainder == 0) {
                return node.count;
            }
            
            for (int i = 0; i < N; i++) {
                queue.add(new Node(node.count + node.remainder % A[i], node.remainder / A[i]));
            }
            
            queue.add(new Node(node.count + node.remainder, 0));
        }
        
        return K;
    }
    
    static class Node implements Comparable<Node> {
        
        int count, remainder;
        
        private Node(int count, int remainder) {
            this.count = count;
            this.remainder = remainder;
        }
        
        public int compareTo(Node o) {
            return Integer.compare(this.count, o.count);
        }
    }
}

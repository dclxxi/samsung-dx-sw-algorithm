package _06_heap.swea3000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            Queue<Integer> minHeap = new PriorityQueue<>();
            Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            maxHeap.offer(Integer.parseInt(st.nextToken()));
            
            int sum = 0;
            for (int num = 0; num < N; num++) {
                st = new StringTokenizer(br.readLine(), " ");
                minHeap.offer(Integer.parseInt(st.nextToken()));
                maxHeap.offer(Integer.parseInt(st.nextToken()));
                
                if (minHeap.peek() < maxHeap.peek()) {
                    int minRoot = minHeap.poll();
                    int maxRoot = maxHeap.poll();
                    
                    minHeap.offer(maxRoot);
                    maxHeap.offer(minRoot);
                }
                
                sum += maxHeap.peek();
                sum %= 20171109;
            }
            sb.append("#").append(testCase).append(" ").append(sum).append("\n");
        }
        System.out.print(sb);
    }
}

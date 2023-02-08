package _06_heap.swea2930;

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
            sb.append("#").append(testCase).append(" ");
            
            int N = Integer.parseInt(br.readLine());
            Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            for (int operation = 0; operation < N; operation++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                
                switch (Integer.parseInt(st.nextToken())) {
                    case 1:
                        maxHeap.add(Integer.parseInt(st.nextToken()));
                        break;
                    case 2:
                        Integer maxKey = maxHeap.poll();
                        if (maxKey != null) {
                            sb.append(maxKey).append(" ");
                        } else {
                            sb.append(-1).append(" ");
                        }
                        break;
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}


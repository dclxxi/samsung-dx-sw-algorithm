package _10_binary_search.swea11446;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    
    private static final long[] A = new long[100];
    
    private static int N;
    private static long M;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Long.parseLong(st.nextToken());
            
            long maxBagCount = 0;
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                A[i] = Long.parseLong(st.nextToken());
                maxBagCount = Math.max(maxBagCount, A[i]);
            }
            
            sb.append("#").append(test_case).append(" ").append(getMaxBagCount(maxBagCount)).append("\n");
        }
        System.out.println(sb);
    }
    
    private static long getMaxBagCount(long maxBagCount) {
        long minBagCount = 0;
        
        while (minBagCount < maxBagCount) {
            long bagCount = maxBagCount - (maxBagCount - minBagCount) / 2;
            
            long candyCount = 0;
            for (int i = 0; i < N; i++) {
                candyCount += (A[i] / bagCount);
            }
            
            if (candyCount < M) {
                maxBagCount = bagCount - 1;
            } else {
                minBagCount = bagCount;
            }
        }
        
        return maxBagCount;
    }
}

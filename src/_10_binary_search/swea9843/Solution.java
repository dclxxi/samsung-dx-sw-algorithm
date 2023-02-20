package _10_binary_search.swea9843;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    
    private static long N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            N = Long.parseLong(br.readLine()) << 1;
            
            sb.append("#").append(testCase).append(" ").append(getK()).append("\n");
        }
        System.out.print(sb);
    }
    
    private static long getK() {
        long left = 0;
        long right = (long) Math.sqrt(N);
        
        while (left <= right) {
            long middle = right - (right - left) / 2;
            
            long candleCount = middle * (middle + 1);
            if (candleCount == N) {
                return middle;
            }
            
            if (candleCount < N) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        
        return -1;
    }
}

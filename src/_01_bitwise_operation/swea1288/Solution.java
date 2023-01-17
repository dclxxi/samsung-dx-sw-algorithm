package _01_bitwise_operation.swea1288;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            
            int visited = 0;
            for (int K = 1; ; K++) {
                int sheepCount = K * N;
                
                int number = sheepCount;
                while (number > 0) {
                    visited |= (1 << number % 10);
                    number /= 10;
                }
                
                if (visited == (1 << 10) - 1) {
                    sb.append("#").append(testCase).append(" ").append(sheepCount).append("\n");
                    break;
                }
            }
        }
        System.out.print(sb);
    }
}

package _09_divide_and_conquer.swea13736;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            long candySum = A + B;
            long candyCount = (power(candySum, K) * A) % candySum;
            
            sb.append("#").append(testCase).append(" ")
                    .append(Math.min(candyCount, candySum - candyCount)).append("\n");
        }
        System.out.print(sb);
    }
    
    private static long power(long x, long p) {
        if (p == 0) {
            return 1;
        }
        
        long result = power(x, p / 2);
        result = (result * result) % x;
        
        if ((p & 1) != 0) {
            result = (result * 2) % x;
        }
        
        return result;
    }
}

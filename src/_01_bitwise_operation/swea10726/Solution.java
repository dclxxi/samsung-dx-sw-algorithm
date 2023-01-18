package _01_bitwise_operation.swea10726;

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
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            sb.append("#").append(testCase).append(" ");
            
            int bit = (1 << N) - 1;
            if ((M & bit) == bit) {
                sb.append("ON");
            } else {
                sb.append("OFF");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}

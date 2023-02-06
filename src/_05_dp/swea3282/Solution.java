package _05_dp.swea3282;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    
    private static final int[] V = new int[101];
    private static final int[] C = new int[101];
    private static final int[][] dp = new int[101][1001];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                V[i] = Integer.parseInt(st.nextToken());
                C[i] = Integer.parseInt(st.nextToken());
            }
            
            for (int object = 0; object <= N; object++) {
                for (int volume = 0; volume <= K; volume++) {
                    if (object == 0 || volume == 0) {
                        dp[object][volume] = 0;
                    } else if (V[object] > volume) {
                        dp[object][volume] = dp[object - 1][volume];
                    } else {
                        dp[object][volume] =
                                Math.max(dp[object - 1][volume - V[object]] + C[object], dp[object - 1][volume]);
                    }
                }
            }
            
            sb.append("#").append(testCase).append(" ").append(dp[N][K]).append("\n");
        }
        System.out.print(sb);
    }
}


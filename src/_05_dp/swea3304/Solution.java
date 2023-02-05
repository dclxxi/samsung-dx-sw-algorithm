package _05_dp.swea3304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    
    private static final int[][] lcs = new int[1001][1001];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            char[] stringA = st.nextToken().toCharArray();
            char[] stringB = st.nextToken().toCharArray();
            
            int lengthA = stringA.length;
            int lengthB = stringB.length;
            
            for (int i = 1; i <= lengthA; i++) {
                for (int j = 1; j <= lengthB; j++) {
                    if (stringA[i - 1] != stringB[j - 1]) {
                        lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                    } else {
                        lcs[i][j] = lcs[i - 1][j - 1] + 1;
                    }
                }
            }
            
            sb.append("#").append(testCase).append(" ").append(lcs[lengthA][lengthB]).append("\n");
        }
        System.out.print(sb);
    }
}


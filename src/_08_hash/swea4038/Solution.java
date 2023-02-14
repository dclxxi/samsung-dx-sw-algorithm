package _08_hash.swea4038;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    
    private static final int[] table = new int[100000];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            String B = br.readLine();
            String S = br.readLine();
            
            int lengthB = B.length();
            int lengthS = S.length();
            
            for (int i = 0; i < lengthS; i++) {
                table[i] = 0;
            }
            
            int prefix = 0;
            for (int suffix = 1; suffix < lengthS; suffix++) {
                while (prefix > 0 && S.charAt(prefix) != S.charAt(suffix)) {
                    prefix = table[prefix - 1];
                }
                
                if (S.charAt(prefix) == S.charAt(suffix)) {
                    table[suffix] = ++prefix;
                }
            }
            
            int count = 0;
            prefix = 0;
            for (int suffix = 0; suffix < lengthB; suffix++) {
                while (prefix > 0 && B.charAt(suffix) != S.charAt(prefix)) {
                    prefix = table[prefix - 1];
                }
                
                if (B.charAt(suffix) == S.charAt(prefix)) {
                    if (prefix == lengthS - 1) {
                        prefix = table[prefix];
                        count++;
                    } else {
                        prefix++;
                    }
                }
            }
            
            sb.append("#").append(testCase).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }
}

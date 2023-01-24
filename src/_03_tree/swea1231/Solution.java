package _03_tree.swea1231;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    
    private static final String[] alphabets = new String[101];
    
    private static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        for (int testCase = 1; testCase <= 10; testCase++) {
            N = Integer.parseInt(br.readLine());
            
            for (int i = 1; i <= N; i++) {
                alphabets[i] = null;
            }
            
            for (int node = 1; node <= N; node++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                st.nextToken();
                
                alphabets[node] = st.nextToken();
            }
            
            sb.append("#").append(testCase).append(" ");
            inorder(1, sb);
            sb.append("\n");
        }
        System.out.print(sb);
    }
    
    private static void inorder(int node, StringBuilder sb) {
        int child = node << 1;
        
        if (child <= N) {
            inorder(child, sb);
        }
        
        sb.append(alphabets[node]);
        
        if (++child <= N) {
            inorder(child, sb);
        }
    }
}

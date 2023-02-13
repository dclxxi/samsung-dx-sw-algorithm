package _08_hash.swea2948;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
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
            
            Set<String> strings = new HashSet<>();
            st = new StringTokenizer(br.readLine(), " ");
            for (int string = 0; string < N; string++) {
                strings.add(st.nextToken());
            }
            
            int count = 0;
            st = new StringTokenizer(br.readLine(), " ");
            for (int string = 0; string < M; string++) {
                if (strings.contains(st.nextToken())) {
                    count++;
                }
            }
            
            sb.append("#").append(testCase).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }
}

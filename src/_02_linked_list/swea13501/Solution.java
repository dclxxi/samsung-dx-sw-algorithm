package _02_linked_list.swea13501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
            int L = Integer.parseInt(st.nextToken());
            
            List<Integer> sequence = new ArrayList<>();
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                sequence.add(Integer.parseInt(st.nextToken()));
            }
            
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                
                String command = st.nextToken();
                switch (command) {
                    case "I":
                        sequence.add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                        break;
                    case "D":
                        sequence.remove(Integer.parseInt(st.nextToken()));
                        break;
                    case "C":
                        sequence.set(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                        break;
                }
            }
            
            sb.append("#").append(testCase).append(" ");
            if (sequence.size() > L) {
                sb.append(sequence.get(L));
            } else {
                sb.append("-1");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}

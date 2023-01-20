package _02_linked_list.swea1230;

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
        
        for (int testCase = 1; testCase <= 10; testCase++) {
            int N = Integer.parseInt(br.readLine());
            
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            List<Integer> cryptograms = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                cryptograms.add(Integer.parseInt(st.nextToken()));
            }
            
            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            for (int commandCount = 0; commandCount < M; commandCount++) {
                String command = st.nextToken();
                
                int x, y;
                switch (command) {
                    case "I":
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        for (int i = 0; i < y; i++) {
                            cryptograms.add(x + i, Integer.parseInt(st.nextToken()));
                        }
                        break;
                    case "D":
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        for (int i = 0; i < y; i++) {
                            cryptograms.remove(x);
                        }
                        break;
                    case "A":
                        y = Integer.parseInt(st.nextToken());
                        for (int i = 0; i < y; i++) {
                            cryptograms.add(Integer.parseInt(st.nextToken()));
                        }
                        break;
                }
            }
            
            sb.append("#").append(testCase).append(" ");
            for (int i = 0; i < 10; i++) {
                sb.append(cryptograms.get(i)).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
package _03_tree.swea1233;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        for (int test_case = 1; test_case <= 10; test_case++) {
            sb.append("#").append(test_case).append(" ");
            
            int N = Integer.parseInt(br.readLine());
            int answer = 1;
            for (int node = 0; node < N; node++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                if (answer != 0) {
                    st.nextToken();
                    
                    if (Character.isDigit(st.nextToken().charAt(0))) {
                        if (st.hasMoreTokens()) {
                            answer = 0;
                        }
                    } else if (st.countTokens() != 2) {
                        answer = 0;
                    }
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}

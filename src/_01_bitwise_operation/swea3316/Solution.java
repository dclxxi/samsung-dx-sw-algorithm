package _01_bitwise_operation.swea3316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    
    private static final int[][] cases = new int[10000][16];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            String managers = br.readLine();
            
            int N = managers.length();
            for (int day = 0; day < N; day++) {
                for (int participants = 0; participants < 16; participants++) {
                    cases[day][participants] = 0;
                }
            }
            
            int manager = 1 << managers.charAt(0) - 'A';
            for (int participants = 1; participants < 16; participants++) {
                if ((participants & manager) != 0 && (participants & 1) != 0) {
                    cases[0][participants] = 1;
                }
            }
            
            for (int day = 1; day < N; day++) {
                manager = 1 << managers.charAt(day) - 'A';
                for (int yesterday = 1; yesterday < 16; yesterday++) {
                    if (cases[day - 1][yesterday] != 0) {
                        for (int today = 0; today < 16; today++) {
                            if ((yesterday & today) != 0 && (today & manager) != 0) {
                                cases[day][today] += cases[day - 1][yesterday];
                                cases[day][today] %= 1000000007;
                            }
                        }
                    }
                }
            }
            
            int caseCount = 0, lastDay = N - 1;
            for (int caseNumber = 1; caseNumber < 16; caseNumber++) {
                caseCount += cases[lastDay][caseNumber];
                caseCount %= 1000000007;
            }
            sb.append("#").append(testCase).append(" ").append(caseCount).append("\n");
        }
        System.out.print(sb);
    }
}

package _10_binary_search.swea10507;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    
    private static final boolean[] studyDays = new boolean[1000001];
    private static final int[] dates = new int[200000];
    
    private static int n, p, maxCount;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine(), " ");
            for (int day = 0; day < n; day++) {
                dates[day] = Integer.parseInt(st.nextToken());
                studyDays[dates[day]] = true;
            }
            
            maxCount = p + 1;
            search();
            sb.append("#").append(testCase).append(" ").append(maxCount).append("\n");
            
            for (int day = 0; day < n; day++) {
                studyDays[dates[day]] = false;
            }
        }
        System.out.print(sb);
    }
    
    private static void search() {
        int start = 1, end = 1, studyCount = 0;
        int lastDay = dates[n - 1];
        
        while (end <= lastDay) {
            if (studyDays[end]) {
                maxCount = Math.max(maxCount, ++studyCount);
                end++;
            } else {
                if (p != 0) {
                    p--;
                    studyCount++;
                    end++;
                } else {
                    maxCount = Math.max(maxCount, studyCount--);
                    if (!studyDays[start++]) {
                        p++;
                    }
                }
            }
        }
    }
}

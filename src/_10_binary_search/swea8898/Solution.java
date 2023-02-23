package _10_binary_search.swea8898;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    
    private static final int[] cows = new int[500000];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine(), " ");
            int dx = Math.abs(Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken()));
            
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                cows[i] = Integer.parseInt(st.nextToken());
            }
            
            Arrays.sort(cows, 0, N);
            
            int min = Integer.MAX_VALUE;
            int count = 0;
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < M; i++) {
                int horse = Integer.parseInt(st.nextToken());
                int cowIndex = getCowIndex(horse, N);
                
                if (cowIndex >= 0 && cowIndex < N) {
                    int cow = cows[cowIndex];
                    int dz = Math.abs(cow - horse);
                    
                    if (min > dz) {
                        min = dz;
                        count = 1;
                    } else if (min == dz) {
                        count++;
                    }
                    
                    if (cowIndex != 0 && min != 0) {
                        cow = cows[cowIndex - 1];
                        dz = Math.abs(cow - horse);
                        
                        if (min > dz) {
                            min = dz;
                            count = 1;
                        } else if (min == dz) {
                            count++;
                        }
                    }
                }
            }
            
            sb.append("#").append(testCase).append(" ").append(dx + min).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }
    
    private static int getCowIndex(int horse, int cowCount) {
        int left = 0;
        int right = cowCount - 1;
        
        if (cows[left] > horse) {
            return left;
        }
        if (cows[right] < horse) {
            return right;
        }
        
        int middle = 0;
        while (left <= right) {
            middle = right - (right - left) / 2;
            
            if (cows[middle] == horse) {
                return middle;
            }
            
            if (cows[middle] < horse) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        
        if (cows[middle] < horse) {
            return middle + 1;
        }
        
        return middle;
    }
}

package _10_binary_search.swea9999;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    
    private static final PeakTime[] peakTimes = new PeakTime[100000];
    
    private static int L, N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            L = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());
            
            int sum = 0;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                sum += e - s;
                peakTimes[i] = new PeakTime(s, e, sum);
            }
            
            sb.append("#").append(testCase).append(" ").append(getMax()).append("\n");
        }
        System.out.println(sb);
    }
    
    private static int getMax() {
        int max = -1;
        
        for (int i = 0; i < N; i++) {
            int endTime = peakTimes[i].s + L;
            int upperBound = getUpperBound(endTime);
            
            int sum = peakTimes[upperBound - 1].sum;
            
            if (i > 0) {
                sum -= peakTimes[i - 1].sum;
            }
            
            if (upperBound < N && endTime > peakTimes[upperBound].s) {
                sum += endTime - peakTimes[upperBound].s;
            }
            
            max = Math.max(max, sum);
        }
        
        return max;
    }
    
    private static int getUpperBound(int endTime) {
        int left = 0;
        int right = N - 1;
        
        int upperBound = N;
        while (left <= right) {
            int middle = right - (right - left) / 2;
            
            if (peakTimes[middle].e > endTime) {
                right = middle - 1;
                upperBound = middle;
            } else {
                left = middle + 1;
            }
        }
        
        return upperBound;
    }
    
    static class PeakTime {
        
        int s, e, sum;
        
        PeakTime(int s, int e, int sum) {
            this.s = s;
            this.e = e;
            this.sum = sum;
        }
    }
}

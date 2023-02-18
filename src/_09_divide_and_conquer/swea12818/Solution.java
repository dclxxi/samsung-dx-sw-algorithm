package _09_divide_and_conquer.swea12818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    
    private static final int[] A = new int[100000];
    private static final int[] buffer = new int[100000];
    
    private static long inversionCount;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
            
            inversionCount = 0;
            mergeSort(0, N - 1);
            sb.append("#").append(testCase).append(" ").append(inversionCount).append("\n");
        }
        System.out.println(sb);
    }
    
    private static void mergeSort(int begin, int end) {
        if (begin >= end) {
            return;
        }
        
        int middle = begin + (end - begin) / 2;
        
        mergeSort(begin, middle);
        mergeSort(middle + 1, end);
        merge(begin, middle, end);
    }
    
    private static void merge(int begin, int middle, int end) {
        int begin1 = begin;
        int begin2 = middle + 1;
        
        int index = begin;
        while (begin1 <= middle && begin2 <= end) {
            if (A[begin1] <= A[begin2]) {
                buffer[index++] = A[begin1++];
            } else {
                inversionCount += (middle - begin1 + 1);
                buffer[index++] = A[begin2++];
            }
        }
        
        while (begin1 <= middle) {
            buffer[index++] = A[begin1++];
        }
        
        while (begin2 <= end) {
            buffer[index++] = A[begin2++];
        }
        
        System.arraycopy(buffer, begin, A, begin, end - begin + 1);
    }
}

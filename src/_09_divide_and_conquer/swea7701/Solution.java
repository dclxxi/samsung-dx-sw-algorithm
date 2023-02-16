package _09_divide_and_conquer.swea7701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    
    private static final String[] names = new String[20000];
    private static final String[] buffer = new String[20000];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            for (int name = 0; name < N; name++) {
                names[name] = br.readLine();
            }
            
            mergeSort(0, N - 1);
            
            sb.append("#").append(testCase).append("\n").append(names[0]).append("\n");
            for (int name = 1; name < N; name++) {
                if (names[name - 1].equals(names[name])) {
                    continue;
                }
                
                sb.append(names[name]).append("\n");
            }
        }
        System.out.print(sb);
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
            if (names[begin1].length() < names[begin2].length()) {
                buffer[index++] = names[begin1++];
            } else if (names[begin1].length() > names[begin2].length()) {
                buffer[index++] = names[begin2++];
            } else {
                if (names[begin1].compareTo(names[begin2]) <= 0) {
                    buffer[index++] = names[begin1++];
                } else {
                    buffer[index++] = names[begin2++];
                }
            }
        }
        
        while (begin1 <= middle) {
            buffer[index++] = names[begin1++];
        }
        
        while (begin2 <= end) {
            buffer[index++] = names[begin2++];
        }
        
        System.arraycopy(buffer, begin, names, begin, end - begin + 1);
    }
}

package _08_hash.swea7091;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    
    private static final long[] dreamRowHash = new long[2000];
    private static final long[][] teacherRowHash = new long[2000][2000];
    
    private static int H, W;
    private static long hash, power;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            int widthGap = M - W;
            int heightGap = N - H;
            
            for (int h = 0; h < H; h++) {
                dreamRowHash[h] = getRowHash(br.readLine());
            }
            long dreamHash = getColumnHash(dreamRowHash);
            
            for (int n = 0; n < N; n++) {
                String line = br.readLine();
                teacherRowHash[0][n] = getRowHash(line);
                
                for (int width = 0; width < widthGap; width++) {
                    teacherRowHash[width + 1][n] = getRightHash(line, width);
                }
            }
            
            int count = 0;
            for (int width = 0; width <= widthGap; width++) {
                if (dreamHash == getColumnHash(teacherRowHash[width])) {
                    count++;
                }
                
                for (int height = 0; height < heightGap; height++) {
                    if (dreamHash == getRightHash(width, height)) {
                        count++;
                    }
                }
            }
            
            sb.append("#").append(testCase).append(" ").append(count).append("\n");
        }
        System.out.print(sb);
    }
    
    private static long getRowHash(String line) {
        hash = 0;
        power = 1;
        for (int w = 0; w < W; w++) {
            hash += line.charAt(W - w - 1) * power;
            if (w < W - 1) {
                power *= 33;
            }
        }
        
        return hash;
    }
    
    private static long getColumnHash(long[] rowHash) {
        hash = 0;
        power = 1;
        for (int h = 0; h < H; h++) {
            hash += rowHash[H - h - 1] * power;
            if (h < H - 1) {
                power *= 5381;
            }
        }
        
        return hash;
    }
    
    private static long getRightHash(String line, int width) {
        hash -= line.charAt(width) * power;
        return hash = hash * 33 + line.charAt(width + W);
    }
    
    private static long getRightHash(int width, int height) {
        hash -= teacherRowHash[width][height] * power;
        return hash = hash * 5381 + teacherRowHash[width][height + H];
    }
}

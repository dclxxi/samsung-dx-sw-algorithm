package _04_graph.swea1461;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    
    private static final int[][] processor = new int[12][12];
    private static final Point[] cores = new Point[12];
    
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};
    
    private static int N, totalCoreCount, maxCoreCount, minWireLength;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());
            
            totalCoreCount = 0;
            for (int x = 0; x < N; x++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int y = 0; y < N; y++) {
                    int cell = Integer.parseInt(st.nextToken());
                    processor[x][y] = cell;
                    
                    if (cell == 1 && x != 0 && x != N - 1 && y != 0 && y != N - 1) {
                        cores[totalCoreCount++] = new Point(x, y);
                    }
                }
            }
            
            maxCoreCount = Integer.MIN_VALUE;
            minWireLength = Integer.MAX_VALUE;
            
            dfs(0, 0, 0);
            
            sb.append("#").append(testCase).append(" ").append(minWireLength).append("\n");
        }
        System.out.println(sb);
    }
    
    private static void dfs(int coreIndex, int coreCount, int wireLength) {
        if (coreIndex == totalCoreCount) {
            if (maxCoreCount < coreCount) {
                maxCoreCount = coreCount;
                minWireLength = wireLength;
            } else if (maxCoreCount == coreCount) {
                if (minWireLength > wireLength) {
                    minWireLength = wireLength;
                }
            }
            return;
        }
        
        int x = cores[coreIndex].x;
        int y = cores[coreIndex].y;
        
        for (int direction = 0; direction < 4; direction++) {
            int length = 0;
            int nx = x;
            int ny = y;
            
            while (true) {
                nx += dx[direction];
                ny += dy[direction];
                
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    break;
                }
                
                if (processor[nx][ny] == 1) {
                    length = 0;
                    break;
                }
                
                length++;
            }
            
            if (length == 0) {
                dfs(coreIndex + 1, coreCount, wireLength);
            } else {
                nx = x;
                ny = y;
                for (int wire = 0; wire < length; wire++) {
                    nx += dx[direction];
                    ny += dy[direction];
                    processor[nx][ny] = 1;
                }
                
                dfs(coreIndex + 1, coreCount + 1, wireLength + length);
                
                nx = x;
                ny = y;
                for (int wire = 0; wire < length; wire++) {
                    nx += dx[direction];
                    ny += dy[direction];
                    processor[nx][ny] = 0;
                }
            }
        }
    }
}

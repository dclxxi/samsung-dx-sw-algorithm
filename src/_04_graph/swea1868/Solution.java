package _04_graph.swea1868;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    
    private static final char[][] graph = new char[300][300];
    private static final int[][] landMine = new int[300][300];
    private static final Queue<Point> queue = new LinkedList<>();
    
    private static final int[] dy = {1, 1, 1, 0, -1, -1, -1, 0};
    private static final int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
    
    private static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());
            
            for (int n = 0; n < N; n++) {
                graph[n] = br.readLine().toCharArray();
            }
            
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (graph[x][y] == '.') {
                        int landMineCount = 0;
                        for (int direction = 0; direction < 8; direction++) {
                            int nx = x + dx[direction];
                            int ny = y + dy[direction];
                            
                            if (nx < 0 || nx >= N || ny < 0 || ny >= N || graph[nx][ny] == '.') {
                                continue;
                            }
                            
                            landMineCount++;
                        }
                        landMine[x][y] = landMineCount;
                    }
                }
            }
            
            sb.append("#").append(testCase).append(" ").append(getClickCount()).append("\n");
        }
        System.out.print(sb);
    }
    
    private static int getClickCount() {
        int clickCount = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (landMine[x][y] == 0 && graph[x][y] != '*') {
                    click(x, y);
                    clickCount++;
                }
            }
        }
        
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (landMine[x][y] > 0 && graph[x][y] != '*') {
                    clickCount++;
                }
            }
        }
        
        return clickCount;
    }
    
    private static void click(int x, int y) {
        landMine[x][y] = -1;
        
        queue.offer(new Point(x, y));
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            for (int direction = 0; direction < 8; direction++) {
                int nx = point.x + dx[direction];
                int ny = point.y + dy[direction];
                
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || landMine[nx][ny] == -1) {
                    continue;
                }
                
                if (landMine[nx][ny] == 0) {
                    queue.add(new Point(nx, ny));
                }
                
                landMine[nx][ny] = -1;
            }
        }
    }
}

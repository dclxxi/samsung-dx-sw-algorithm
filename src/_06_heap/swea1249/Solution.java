package _06_heap.swea1249;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    
    private static final int[][] map = new int[100][100];
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};
    
    private static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());
            
            for (int x = 0; x < N; x++) {
                String time = br.readLine();
                for (int y = 0; y < N; y++) {
                    map[x][y] = time.charAt(y) - '0';
                }
            }
            
            sb.append("#").append(testCase).append(" ").append(getTime()).append("\n");
        }
        System.out.print(sb);
    }
    
    private static int getTime() {
        Queue<Road> queue = new PriorityQueue<>();
        
        queue.offer(new Road(0, 0, 0));
        map[0][0] = -1;
        
        while (!queue.isEmpty()) {
            Road road = queue.poll();
            int x = road.x;
            int y = road.y;
            int time = road.time;
            
            if (x == N - 1 && y == N - 1) {
                return time;
            }
            
            for (int direction = 0; direction < 4; direction++) {
                int nx = x + dx[direction];
                int ny = y + dy[direction];
                
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == -1) {
                    continue;
                }
                
                queue.offer(new Road(nx, ny, time + map[nx][ny]));
                map[nx][ny] = -1;
            }
        }
        
        return 0;
    }
    
    static class Road implements Comparable<Road> {
        
        int x, y, time;
        
        private Road(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
        
        @Override
        public int compareTo(Road o) {
            return Integer.compare(this.time, o.time);
        }
    }
}

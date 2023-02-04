package _04_graph.swea12318;

class Point {
    
    int x, y;
    
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class UserSolution {
    
    private final int[][] distance = new int[10][10];
    private final Point[] queue = new Point[100];
    
    private final int[] dx = {0, 0, -1, 1};
    private final int[] dy = {-1, 1, 0, 0};
    
    private int[][] map;
    private int mapSize, front, rear, queueSize;
    
    void bfs_init(int map_size, int[][] map) {
        this.mapSize = map_size;
        this.map = map;
    }
    
    int bfs(int x1, int y1, int x2, int y2) {
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                distance[x][y] = 0;
            }
        }
        
        front = rear = -1;
        queueSize = 0;
        
        offer(new Point(x1 - 1, y1 - 1));
        while (queueSize > 0) {
            Point point = poll();
            int x = point.x;
            int y = point.y;
            
            for (int direction = 0; direction < 4; direction++) {
                int nx = x + dx[direction];
                int ny = y + dy[direction];
                
                if (nx < 0 || nx >= mapSize || ny < 0 || ny >= mapSize) {
                    continue;
                }
                
                if (map[ny][nx] == 0 && distance[ny][nx] == 0) {
                    distance[ny][nx] = distance[y][x] + 1;
                    
                    if (nx == (x2 - 1) && ny == (y2 - 1)) {
                        return distance[ny][nx];
                    }
                    
                    offer(new Point(nx, ny));
                }
            }
        }
        
        return -1;
    }
    
    private void offer(Point point) {
        rear = (rear + 1) % 100;
        queueSize++;
        queue[rear] = point;
    }
    
    private Point poll() {
        front = (front + 1) % 100;
        queueSize--;
        return queue[front];
    }
}


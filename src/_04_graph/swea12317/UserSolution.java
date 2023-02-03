package _04_graph.swea12317;

class UserSolution {
    
    private final int[][] children = new int[100][5];
    private final int[] childCount = new int[100];
    
    private int nextKing, king;
    
    void dfs_init(int N, int[][] path) {
        for (int i = 0; i < 100; i++) {
            childCount[i] = 0;
            for (int j = 0; j < 5; j++) {
                children[i][j] = 0;
            }
        }
        
        int relationshipCount = N - 1;
        for (int i = 0; i < relationshipCount; i++) {
            int parent = path[i][0];
            children[parent][childCount[parent]++] = path[i][1];
        }
    }
    
    int dfs(int N) {
        king = N;
        nextKing = -1;
        
        findNextKing(N);
        
        return nextKing;
    }
    
    private void findNextKing(int parent) {
        for (int i = 0; i < 5 && children[parent][i] != 0; i++) {
            int child = children[parent][i];
            
            if (king < child && nextKing == -1) {
                nextKing = child;
                return;
            }
            
            findNextKing(child);
        }
    }
}

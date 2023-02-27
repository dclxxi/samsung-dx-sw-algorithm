package _13_segment_tree.swea14726;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    
    private static final int[] a = new int[100000];
    private static final int[][] tree = new int[2][400000];
    private static final int MIN = 0, MAX = 1;
    
    private static int n;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; ++i) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            
            init();
            
            sb.append("#").append(testCase);
            
            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                
                switch (Integer.parseInt(st.nextToken())) {
                    case 0:
                        update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                        break;
                    case 1:
                        sb.append(" ")
                                .append(query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1))
                                .append(" ");
                        break;
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    
    private static void init() {
        init(0, n - 1, 1, MIN);
        init(0, n - 1, 1, MAX);
    }
    
    private static int init(int l, int r, int node, int type) {
        if (l == r) {
            return tree[type][node] = a[l];
        }
        
        int middle = l + (r - l) / 2;
        int child = node << 1;
        
        int leftValue = init(l, middle, child, type);
        int rightValue = init(middle + 1, r, child | 1, type);
        
        if (type == MIN) {
            return tree[type][node] = Math.min(leftValue, rightValue);
        }
        return tree[type][node] = Math.max(leftValue, rightValue);
    }
    
    private static void update(int i, int x) {
        update(i, x, 1, 0, n - 1, MIN);
        update(i, x, 1, 0, n - 1, MAX);
    }
    
    private static int update(int i, int x, int node, int left, int right, int type) {
        if (i < left || right < i) {
            return tree[type][node];
        }
        
        if (left == right) {
            return tree[type][node] = x;
        }
        
        int middle = left + (right - left) / 2;
        int child = node << 1;
        
        int leftValue = update(i, x, child, left, middle, type);
        int rightValue = update(i, x, child | 1, middle + 1, right, type);
        
        if (type == MIN) {
            return tree[type][node] = Math.min(leftValue, rightValue);
        }
        return tree[type][node] = Math.max(leftValue, rightValue);
    }
    
    private static int query(int l, int r) {
        return query(l, r, 1, 0, n - 1, MAX) - query(l, r, 1, 0, n - 1, MIN);
    }
    
    private static int query(int l, int r, int node, int left, int right, int type) {
        if (r < left || right < l) {
            if (type == MIN) {
                return Integer.MAX_VALUE;
            }
            
            return 0;
        }
        
        if (l <= left && right <= r) {
            return tree[type][node];
        }
        
        int middle = left + (right - left) / 2;
        int child = node << 1;
        
        int leftValue = query(l, r, child, left, middle, type);
        int rightValue = query(l, r, child | 1, middle + 1, right, type);
        
        if (type == MIN) {
            return Math.min(leftValue, rightValue);
        }
        return Math.max(leftValue, rightValue);
    }
}
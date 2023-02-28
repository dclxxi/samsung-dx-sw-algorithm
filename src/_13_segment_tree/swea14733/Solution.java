package _13_segment_tree.swea14733;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    
    private static final long[] tree = new long[200000];
    
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
                if ((i & 1) == 0) {
                    tree[i + n] = Integer.parseInt(st.nextToken());
                } else {
                    tree[i + n] = -Integer.parseInt(st.nextToken());
                }
            }
            
            for (int i = n - 1; i > 0; --i) {
                tree[i] = tree[i << 1] + tree[i << 1 | 1];
            }
            
            sb.append("#").append(testCase);
            
            for (int query = 0; query < q; query++) {
                st = new StringTokenizer(br.readLine(), " ");
                
                switch (Integer.parseInt(st.nextToken())) {
                    case 0:
                        update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                        break;
                    case 1:
                        sb.append(" ")
                                .append(query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())))
                                .append(" ");
                        break;
                }
            }
            
            sb.append("\n");
        }
        System.out.print(sb);
    }
    
    private static void update(int i, int x) {
        if ((i & 1) != 0) {
            x = -x;
        }
        tree[i += n] = x;
        
        while ((i >>= 1) != 0) {
            int child = i << 1;
            tree[i] = tree[child] + tree[child | 1];
        }
    }
    
    private static long query(int l, int r) {
        int i = l;
        
        long result = 0;
        for (l += n, r += n; l != r; l >>= 1, r >>= 1) {
            if ((l & 1) != 0) {
                result += tree[l++];
            }
            
            if ((r & 1) != 0) {
                result += tree[--r];
            }
        }
        
        if ((i & 1) != 0) {
            return -result;
        }
        
        return result;
    }
}
package _04_graph.swea1251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    
    private static final int[] X = new int[1000];
    private static final int[] Y = new int[1000];
    private static final int[] parents = new int[1000];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int island = 0; island < N; island++) {
                X[island] = Integer.parseInt(st.nextToken());
            }
            
            st = new StringTokenizer(br.readLine(), " ");
            for (int island = 0; island < N; island++) {
                Y[island] = Integer.parseInt(st.nextToken());
            }
            
            double E = Double.parseDouble(br.readLine());
            
            List<Road> roads = new ArrayList<>();
            for (int isLand1 = 0; isLand1 < N; isLand1++) {
                for (int isLand2 = isLand1 + 1; isLand2 < N; isLand2++) {
                    long lengthX = X[isLand1] - X[isLand2];
                    long lengthY = Y[isLand1] - Y[isLand2];
                    roads.add(new Road(isLand1, isLand2, lengthX * lengthX + lengthY * lengthY));
                }
            }
            
            Collections.sort(roads);
            
            for (int isLand = 0; isLand < N; isLand++) {
                parents[isLand] = isLand;
            }
            
            int count = N - 1;
            long length = 0;
            for (Road road : roads) {
                if (union(road.isLand1, road.isLand2)) {
                    length += road.length;
                    
                    if (--count == 0) {
                        break;
                    }
                }
            }
            
            sb.append("#").append(testCase).append(" ").append(Math.round(E * length)).append("\n");
        }
        System.out.print(sb);
    }
    
    private static boolean union(int isLand1, int isLand2) {
        isLand1 = findParent(isLand1);
        isLand2 = findParent(isLand2);
        
        if (isLand1 != isLand2) {
            parents[isLand2] = isLand1;
            return true;
        }
        
        return false;
    }
    
    private static int findParent(int isLand) {
        if (parents[isLand] == isLand) {
            return isLand;
        }
        
        return parents[isLand] = findParent(parents[isLand]);
    }
    
    static class Road implements Comparable<Road> {
        
        int isLand1, isLand2;
        double length;
        
        private Road(int isLand1, int isLand2, double length) {
            this.isLand1 = isLand1;
            this.isLand2 = isLand2;
            this.length = length;
        }
        
        @Override
        public int compareTo(Road o) {
            return Double.compare(this.length, o.length);
        }
    }
}

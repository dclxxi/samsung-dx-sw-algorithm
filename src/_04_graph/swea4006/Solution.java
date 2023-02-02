package _04_graph.swea4006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    
    private static final int[] parents = new int[50001];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            
            List<Road> roads = new ArrayList<>(M);
            for (int index = 0; index < M; index++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                roads.add(new Road(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())));
            }
            
            Collections.sort(roads);
            
            for (int city = 1; city <= N; city++) {
                parents[city] = city;
            }
            
            int count = N - 1;
            long cost = 0;
            for (Road road : roads) {
                if (union(road.s, road.e)) {
                    cost += road.c;
                    
                    if (--count == 0) {
                        break;
                    }
                }
            }
            
            sb.append("#").append(testCase).append(" ").append(cost).append("\n");
        }
        System.out.print(sb);
    }
    
    private static boolean union(int start, int end) {
        start = findParent(start);
        end = findParent(end);
        
        if (start != end) {
            parents[end] = start;
            return true;
        }
        
        return false;
    }
    
    private static int findParent(int city) {
        if (parents[city] == city) {
            return city;
        }
        
        return parents[city] = findParent(parents[city]);
    }
    
    static class Road implements Comparable<Road> {
        
        int s, e, c;
        
        private Road(int s, int e, int c) {
            this.s = s;
            this.e = e;
            this.c = c;
        }
        
        @Override
        public int compareTo(Road o) {
            return Integer.compare(this.c, o.c);
        }
    }
}


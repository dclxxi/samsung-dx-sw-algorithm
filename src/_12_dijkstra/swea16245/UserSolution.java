package _12_dijkstra.swea16245;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class UserSolution {
    
    private final int[] cities = new int[1400];
    
    private Map<Integer, Map<Integer, Integer>> forwardRoads;
    private Map<Integer, Map<Integer, Integer>> reverseRoads;
    private Map<Integer, Map<Integer, Integer>> costs;
    private int size;
    
    int init(int N, int[] sCity, int[] eCity, int[] mCost) {
        forwardRoads = new HashMap<>();
        reverseRoads = new HashMap<>();
        costs = new HashMap<>();
        
        size = 0;
        for (int i = 0; i < N; i++) {
            if (!forwardRoads.containsKey(sCity[i])) {
                forwardRoads.put(sCity[i], new HashMap<>());
                
                costs.put(sCity[i], new HashMap<>());
                cities[size++] = sCity[i];
            }
            forwardRoads.get(sCity[i]).put(eCity[i], mCost[i]);
            
            if (!reverseRoads.containsKey(eCity[i])) {
                reverseRoads.put(eCity[i], new HashMap<>());
            }
            reverseRoads.get(eCity[i]).put(sCity[i], mCost[i]);
        }
        
        return size;
    }
    
    void add(int sCity, int eCity, int mCost) {
        forwardRoads.get(sCity).put(eCity, mCost);
        reverseRoads.get(eCity).put(sCity, mCost);
    }
    
    int cost(int mHub) {
        return dijkstra(mHub, forwardRoads) + dijkstra(mHub, reverseRoads);
    }
    
    private int dijkstra(int mHub, Map<Integer, Map<Integer, Integer>> routes) {
        for (int i = 0; i < size; i++) {
            if (mHub != cities[i]) {
                costs.get(mHub).put(cities[i], routes.get(mHub).getOrDefault(cities[i], Integer.MAX_VALUE));
            } else {
                costs.get(mHub).put(cities[i], 0);
            }
        }
        
        Set<Integer> visited = new HashSet<>();
        visited.add(mHub);
        for (int i = 1; i < size; i++) {
            int waypoint = getMinCity(visited, mHub);
            
            visited.add(waypoint);
            for (int j = 0; j < size; j++) {
                if (visited.contains(cities[j])) {
                    continue;
                }
                
                if (routes.get(waypoint).containsKey(cities[j])) {
                    int cost = costs.get(mHub).get(waypoint) + routes.get(waypoint).get(cities[j]);
                    if (cost < costs.get(mHub).get(cities[j])) {
                        costs.get(mHub).replace(cities[j], cost);
                    }
                }
            }
        }
        
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += costs.get(mHub).get(cities[i]);
        }
        
        return sum;
    }
    
    private int getMinCity(Set<Integer> visited, int mHub) {
        int min = -1;
        
        for (int i = 0; i < size; i++) {
            if (visited.contains(cities[i])) {
                continue;
            }
            
            if (min < 0 || costs.get(mHub).get(min) > costs.get(mHub).get(cities[i])) {
                min = cities[i];
            }
        }
        
        return min;
    }
}

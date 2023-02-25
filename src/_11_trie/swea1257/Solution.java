package _11_trie.swea1257;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Solution {
    
    private static final char[] substrings = new char[400];
    
    private static List<Node> nodes;
    private static int K, size, count, length;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            K = Integer.parseInt(br.readLine());
            String str = br.readLine();
            
            nodes = new ArrayList<>();
            nodes.add(new Node());
            size = 1;
            
            int n = str.length();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    insert(str.substring(i, j));
                }
            }
            
            count = 0;
            sb.append("#").append(testCase).append(" ");
            
            if (find(0, 0)) {
                sb.append(substrings, 0, length);
            } else {
                sb.append("none");
            }
            
            sb.append("\n");
        }
        System.out.print(sb);
    }
    
    private static void insert(String substring) {
        int id = 0;
        
        int n = substring.length();
        for (int i = 0; i < n; i++) {
            int alphabet = substring.charAt(i) - 'a';
            if (nodes.get(id).children[alphabet] == 0) {
                nodes.get(id).children[alphabet] = size++;
                nodes.add(new Node());
            }
            
            id = nodes.get(id).children[alphabet];
        }
        
        nodes.get(id).terminal = true;
    }
    
    private static boolean find(int id, int index) {
        if (nodes.get(id).terminal) {
            count++;
        }
        
        if (K == count) {
            length = index;
            return true;
        }
        
        for (int i = 0; i < 26; i++) {
            int child = nodes.get(id).children[i];
            if (child != 0) {
                substrings[index] = (char) (i + 'a');
                
                if (find(child, index + 1)) {
                    return true;
                }
                
                substrings[index] = '\0';
            }
        }
        
        return false;
    }
    
    static class Node {
        
        int[] children = new int[26];
        boolean terminal;
    }
}

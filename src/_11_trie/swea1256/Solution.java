package _11_trie.swea1256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Solution {
    
    private static List<Node> nodes;
    private static int K, size;
    
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
                insert(str.substring(i));
            }
            
            sb.append("#").append(testCase).append(" ");
            find(sb);
            sb.append("\n");
        }
        System.out.println(sb);
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
            
            nodes.get(id).count++;
            id = nodes.get(id).children[alphabet];
        }
        
        nodes.get(id).count++;
        nodes.get(id).terminal = true;
    }
    
    private static void find(StringBuilder sb) {
        int id = 0;
        
        if (K > nodes.get(id).count) {
            sb.append("none");
            return;
        }
        
        int count = 0;
        while (!nodes.get(id).terminal) {
            for (int i = 0; i < 26; i++) {
                int child = nodes.get(id).children[i];
                if (child != 0) {
                    count += nodes.get(child).count;
                    
                    if (K <= count) {
                        sb.append((char) (i + 'a'));
                        
                        id = child;
                        count -= nodes.get(child).count;
                        break;
                    }
                }
            }
        }
    }
    
    static class Node {
        
        int[] children = new int[26];
        int count;
        boolean terminal;
    }
}

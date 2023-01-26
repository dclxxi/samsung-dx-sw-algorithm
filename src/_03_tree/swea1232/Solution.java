package _03_tree.swea1232;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    
    private static final Node[] nodes = new Node[1000];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        for (int testCase = 1; testCase <= 10; testCase++) {
            int N = Integer.parseInt(br.readLine());
            
            for (int node = 1; node <= N; node++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                st.nextToken();
                
                String str = st.nextToken();
                if (Character.isDigit(str.charAt(0))) {
                    nodes[node] = new Node(Integer.parseInt(str));
                } else {
                    nodes[node] = new Node(str, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                }
            }
            sb.append("#").append(testCase).append(" ").append((int) calculation(nodes[1])).append("\n");
        }
        System.out.println(sb);
    }
    
    
    private static double calculation(Node node) {
        if (node.operator == null) {
            return node.number;
        }
        
        double left = calculation(nodes[node.left]);
        double right = calculation(nodes[node.right]);
        
        switch (node.operator) {
            case "+":
                return left + right;
            
            case "-":
                return left - right;
            
            case "*":
                return left * right;
            
            case "/":
                return left / right;
        }
        
        return 0;
    }
    
    static class Node {
        
        int number;
        String operator;
        int left, right;
        
        private Node(int number) {
            this.number = number;
        }
        
        private Node(String operator, int left, int right) {
            this.operator = operator;
            this.left = left;
            this.right = right;
        }
    }
}
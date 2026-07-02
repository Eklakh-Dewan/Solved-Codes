import java.util.*;

class Solution {
    public int maxBalancedShipments(int[] weight) {
        int n = weight.length;
        int[] dp = new int[n + 1];
      
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            
            while (!stack.isEmpty() && weight[stack.peek()] <= weight[i]) {
                stack.pop();
            }
            
           
            dp[i + 1] = dp[i];
            
            if (!stack.isEmpty()) {
                int j = stack.peek();
                dp[i + 1] = Math.max(dp[i + 1], dp[j] + 1);
            }
            
            stack.push(i);
        }
        
        return dp[n];
    }
}
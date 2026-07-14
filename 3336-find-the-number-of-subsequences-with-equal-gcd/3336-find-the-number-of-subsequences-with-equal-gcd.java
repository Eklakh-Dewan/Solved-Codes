import java.util.*;

class Solution {
    public int subsequencePairCount(int[] nums) {
        int MOD = 1_000_000_007;
        int maxVal = 200;
        
        // dp[i][j] stores the number of ways to have two disjoint subsequences
        // with GCD i and GCD j.
        long[][] dp = new long[maxVal + 1][maxVal + 1];
        
        for (int x : nums) {
            long[][] nextDp = new long[maxVal + 1][maxVal + 1];
            
            // Carry over previous states
            for (int i = 0; i <= maxVal; i++) {
                for (int j = 0; j <= maxVal; j++) {
                    nextDp[i][j] = dp[i][j];
                }
            }
            
            // Start a new sequence with x or update existing sequences
            for (int i = 0; i <= maxVal; i++) {
                for (int j = 0; j <= maxVal; j++) {
                    if (dp[i][j] == 0) continue;
                    
                    // Case 1: Add x to the first subsequence
                    int nextG1 = (i == 0) ? x : gcd(i, x);
                    nextDp[nextG1][j] = (nextDp[nextG1][j] + dp[i][j]) % MOD;
                    
                    // Case 2: Add x to the second subsequence
                    int nextG2 = (j == 0) ? x : gcd(j, x);
                    nextDp[i][nextG2] = (nextDp[i][nextG2] + dp[i][j]) % MOD;
                }
            }
            
            // Case 3: Start first subsequence with x
            nextDp[x][0] = (nextDp[x][0] + 1) % MOD;
            
            // Case 4: Start second subsequence with x
            nextDp[0][x] = (nextDp[0][x] + 1) % MOD;
            
            dp = nextDp;
        }
        
        long ans = 0;
        for (int i = 1; i <= maxVal; i++) {
            ans = (ans + dp[i][i]) % MOD;
        }
        
        return (int) ans;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
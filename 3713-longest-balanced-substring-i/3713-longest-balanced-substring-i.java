class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxLen = 0;
        
        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'a']++;
                
                if (isBalanced(freq)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }
    
    private boolean isBalanced(int[] freq) {
        int count = -1;
        for (int f : freq) {
            if (f == 0) continue;
            if (count == -1) {
                count = f;
            } else if (f != count) {
                return false;
            }
        }
        return count != -1;
    }
}
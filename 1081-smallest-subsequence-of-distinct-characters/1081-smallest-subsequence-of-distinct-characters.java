class Solution {
    public String smallestSubsequence(String s) {
        // Track the last index of each character
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        boolean[] seen = new boolean[26];
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            
            // If already in stack, skip
            if (seen[curr - 'a']) continue;
            
            // Maintain monotonic property:
            // Pop if top is > curr AND we can find another occurrence of top later
            while (!stack.isEmpty() && stack.peek() > curr && i < lastIndex[stack.peek() - 'a']) {
                seen[stack.pop() - 'a'] = false;
            }
            
            stack.push(curr);
            seen[curr - 'a'] = true;
        }
        
        // Build the resulting string
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        
        return sb.toString();
    }
}
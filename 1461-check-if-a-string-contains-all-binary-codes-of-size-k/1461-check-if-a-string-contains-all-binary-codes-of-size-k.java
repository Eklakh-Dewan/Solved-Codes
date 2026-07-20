class Solution {
    public boolean hasAllCodes(String s, int k) {
        int target = 1 << k;
        Set<String> seen = new HashSet<>();
        
        for (int i = 0; i <= s.length() - k; i++) {
            seen.add(s.substring(i, i + k));
            if (seen.size() == target) {
                return true;
            }
        }
        
        return seen.size() == target;
    }
}
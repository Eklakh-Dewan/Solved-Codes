class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(String s, int start, List<String> parts, List<String> res) {
        if (parts.size() == 4) {
            if (start == s.length()) {
                res.add(String.join(".", parts));
            }
            return;
        }

        for (int len = 1; len <= 3; len++) {
            if (start + len > s.length()) break;
            
            String segment = s.substring(start, start + len);
            
            if (isValid(segment)) {
                parts.add(segment);
                backtrack(s, start + len, parts, res);
                parts.remove(parts.size() - 1);
            }
        }
    }

    private boolean isValid(String s) {
        if (s.length() > 1 && s.startsWith("0")) return false;
        int val = Integer.parseInt(s);
        return val <= 255;
    }
}
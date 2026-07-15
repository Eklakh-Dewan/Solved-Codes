class Solution {
    public String makeLargestSpecial(String s) {
        List<String> mountains = new ArrayList<>();
        int count = 0; // Tracks balance of 1s and 0s
        int anchor = 0; // Start index of current mountain
        
        // Decompose the string into "mountains"
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                count++;
            } else {
                count--;
            }
            
            // When count hits 0, a complete special substring is found
            if (count == 0) {
                // The substring is "1" + result of inner part + "0"
                // We recursively call makeLargestSpecial on the inner part
                String inner = s.substring(anchor + 1, i);
                mountains.add("1" + makeLargestSpecial(inner) + "0");
                anchor = i + 1;
            }
        }
        
        // Sort mountains in descending order to get the lexicographically largest string
        Collections.sort(mountains, Collections.reverseOrder());
        
        // Join the sorted mountains
        StringBuilder result = new StringBuilder();
        for (String m : mountains) {
            result.append(m);
        }
        
        return result.toString();
    }
}
class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        String t = "1" + s + "1";

        List<Character> chars = new ArrayList<>();
        List<Integer> lens = new ArrayList<>();

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (chars.isEmpty() || chars.get(chars.size() - 1) != c) {
                chars.add(c);
                lens.add(1);
            } else {
                lens.set(lens.size() - 1, lens.get(lens.size() - 1) + 1);
            }
        }

        int bestGain = 0;

        for (int i = 1; i < chars.size() - 1; i++) {
            if (chars.get(i) == '1') {
                bestGain = Math.max(bestGain, lens.get(i - 1) + lens.get(i + 1));
            }
        }

        return ones + bestGain;
    }
}
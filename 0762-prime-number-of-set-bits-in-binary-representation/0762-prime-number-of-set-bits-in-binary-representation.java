class Solution {
    public int countPrimeSetBits(int left, int right) {
        int count = 0;
        
        int primeMask = 665772; 

        for (int i = left; i <= right; i++) {
            int setBits = Integer.bitCount(i);
            if (((primeMask >> setBits) & 1) == 1) {
                count++;
            }
        }
        return count;
    }
}
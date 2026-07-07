class Solution {
    public long sumAndMultiply(int n) {
        
        if (n == 0) return 0;
        
        long x = 0;
        long sum = 0;
        long multiplier = 1;
        
        
        String s = String.valueOf(n);
        
      
        for (int i = s.length() - 1; i >= 0; i--) {
            int digit = s.charAt(i) - '0';
            if (digit != 0) {
                x += (long) digit * multiplier;
                sum += digit;
                multiplier *= 10;
            }
        }
        
        return x * sum;
    }
}
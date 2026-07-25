import java.util.*;

class Solution {
    public int maxProduct(int n) {
        List<Integer> digits = new ArrayList<>();
        
        // Extract all digits
        int temp = n;
        while (temp > 0) {
            digits.add(temp % 10);
            temp /= 10;
        }
        
        // Sort digits to easily find the two largest
        Collections.sort(digits);
        
        // Multiply the last two (largest) digits
        int size = digits.size();
        return digits.get(size - 1) * digits.get(size - 2);
    }
}
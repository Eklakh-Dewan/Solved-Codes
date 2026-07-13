import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        
        // Iterate through all possible starting digits (1-9)
        for (int i = 1; i <= 9; i++) {
            int num = i;
            int nextDigit = i + 1;
            
            // Build the number digit by digit
            while (num <= high && nextDigit <= 9) {
                num = num * 10 + nextDigit;
                
                // Add to result if it falls within the range
                if (num >= low && num <= high) {
                    result.add(num);
                }
                nextDigit++;
            }
        }
        
        // Sort the list before returning
        Collections.sort(result);
        return result;
    }
}
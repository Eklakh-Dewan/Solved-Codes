import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        // Clone the original array to preserve the initial order
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        
        // Map to store the element and its corresponding rank
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        
        // Assign ranks to unique sorted elements
        for (int num : sortedArr) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank);
                rank++;
            }
        }
        
        // Transform the original array using the mapped ranks
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rankMap.get(arr[i]);
        }
        
        return arr;
    }
}
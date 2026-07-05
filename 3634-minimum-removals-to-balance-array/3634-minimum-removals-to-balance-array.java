import java.util.Arrays;

class Solution {
    public int minRemoval(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int maxWindowSize = 0;
        int i = 0;
        
        // Use sliding window to find the longest valid subarray
        for (int j = 0; j < n; j++) {
            // Shrink window from the left if the condition is violated
            while ((long) nums[j] > (long) k * nums[i]) {
                i++;
            }
            // Update the maximum window size found so far
            maxWindowSize = Math.max(maxWindowSize, j - i + 1);
        }
        
        return n - maxWindowSize;
    }
}
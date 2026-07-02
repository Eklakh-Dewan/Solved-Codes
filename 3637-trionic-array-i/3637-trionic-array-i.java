class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        if (n < 4) return false;

        int p = 0;
        // Step 1: Find end of strictly increasing part
        while (p < n - 1 && nums[p] < nums[p + 1]) {
            p++;
        }
        
        // p must be > 0 and not at the very end
        if (p == 0 || p == n - 1) return false;

        int q = p;
        // Step 2: Find end of strictly decreasing part
        while (q < n - 1 && nums[q] > nums[q + 1]) {
            q++;
        }

        // q must be > p and not at the very end
        if (q == p || q == n - 1) return false;

        // Step 3: Check if the remainder is strictly increasing
        for (int i = q; i < n - 1; i++) {
            if (nums[i] >= nums[i + 1]) return false;
        }

        return true;
    }
}
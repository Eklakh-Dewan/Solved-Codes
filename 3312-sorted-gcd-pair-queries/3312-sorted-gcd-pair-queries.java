class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxNum = 0;
        for (int num : nums) maxNum = Math.max(maxNum, num);
        
        long[] cnt = new long[maxNum + 1];
        for (int num : nums) cnt[num]++;
        
        // Count pairs with GCD being a multiple of g
        long[] countG = new long[maxNum + 1];
        for (int g = 1; g <= maxNum; g++) {
            long multiples = 0;
            for (int i = g; i <= maxNum; i += g) {
                multiples += cnt[i];
            }
            countG[g] = multiples * (multiples - 1) / 2;
        }
        
        // Inclusion-Exclusion to get exact count for each GCD
        for (int g = maxNum; g >= 1; g--) {
            for (int i = 2 * g; i <= maxNum; i += g) {
                countG[g] -= countG[i];
            }
        }
        
        // Prefix sum to search indices
        long[] prefixSum = new long[maxNum + 1];
        for (int i = 1; i <= maxNum; i++) {
            prefixSum[i] = prefixSum[i - 1] + countG[i];
        }
        
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];
            // Binary search to find the smallest g such that prefixSum[g] > q
            int left = 1, right = maxNum;
            int ans = maxNum;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (prefixSum[mid] > q) {
                    ans = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            result[i] = ans;
        }
        
        return result;
    }
}
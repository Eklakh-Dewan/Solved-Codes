class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // comp[i] will store the connected component ID for node i
        int[] comp = new int[n];
        int currentComp = 0;
        
        // Traverse the sorted array to assign component IDs
        // If the gap between adjacent elements is > maxDiff, it's a new component
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                currentComp++;
            }
            comp[i] = currentComp;
        }
        
        // Answer each query in O(1) by comparing component IDs
        int q = queries.length;
        boolean[] ans = new boolean[q];
        for (int i = 0; i < q; i++) {
            ans[i] = (comp[queries[i][0]] == comp[queries[i][1]]);
        }
        
        return ans;
    }
}
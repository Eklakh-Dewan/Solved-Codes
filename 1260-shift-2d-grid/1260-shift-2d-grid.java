class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        
        k = k % total;
        
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            result.add(new ArrayList<>(Collections.nCopies(n, 0)));
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int originalIndex = i * n + j;
                int newIndex = (originalIndex + k) % total;
                
                int newRow = newIndex / n;
                int newCol = newIndex % n;
                
                result.get(newRow).set(newCol, grid[i][j]);
            }
        }
        
        return result;
    }
}
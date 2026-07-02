import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        // minCost[r][c] stores the minimum unsafe cells visited to reach (r, c)
        int[][] minCost = new int[m][n];
        for (int[] row : minCost) Arrays.fill(row, Integer.MAX_VALUE);
        
        // Start cell
        int startCost = grid.get(0).get(0);
        minCost[0][0] = startCost;
        
        // Deque for 0-1 BFS: [cost, r, c]
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{startCost, 0, 0});
        
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
        while (!deque.isEmpty()) {
            int[] current = deque.pollFirst();
            int cost = current[0];
            int r = current[1];
            int c = current[2];
            
            if (cost >= health) continue;
            
            for (int[] d : directions) {
                int nr = r + d[0];
                int nc = c + d[1];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nextCost = cost + grid.get(nr).get(nc);
                    
                    if (nextCost < minCost[nr][nc]) {
                        minCost[nr][nc] = nextCost;
                        // 0-1 BFS: If cost increases, push to back; if same, push to front
                        if (grid.get(nr).get(nc) == 1) {
                            deque.addLast(new int[]{nextCost, nr, nc});
                        } else {
                            deque.addFirst(new int[]{nextCost, nr, nc});
                        }
                    }
                }
            }
        }
        
        return minCost[m - 1][n - 1] < health;
    }
}

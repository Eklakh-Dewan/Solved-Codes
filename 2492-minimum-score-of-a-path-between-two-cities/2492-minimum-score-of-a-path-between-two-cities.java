class Solution {
    public int minScore(int n, int[][] roads) {
        // Build adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            adj.get(road[0]).add(new int[]{road[1], road[2]});
            adj.get(road[1]).add(new int[]{road[0], road[2]});
        }

        int minScore = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        
        // Start BFS from city 1
        queue.add(1);
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            for (int[] edge : adj.get(curr)) {
                int neighbor = edge[0];
                int distance = edge[1];
                
                // Update minimum score found so far
                minScore = Math.min(minScore, distance);
                
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        
        return minScore;
    }
}
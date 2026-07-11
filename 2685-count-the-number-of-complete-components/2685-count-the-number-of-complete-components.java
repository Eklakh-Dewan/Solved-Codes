class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        java.util.List<java.util.List<Integer>> adj = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new java.util.ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                java.util.List<Integer> component = new java.util.ArrayList<>();
                java.util.Queue<Integer> queue = new java.util.LinkedList<>();
                
                queue.add(i);
                visited[i] = true;
                
                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    component.add(u);
                    for (int v : adj.get(u)) {
                        if (!visited[v]) {
                            visited[v] = true;
                            queue.add(v);
                        }
                    }
                }

                long nodes = component.size();
                long edgesCount = 0;
                for (int node : component) {
                    edgesCount += adj.get(node).size();
                }
                edgesCount /= 2;

                if (edgesCount == nodes * (nodes - 1) / 2) {
                    completeComponents++;
                }
            }
        }
        return completeComponents;
    }
}
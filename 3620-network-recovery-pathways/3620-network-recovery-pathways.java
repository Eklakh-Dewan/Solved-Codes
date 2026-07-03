import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<List<int[]>> adj = new ArrayList<>();
        int minW = Integer.MAX_VALUE, maxW = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
            minW = Math.min(minW, e[2]);
            maxW = Math.max(maxW, e[2]);
        }

        int low = minW, high = maxW, result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(n, adj, online, k, mid)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    private boolean isPossible(int n, List<List<int[]>> adj, boolean[] online, long k, int threshold) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int u = (int) curr[0];
            long d = curr[1];

            if (d > dist[u]) continue;
            if (u == n - 1) return d <= k;

            for (int[] edge : adj.get(u)) {
                int v = edge[0], w = edge[1];
                
                // Rule: Intermediate nodes must be online
                if (w >= threshold && (v == n - 1 || online[v])) {
                    if (dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                        pq.offer(new long[]{v, dist[v]});
                    }
                }
            }
        }
        return false;
    }
}
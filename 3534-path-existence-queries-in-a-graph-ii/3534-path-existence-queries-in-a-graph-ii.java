class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        java.util.Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] pos = new int[n];
        int[] comp = new int[n];
        int[] values = new int[n];

        int cid = 0;
        for (int i = 0; i < n; i++) {
            values[i] = arr[i][0];
            if (i > 0 && values[i] - values[i - 1] > maxDiff) {
                cid++;
            }
            comp[i] = cid;
            pos[arr[i][1]] = i;
        }

        // next[i] = farthest index reachable in one step
        int[] next = new int[n];
        int r = 0;
        for (int l = 0; l < n; l++) {
            if (r < l) r = l;
            while (r < n && values[r] - values[l] <= maxDiff) {
                r++;
            }
            next[l] = r - 1;
        }

        int LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        int[][] up = new int[LOG][n];
        for (int i = 0; i < n; i++) {
            up[0][i] = next[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {
            int u = queries[qi][0];
            int v = queries[qi][1];

            if (u == v) {
                ans[qi] = 0;
                continue;
            }

            int a = pos[u];
            int b = pos[v];

            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }

            if (comp[a] != comp[b]) {
                ans[qi] = -1;
                continue;
            }

            int cur = a;
            int steps = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][cur] < b) {
                    cur = up[k][cur];
                    steps += 1 << k;
                }
            }

            if (cur < b) steps++;

            ans[qi] = steps;
        }

        return ans;
    }
}
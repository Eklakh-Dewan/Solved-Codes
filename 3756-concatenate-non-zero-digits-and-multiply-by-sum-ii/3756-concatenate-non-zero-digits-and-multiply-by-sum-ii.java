class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        int MOD = 1_000_000_007;

        // Store values and positions of non-zero digits
        List<Integer> nonZeroDigits = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != '0') {
                nonZeroDigits.add(s.charAt(i) - '0');
                indices.add(i);
            }
        }

        int n = nonZeroDigits.size();
        if (n == 0) return new int[queries.length];

        // Prefix sums for digit sum calculation
        long[] prefSum = new long[n + 1];
        // Prefix values for constructing the number x
        long[] prefVal = new long[n + 1];
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 0; i < n; i++) {
            prefSum[i + 1] = (prefSum[i] + nonZeroDigits.get(i)) % MOD;
            pow10[i + 1] = (pow10[i] * 10) % MOD;
            // The number is formed by concatenating: val[0]*10^(n-1) + val[1]*10^(n-2) ...
            // We store prefix such that we can extract substring value
            prefVal[i + 1] = (prefVal[i] * 10 + nonZeroDigits.get(i)) % MOD;
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            // Find range [start, end] in our nonZeroDigits list
            int start = lowerBound(indices, l);
            int end = upperBound(indices, r);

            if (start >= end) {
                answer[i] = 0;
            } else {
                long sum = (prefSum[end] - prefSum[start] + MOD) % MOD;
                // Calculate substring value: (prefVal[end] - prefVal[start] * 10^(len)) % MOD
                long x = (prefVal[end] - (prefVal[start] * pow10[end - start]) % MOD + MOD) % MOD;
                answer[i] = (int) ((x * sum) % MOD);
            }
        }
        return answer;
    }

    private int lowerBound(List<Integer> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) >= target) high = mid;
            else low = mid + 1;
        }
        return low;
    }

    private int upperBound(List<Integer> list, int target) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) <= target) low = mid + 1;
            else high = mid;
        }
        return low;
    }
}
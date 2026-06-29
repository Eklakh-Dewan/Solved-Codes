import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;

        int maxPoints = 0;

        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeMap = new HashMap<>();
            int currentMax = 0;

            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                
                int common = gcd(dx, dy);
                dx /= common;
                dy /= common;

                String slope = dy + "/" + dx;
                slopeMap.put(slope, slopeMap.getOrDefault(slope, 0) + 1);
                currentMax = Math.max(currentMax, slopeMap.get(slope));
            }
            maxPoints = Math.max(maxPoints, currentMax + 1);
        }

        return maxPoints;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
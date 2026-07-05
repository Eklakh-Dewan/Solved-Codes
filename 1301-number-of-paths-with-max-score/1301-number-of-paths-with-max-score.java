class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int mod = 1_000_000_007;
        int[][] dpScore = new int[n][n];
        int[][] dpPaths = new int[n][n];
        
        for (int[] row : dpScore) Arrays.fill(row, -1);
        
        dpScore[n - 1][n - 1] = 0;
        dpPaths[n - 1][n - 1] = 1;
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                char c = board.get(i).charAt(j);
                if (c == 'X' || (i == n - 1 && j == n - 1)) continue;
                
                int maxScore = -1;
                int paths = 0;
                
                // Possible moves: down, right, diagonal
                int[][] dirs = {{0, 1}, {1, 0}, {1, 1}};
                for (int[] d : dirs) {
                    int ni = i + d[0], nj = j + d[1];
                    if (ni < n && nj < n && dpScore[ni][nj] != -1) {
                        if (dpScore[ni][nj] > maxScore) {
                            maxScore = dpScore[ni][nj];
                            paths = dpPaths[ni][nj];
                        } else if (dpScore[ni][nj] == maxScore) {
                            paths = (paths + dpPaths[ni][nj]) % mod;
                        }
                    }
                }
                
                if (maxScore != -1) {
                    dpScore[i][j] = maxScore + (c == 'E' ? 0 : c - '0');
                    dpPaths[i][j] = paths;
                }
            }
        }
        
        return dpScore[0][0] == -1 ? new int[]{0, 0} : new int[]{dpScore[0][0], dpPaths[0][0]};
    }
}
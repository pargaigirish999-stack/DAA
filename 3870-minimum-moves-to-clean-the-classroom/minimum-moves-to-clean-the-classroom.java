import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        // Find starting position and litter positions
        int sr = 0, sc = 0;
        List<int[]> litter = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                }

                if (ch == 'L') {
                    litter.add(new int[]{i, j});
                }
            }
        }

        int k = litter.size();

        // mask = 1 means litter is still not collected
        int fullMask = (1 << k) - 1;

        Queue<int[]> queue = new LinkedList<>();

        // row, col, energy, mask, moves
        queue.offer(new int[]{sr, sc, energy, fullMask, 0});

        // visited[row][col][energy][mask]
        boolean[][][][] visited =
                new boolean[m][n][energy + 1][1 << k];

        visited[sr][sc][energy][fullMask] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int[] curr = queue.poll();

            int r = curr[0];
            int c = curr[1];
            int e = curr[2];
            int mask = curr[3];
            int moves = curr[4];

            // All litter collected
            if (mask == 0) {
                return moves;
            }

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside grid
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                // Wall
                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                int newEnergy = e - 1;

                // No energy to move
                if (newEnergy < 0) {
                    continue;
                }

                // Recharge
                if (classroom[nr].charAt(nc) == 'R') {
                    newEnergy = energy;
                }

                int newMask = mask;

                // Check if this cell contains litter
                for (int i = 0; i < k; i++) {

                    if (litter.get(i)[0] == nr &&
                        litter.get(i)[1] == nc) {

                        newMask &= ~(1 << i);
                        break;
                    }
                }

                if (!visited[nr][nc][newEnergy][newMask]) {

                    visited[nr][nc][newEnergy][newMask] = true;

                    queue.offer(new int[]{
                        nr, nc, newEnergy, newMask, moves + 1
                    });
                }
            }
        }

        return -1;
    }
}
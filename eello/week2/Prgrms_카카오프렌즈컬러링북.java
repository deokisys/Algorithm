import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    
    private static class Pos {
        int y;
        int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public int[] solution(int m, int n, int[][] picture) {
        boolean[][] visit = new boolean[m][n];

        int maxArea = 0;
        int section = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] > 0 && !visit[i][j]) {
                    section++;
                    maxArea = Math.max(maxArea, bfs(picture, visit, i, j));
                }
            }
        }

        return new int[]{section, maxArea};
    }

    private int bfs(int[][] picture, boolean[][] visit, int y, int x) {
        Queue<Pos> q = new ArrayDeque<>();

        q.add(new Pos(y, x));
        visit[y][x] = true;

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int area = 1;
        while (!q.isEmpty()) {
            Pos pos = q.poll();

            for (int[] d : dir) {
                int ny = pos.y + d[0];
                int nx = pos.x + d[1];

                if (checkRange(picture, ny, nx) && !visit[ny][nx] && picture[ny][nx] == picture[y][x]) {
                    visit[ny][nx] = true;
                    area++;
                    q.add(new Pos(ny, nx));
                }
            }
        }

        return area;
    }

    private boolean checkRange(int[][] picture, int y, int x) {
        return 0 <= y && y < picture.length && 0 <= x && x < picture[0].length;
    }
}
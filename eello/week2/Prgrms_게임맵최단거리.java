import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    
    private static class Pos {
        int y;
        int x;
        int moveCnt;

        public Pos(int y, int x, int moveCnt) {
            this.y = y;
            this.x = x;
            this.moveCnt = moveCnt;
        }
    }

    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;

        Queue<Pos> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[n][m];

        q.add(new Pos(0, 0, 1));
        visit[0][0] = true;

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!q.isEmpty()) {
            Pos pos = q.poll();

            if (pos.y == n - 1 && pos.x == m - 1) {
                return pos.moveCnt;
            }

            for (int[] d : dir) {
                int ny = pos.y + d[0];
                int nx = pos.x + d[1];

                if (checkRange(maps, ny, nx) && maps[ny][nx] == 1 && !visit[ny][nx]) {
                    visit[ny][nx] = true;
                    q.add(new Pos(ny, nx, pos.moveCnt + 1));
                }
            }
        }
        
        return -1;
    }

    private boolean checkRange(int[][] maps, int y, int x) {
        return 0 <= y && y < maps.length && 0 <= x && x < maps[0].length;
    }
}
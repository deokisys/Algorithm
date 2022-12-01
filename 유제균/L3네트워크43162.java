import java.util.*;

class L3네트워크43162 {
    static int N;
    static boolean visited[];
    static Queue<Integer> q;

    public static void bfs(int n, int[][] computers) {
        q.add(n);
        visited[n] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 0; i < N; i++) {
                if (computers[now][i] == 1) {
                    if (visited[i])
                        continue;

                    visited[i] = true;
                    q.add(i);
                }
            }

        }
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        N = n;
        q = new LinkedList<>();
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (visited[i])
                continue;
            answer++;
            bfs(i, computers);
        }

        return answer;
    }
}
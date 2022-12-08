import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] computers) {
        int network = 0;

        boolean[] visit = new boolean[n];
        for (int i = 0; i < n; i++) {
            // i번째 노드를 방문하지 않았다면
            if (!visit[i]) {
                network++; // 네트워크 개수 추가
                bfs(i, computers, visit); // bfs로 i번부터 시작해서 갈 수 있는 모든 노드 방문
            }
        }

        return network;
    }

    private void bfs(int start, int[][] computers, boolean[] visit) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 0; i < computers.length; i++) {
                if (!visit[i] && computers[cur][i] == 1) {
                    q.add(i);
                    visit[i] = true;
                }
            }
        }
    }
}
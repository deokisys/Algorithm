package programmers;

import java.util.*;

public class 게임맵최단거리 {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static class Point {
		int x, y, dist;

		public Point(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) {
		int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
		System.out.println(solution(maps));
	}

	public static int solution(int[][] maps) {
        int answer = bfs(maps);
        return answer;
    }
	
	public static int bfs(int[][] maps) {
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(0, 0, 1));
		int N = maps.length;
		int M = maps[0].length;
		boolean[][] visited = new boolean[N][M];
		visited[0][0] = true;
		
		while(!que.isEmpty()) {
			Point p = que.poll();
			
			if(p.x == N-1 && p.y == M-1) return p.dist;
			
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if(!visited[nx][ny] && maps[nx][ny] == 1) {
						visited[nx][ny] = true;
						que.add(new Point(nx, ny, p.dist+1));
					}
				}
			}
		}
		
		return -1;
	}
}

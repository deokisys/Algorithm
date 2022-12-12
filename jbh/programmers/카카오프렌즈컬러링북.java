package programmers;

import java.util.*;

public class 카카오프렌즈컬러링북 {
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		int m = 6; // 행
		int n = 4; // 열
		int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
		System.out.println(Arrays.toString(solution(m, n, picture)));
	}
	
	public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];

        for(int i = 0; i < picture.length; i++) {
        	for(int j = 0; j < picture[i].length; j++) {
        		if(!visited[i][j] && picture[i][j] != 0) {
        			numberOfArea++;
        			maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(i, j, picture));
        		}
        	}
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
	
	public static int bfs(int x, int y, int[][] picture) {
		Queue<Point> que = new LinkedList<>();
		visited[x][y] = true;
		que.add(new Point(x, y));
		int count = 1;
		
		while(!que.isEmpty()) {
			Point p = que.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx >= 0 && ny >= 0 && nx < picture.length && ny < picture[0].length) {
					if(!visited[nx][ny] && picture[nx][ny] == picture[p.x][p.y]) {
						visited[nx][ny] = true;
						que.add(new Point(nx, ny));
						count++;
					}
				}
			}
		}
		
		return count;
	}

}

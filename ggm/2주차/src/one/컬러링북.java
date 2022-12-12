package one;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * bfs를 통해 상하 좌우에 같은 숫자가 있는지 판별하여 있게 되면 cnt를 1씩 늘리고 최종 적으로 max에 cnt를 비교하여
 * 최대 값을 갱신해준다.
 * 
 * 
 */
public class 컬러링북 {
	
	static class Node{
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + "]";
		}
		
		
	}
	
	static boolean[][] v;
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,-1,0,1};

	public static void main(String[] args) {
		
		int m = 6;
		int n = 4;
		int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
		System.out.println(Arrays.toString(solution(m, n, picture)));
	}
	
	public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        
        v = new boolean[m][n];
        
        for(int i = 0; i < m; i++) {
        	for(int j = 0; j < n; j++) {
        		if(!v[i][j] && picture[i][j] > 0) {
        			numberOfArea++;			
        			int cnt = bfs(i,j , m, n, picture);
        			maxSizeOfOneArea = Math.max(cnt, maxSizeOfOneArea);
        		}
        	}
        }
       

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

	private static int bfs(int r, int c, int m, int n, int[][] picture) {
		Queue<Node> que = new LinkedList<>();
		que.add(new Node(r, c));
		v[r][c] = true;
		
		int cnt = 1;
		int num = picture[r][c];
		
		while(!que.isEmpty()) {
			Node cur = que.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(check(nr, nc, m, n) && !v[nr][nc] && picture[nr][nc] == num) {
					v[nr][nc] = true;
					cnt++;
					que.add(new Node(nr, nc));
				}
			}
		}
		
		
		
		return cnt;
	}

	private static boolean check(int nr, int nc, int m, int n) {
		if(nr >= 0 && nr < m && nc >= 0 && nc < n) {
			return true;
		}
		return false;
	}

	

	
	
	

}

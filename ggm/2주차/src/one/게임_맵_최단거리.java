package one;

import java.util.LinkedList;
import java.util.Queue;

public class 게임_맵_최단거리 {
	
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
	static int[] dc = {0,1,0,-1};
	
	static int R;
	static int C;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
		System.out.println(solution(maps));

	}
	
	public static int solution(int[][] maps) {
        int answer = 0;
        
        R = maps.length;
        C = maps[R-1].length;
        
//        System.out.println(R + " " + C);
        
        v = new boolean[R][C];
        
        
        
        answer = bfs(maps);
        
        
        return answer;
    }

	private static int bfs(int[][] maps) {
		Queue<Node> que = new LinkedList<>();
		que.add(new Node(0, 0));
		v[0][0] = true;
		
		int cnt = 0;
		
		while(!que.isEmpty()) {
			int size = que.size();
			
			for(int k = 0; k < size; k++) {
				Node cur = que.poll();
//				System.out.println(cur);
				
				if(cur.r == R -1 && cur.c == C - 1) {
					return cnt + 1;
				}
				
				for(int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
//					System.out.println(check(nr, nc));
					if(check(nr, nc) && !v[nr][nc] && maps[nr][nc] == 1) {
						v[nr][nc] = true;
						que.add(new Node(nr, nc));
					}
				}
				
			}
			
			cnt++;
		}
		
		return -1;
		
		
	}

	private static boolean check(int nr, int nc) {
		
		if(nr >= 0 && nr < R && nc >= 0 && nc < C) {
			return true;
		}
		return false;
	}

	

	
	
	
	

}

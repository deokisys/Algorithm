package programmers;

import java.util.*;

public class 네트워크 {
	public static void main(String[] args) {
		int n = 3;
		int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};

		System.out.println(solution(n, computers));
	}
	
	public static int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        
        for(int i = 0; i < n; i++) {
        	if(!visited[i]) {
        		bfs(i, n, computers, visited);
        		answer++;
        	}
        }
        return answer;
    }
	
	public static void bfs(int x, int n, int[][] computers, boolean[] visited) {
		Queue<Integer> que = new LinkedList<>();
		que.add(x);
		visited[x] = true;
		
		while(!que.isEmpty()) {
			int num = que.poll();
			
			for(int i = 0; i < n; i++) {
				if(computers[num][i] == 1 && !visited[i]) {
					que.add(i);
					visited[i] = true;
				}
			}
		}
	}

}

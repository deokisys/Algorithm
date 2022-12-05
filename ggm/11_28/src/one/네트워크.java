package one;

public class 네트워크 {
	
	static boolean[] v;
	static int cnt;

	public static void main(String[] args) {
		int n = 3;
		int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
		
		System.out.println(solution(n, computers));

	}
	
	public static int solution(int n, int[][] computers) {
        
        
        
        v = new boolean[computers.length];
        cnt = 0;
        for(int i = 0; i < computers.length; i++) {
        	if(!v[i]) {
//        		System.out.println(i);
        		dfs(i, computers);
        		cnt++;
//        		System.out.println();
        	}
        }
        
        int answer = cnt;
        
        return answer;
    }

	private static void dfs(int idx, int[][] computers) {
		v[idx] = true;
//		System.out.print(idx + " ");
		for(int i = 0; i < computers[idx].length; i++) {
			if(i != idx) {
				if(!v[i] && computers[idx][i] == 1) {
					dfs(i, computers);
				}
			}
		}
		
	}

}

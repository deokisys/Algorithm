import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        //네트워크의 개수?
        //bfs를 진행
        
        boolean[] visited = new boolean[n];
        for(int i=0;i<n;i++){
            if(!visited[i]){
                bfs(i,visited,computers);
                answer+=1;
            }
        }
        
        return answer;
    }
    public void bfs(int start,boolean[] visited, int[][] adjMap){
        Queue<Integer> que = new ArrayDeque<>();
        que.offer(start);
        visited[start] = true;
        
        while(!que.isEmpty()){
            int cur = que.poll();
            for(int next=0;next<adjMap.length;next++){
                if(adjMap[cur][next]==1 &&!visited[next]){
                    visited[next] = true;
                    que.offer(next);
                }
            }
        }
    }
}
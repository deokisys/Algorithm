import java.util.*;
class Solution {
    class Pos{
        int r,c,dist;
        Pos(int r, int c,int dist){
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
    public int solution(int[][] maps) {
        int answer = 0;
        answer = bfs(maps);
        return answer;
    }
    
    public int bfs(int[][] map){
        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};
        
        Queue<Pos> que = new ArrayDeque<>();
        que.offer(new Pos(0,0,1)); 
        
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[0][0] = true;
        int result = -1;
        while(!que.isEmpty()){
            Pos cur = que.poll();
            if(cur.r==map.length-1&&cur.c==map[0].length-1){
                result = cur.dist;
                break;
            }
            for(int d=0;d<4;d++){
                int zr = cur.r+dr[d];
                int zc = cur.c+dc[d];
                
                if(zr>=0&&zc>=0&&zr<map.length&&zc<map[0].length&&map[zr][zc]==1&&!visited[zr][zc]){
                    visited[zr][zc] = true;
                    que.offer(new Pos(zr,zc,cur.dist+1));
                }
            }
            
            
            
        }
        
        return result;
        
        
        
        
        
    }
}
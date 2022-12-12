import java.util.*;
class Solution {
    class Pos{
        int r,c;
        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public int[] solution(int h, int w, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        boolean[][] visited = new boolean[h][w];
        for(int i=0;i<picture.length;i++){
            for(int j=0;j<picture[0].length;j++){
                if(!visited[i][j]&&picture[i][j]!=0){
                    numberOfArea+=1;
                    int tmp = bfs(i,j,visited,picture);
                    if(maxSizeOfOneArea<tmp){
                        maxSizeOfOneArea = tmp;
                    }
                }
            }
        }
        int[] answer = {numberOfArea,maxSizeOfOneArea};
        return answer;
    }
    public int bfs(int startR, int startC,boolean[][] visited, int[][] picture){
        int curColor = picture[startR][startC];
        int count = 0;
        Queue<Pos> que = new ArrayDeque<>();
        visited[startR][startC] = true;
        que.offer(new Pos(startR,startC));
        int dr[] = {-1,1,0,0};
        int dc[] = {0,0,-1,1};
        while(!que.isEmpty()){
            Pos cur = que.poll();
            count+=1;
            for(int d=0;d<4;d++){
                int zr = cur.r+dr[d];
                int zc = cur.c+dc[d];
                if(zr>=0&&zr<visited.length&&zc>=0&&zc<visited[0].length&&!visited[zr][zc]){
                    if(picture[zr][zc]==curColor){
                        visited[zr][zc] = true;
                        que.offer(new Pos(zr,zc));
                    }
                }
            }            
        }
        return count;
    }
}
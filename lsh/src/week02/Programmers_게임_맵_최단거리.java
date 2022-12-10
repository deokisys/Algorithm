package week02;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : sh Lee
 * @date : 22. 12. 8.
 */
public class Programmers_게임_맵_최단거리 {

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int bfs(int[][] maps){
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        visited[0][0] = true; //시작노드 방문처리.

        Queue<Node> needVisited = new LinkedList<>();
        needVisited.add(new Node(0,0,1)); //시작노드 큐에 넣기

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            //목적지에 도달하면 누적하고 있던 칸의 수를 리턴
            if(currentNode.x == (maps.length - 1) && currentNode.y == (maps[0].length - 1)){
                return currentNode.count;
            }

            for(int i = 0; i < 4; i++){
                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                //맵을 벗어나지 않으면 다음 탐색노드로 추가.
                if(nextX >= 0 && nextX < maps.length && nextY >= 0 && nextY < maps[0].length && !visited[nextX][nextY] && maps[nextX][nextY] == 1){
                    visited[nextX][nextY] = true;
                    needVisited.add(new Node(nextX,nextY, currentNode.count + 1));
                }
            }
        }
        //도착 불가능 하면 -1 리턴
        return -1;
    }

    public int solution(int[][] maps) {

        return bfs(maps);
    }

    static class Node{
        int x,y, count;

        Node(int x,int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}

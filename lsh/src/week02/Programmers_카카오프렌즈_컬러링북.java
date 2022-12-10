package week02;

/**
 * @author : sh Lee
 * @date : 22. 12. 10.
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * 아이디어
 * 영역구하기 문제이다.
 * 1. 방문배열을 만들고, 한칸씩 2중 for문으로 이동하면서, 방문하지 않았다면 bfs 탐색을 해서 영역의 크기를 구한다.
 * 2. bfs 탐색시, 방문한 노드는 전부 방문처리 해서 다음 탐색시에 방문하지 않도록 한다.
 * 소요시간 : 20분
 */
public class Programmers_카카오프렌즈_컬러링북 {

    //4방향 탐색을 위한 dx,dy 정의
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    //영역을 구할 bfs 메서드, 영역의 크기를 리턴한다.
    static int bfs(boolean[][] visited, int[][] picture, int m, int n,Node startNode){

        //방문할 노드를 가지고 있을 큐 정의
        Queue<Node> needVisited = new LinkedList<>();
        needVisited.add(startNode); //시작노드 추가

        visited[startNode.x][startNode.y] = true;//시작노드 방문 처리

        int area = 0; //영역의 크기 구하기.

        //탐색
        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            area++;

            for(int i = 0; i < 4; i++){

                int nextX = currentNode.x + dx[i];
                int nextY = currentNode.y + dy[i];

                if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n
                        && !visited[nextX][nextY]
                        && picture[startNode.x][startNode.y] == picture[nextX][nextY]){

                    visited[nextX][nextY] = true;
                    needVisited.add(new Node(nextX, nextY));
                }
            }
        }

        return area;
    }

    public int[] solution(int m, int n, int[][] picture) {

        int[] answer = new int[2];

        int maxArea = 0;//최대영역크기
        int areaNum = 0;//영역의 수.

        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j] && picture[i][j] != 0){
                    maxArea = Math.max(maxArea,bfs(visited, picture, m, n, new Node(i,j)));
                    areaNum++;
                }
            }
        }

        answer[0] = areaNum;
        answer[1] = maxArea;

        return answer;
    }

    static class Node{

        int x, y;

        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

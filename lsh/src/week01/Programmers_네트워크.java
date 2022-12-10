package week01;

/**
 * @author : sh Lee
 * @date : 22. 12. 5.
 */

/**
 * 아이디어
 * 1. 노드의 번호인 0~(n-1)까지 반복문을 돌면서 방문하지 않았다면 dfs탐색을 한다.
 * 2. dfs 탐색을 하면 하나의 네트워크에 속한 노드들이 탐색되고, 이 노드들은 전부 방문처리를 한다.
 * 3. 탐색이 끝나면 네트워크수+1을 해서 누적시키고 이를 반복해서 모든 네트워크를 구한다.
 * 4. bfs를 이용해서 풀어도 되며 영역구하기 문제와 동일하다
 */
public class Programmers_네트워크 {

    static void dfs(int n, int[][] computers, boolean[] visited, int currentNode){

        if(visited[currentNode]) return;

        visited[currentNode] = true;

        for(int nextNode = 0; nextNode < n ; nextNode++){
            if(currentNode != nextNode && computers[currentNode][nextNode] != 0){
                dfs(n, computers, visited, nextNode);
            }
        }
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;

        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                dfs(n,computers,visited,i);
                answer++;
            }
        }


        return answer;
    }
}

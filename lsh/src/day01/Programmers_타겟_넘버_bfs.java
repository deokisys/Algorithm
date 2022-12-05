package day01;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : sh Lee
 * @date : 22. 12. 05.
 * 아이디어
 * 1. 각 숫자를 노드로 생각하고, 다음 순서의 숫자를 다음 노드로 생각한다.
 * 2. 각 노드 별로 빼거나 더할수 있기 떄문에 인접노드로 가는 경우는 -1,1을 곱한 경우 두가지가 된다.
 * 3. 즉, 한 노드에서 다음 노드로 이동하는데 방법이 두가지인 이진트리로 생각할 수 있다.
 * 4. bfs로 탐색하면서 타켓넘버에 도달하면, count를 증가시켜서 경우의 수를 세어 준다.
 */
public class Programmers_타겟_넘버_bfs {

    static int bfs(int[] numbers, int target){
        int count = 0;

        Queue<Node> needVisited = new LinkedList<>();
        needVisited.add(new Node(numbers[0],numbers[0],0));
        needVisited.add(new Node(numbers[0] * (-1),numbers[0] * (-1),0));

        while(!needVisited.isEmpty()){

            Node currentNode = needVisited.poll();

            //모든 숫자 연산을 다했으면 누적된 결과값 확인.
            if(currentNode.depth == (numbers.length - 1) && currentNode.total == target){
                count++;
            }

            if(currentNode.depth + 1 < numbers.length){
                int nextNode = numbers[currentNode.depth + 1];

                needVisited.add(new Node(nextNode, currentNode.total+ nextNode, currentNode.depth+1));
                needVisited.add(new Node(nextNode * (-1), currentNode.total+ nextNode * (-1), currentNode.depth+1));
            }
        }


        return count;
    }


    public int solution(int[] numbers, int target) {

        return bfs(numbers, target);
    }

    static class Node{
        int node, total,depth; //노드값, 누적된 값, 깊이

        Node(int node, int total, int depth){
            this.node = node;
            this.total = total;
            this.depth = depth;

        }
    }
}

package week02;

import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author : sh Lee
 * @date : 22. 12. 11.
 */

/**
 * 아이디어1 - 실패
 * 1. cpu 스케줄링 기법중 하나인 선입선출 스케줄링
 * 2. pq를 이용해서 처리시간이 빠른 순서로 poll하도록 했고, 처리시간이 같다면 코어번호가 빠른것을 먼저 poll하도록 했다.
 * 3. pq와 반복문을 이용한 방식은 효율성을 통과할 수 없었다.
 *
 * 아이디어2 - 성공(참조함)
 * 효율성을 통과하기 위해서는 이분탐색을 결정문제로 바꾼 "파라메트릭 서치"를 이용해야한다.
 * 이분탐색에서 상한선 하한선을 구하는 것과 동일하다
 * 백준 랜선자르기 나무자르기가 파라메트릭 서치에 해당한다.
 * 특정시간까지 처리한 수를 구하고, 그 수가 n보다 작다면 시간을 업데이트 , n보다 크거나 같다면 종료하도록 하면 된다.
 */
public class Programmers_선입선출_스케줄링 {
    /*효율성 통과 못하는 코드 - pq와 반복문이용
    public int solution(int n, int[] cores) {
        if(n <= cores.length){
            return n;
        }

        //처리시간이 빠른 순서대로 뽑을 수 있도록 pq 사용
        PriorityQueue<Node> pq = new PriorityQueue<>();

        //작업이 가능한 코어를 넣어두는 큐, 작업이 가능한 코어 번호를 넣어둠.
        PriorityQueue<Integer> ableToTask = new PriorityQueue<>();

        //초기에 모든 코어를 넣어둠
        for(int i = 1; i <= cores.length; i++){
            ableToTask.add(i);

            pq.add(new Node(i, cores[i-1]));
        }

        n-= cores.length;

        int answer = 0;
        //반복문을 돌면서 코어 할당과, 해제를 반복,
        while(n > 0){

            Node currentNode = pq.poll();

            n--;
            if(n == 0){
                answer = currentNode.coreNum;
                break;
            }

            pq.add(new Node(currentNode.coreNum, currentNode.processTime + cores[currentNode.coreNum - 1]));

        }

        return answer;
    }
    */
    /*파라메트릭 서치를 활용한 효율성 통과 코드
    * 처리시간을 이진탐색을 이용해서 뽑음.*/
    //특정시간을 인자로 주고, 해당 시간까지 처리할 수 있는 작업량을 계산해서 리턴.
    static long processTaskCount(long processTime, int[] cores){

        //시간이 0이라면 코어수만큼 처리 가능, 시간이 0이라는 뜻은 시작시점이라는 뜻.
        if(processTime == 0){
            return cores.length;
        }

        long returnCount = cores.length;//처리량을 누적해서 리턴, 시작은 코어수만큼(0시간일때의 처리량)

        //모든 코어를 돌면서 해당시간에 몇개가 처리되는지 확인.
        for(int i = 0; i < cores.length; i++){
            returnCount += (processTime/cores[i]); //해당코어(cores[i])가 주어진 시간까지 몇개의 작업을 처리하는지.
        }

        return returnCount;

    }

    public int solution(int n, int[] cores) {
        int answer = 0;

        long maxTime = 250000000; // 코어수2, 처리시간 각각10000, 일의 개수 50000일때가 최악의 경우. 10000 * 25000 -
        long minTime = 0;

        long processCount = 0;//currentTime까지 처리한 개수

        long currentTime = 0;

        //이진탐색 시작
        while(minTime <= maxTime){

            long mid = (minTime + maxTime) / 2;

            long count = processTaskCount(mid, cores); //해당 시간까지의 처리량 구하기

            //구한 처리량이 n보다 크거나 같다면, 시간을 줄여봐야 됨.
            if(count >= n){
                maxTime = mid - 1;
                currentTime = mid;
                processCount = count;
            }
            //n이 더 크다면 시간을 늘리면서 처리량이 n에 가깝도록 구해봄
            else{
                minTime = mid + 1;
            }
        }

//        processCount -= n; //n개를 뺀 나머지 작업을 저장.

        //뒷번호부터 확인 - 코어는 번호가 빠른것 부터 할당하기 때문에 뒷번호부터 순차적으로 할당하면서 찾음.
        for(int i = cores.length - 1; i >= 0; i--){
            if(currentTime % cores[i] == 0){
                //n번째에 도달하면 리턴.
                if(processCount == n){
                    answer = i+1;
                    break;
                }
                processCount--;
            }
        }

        return answer;
    }



    static class Node implements Comparable<Node>{
        int coreNum, processTime; //코어번호, 처리시간

        Node(int coreNum, int processTime){
            this.coreNum = coreNum;
            this.processTime = processTime;
        }


        //처리시간 -> 코어번호 순으로 오름차순 정렬.
        @Override
        public int compareTo(Node o) {

            if(this.processTime == o.processTime){
                return this.coreNum - o.coreNum;
            }

            return this.processTime - o.processTime;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "coreNum=" + coreNum +
                    ", processTime=" + processTime +
                    '}';
        }
    }

    public static void main(String[] args) {

        Programmers_선입선출_스케줄링 s = new Programmers_선입선출_스케줄링();

        int n = 6;
        int[] cores = {1,2,3};

        //2
        System.out.println(s.solution(n,cores));
    }
}

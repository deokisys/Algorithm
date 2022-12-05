package day01;

/**
 * @author : sh Lee
 * @date : 22. 12. 5.
 */
public class Programmers_타겟_넘버_dfs {

    static int result;

    static void dfs(int[] numbers, int target, int total, int idx){

        if(idx == numbers.length){
            if(total == target) result++;

            return;
        }

        //*1을 고르는 경우
        dfs(numbers,target,total + numbers[idx], idx+1);

        //*(-1)을 고르는 경우
        dfs(numbers,target,total + numbers[idx] * (-1), idx+1);
    }


    public int solution(int[] numbers, int target) {
        result = 0;
        dfs(numbers,target,0,0);
        return result;
    }
}

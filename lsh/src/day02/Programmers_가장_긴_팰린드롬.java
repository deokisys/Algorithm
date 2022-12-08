package day02;

/**
 * @author : sh Lee
 * @date : 22. 12. 8.
 * 소요시간: 20분
 */
/*
최대 문자 길이부터 substring으로 자르고, stringbuilder변환후, reverse()를 사용하는 방식은 너무 시간이 오래걸림.
단어 길이가 홀수 일때랑, 짝수일때랑 나눠서 기준점을 잡고 왼쪽은 인덱스--, 오른쪽은 인덱스++하는 식으로 팰린드롬을 구할 수 있음.
최대 단어수가 2500 => N^2이면 약 620만이므로 충분히 시간초과 없이 끝낼 수 있음.
 */
public class Programmers_가장_긴_팰린드롬 {

    //왼쪽 오른쪽 시작인덱스를 받아서 반복해서 비교해보는 메서드
    static int felindromeCheck(String s, int leftIdx, int rightIdx){

        int lengthCount  = 0; //문자열 길이 세기.

        //인덱스가 문자열을 벗어나지 않고, 두 문자열이 같다면 계속 진행.
        while(leftIdx >= 0 && rightIdx < s.length() && s.charAt(leftIdx) == s.charAt(rightIdx)){

            lengthCount+=2; //문자 두개씩 비교하기 때문에 +2
            leftIdx--;
            rightIdx++;
        }

        return lengthCount;
    }

    public int solution(String s) {
        int answer = Integer.MIN_VALUE;

        //왼쪽 시작단어 - 오른쪽에서부터 시작하는 단어도 있기 때문에 문자열 길이-1로 함.(추가 : 이렇게 되면 문자열 길이가 1인 것은 처리가 안됨.)
        for(int i = 0; i < s.length(); i++){
            answer = Math.max(answer, felindromeCheck(s, i,i+1));//길이가 짝수인 팰린드롬 구하기.
            answer = Math.max(answer, felindromeCheck(s, i-1,i+1)+1);//길이가 홀수인 팰린드롬 구하기 - 홀수이면 가운데 문자열 +1;
        }

        return answer;
    }

}

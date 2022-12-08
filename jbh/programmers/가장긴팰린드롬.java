package programmers;

public class 가장긴팰린드롬 {
	// 앞뒤를 뒤집어도 똑같은 문자열 찾기 -> 가장 긴
	// 주어진 문자열 전체를 비교하고 아니면 -1씩 해서 문자열 다시 비교하기
	
	public static void main(String[] args) {
		String s = "abacde";
		System.out.println(solution(s));
	}
	
	public static int solution(String s) {
        int answer = 0;
        
        // 가장 긴 길이부터 시작해서 찾기
        for(int i = s.length(); i > 0; i--) {
        	for(int j = 0; j <= s.length()-i; j++) {
        		boolean flag = true;
        		// 앞이랑 뒤를 비교하여 같은 문자인지 확인
        		for(int k = 0; k < i/2; k++) {
        			// 다르면 끝내기
        			if(s.charAt(k+j) != s.charAt(i-k+j-1)) {
        				flag = false;
        				break;
        			}
        		}
        		
        		if(flag) return i;
        	}
        }
        
        return answer;
    }
}

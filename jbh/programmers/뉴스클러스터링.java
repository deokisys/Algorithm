package programmers;

import java.util.ArrayList;

public class 뉴스클러스터링 {
	static ArrayList<String> list1 = new ArrayList<>();
	static ArrayList<String> list2 = new ArrayList<>();
	
	public static void main(String[] args) {
		String str1 = "E=M*C^2";
		String str2 = "e=m*c^2";
		
		System.out.println(solution(str1, str2));
	}
	
	public static int solution(String str1, String str2) {
        int answer = 0;
        // 1. 대소문자 차이 무시하니까 모두 소문자로 만들어주기
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        // 2. 문자열을 길이 2로 모두 자르기
        cutWord(str1, 1);
        cutWord(str2, 2);
        
        // 4. 교집합 구하기
        int common = 0, total = 0;
 		for(String s : list1) {
 			if(list2.contains(s)) {
 				common++;
 				list2.remove(s); 				
 			}
 		}
 		// 5. 합집합 구하기
 		total = list1.size() + list2.size();
        
 		// 6. 교집합 개수 / 합집합 개수 * 65536 => 소수점 아래 버리고 정수 부분만 출력
        // 만약 str1, str2가 모두 공집합이면 1로 정의하므로 바로 리턴
        if(total == 0) return 65536;
        answer =  (int) ((double) common / total * 65536);
        return answer;
    }
	
	// 크기 2로 자르기
	public static void cutWord(String str, int who) {
		for(int i = 0; i < str.length()-1; i++) {
			String s = str.substring(i, i+2);
			// 3. 숫자나 특수문자가 있으면 버리기
			int count = 0;
			for(char ch = 'a'; ch <= 'z'; ch++) {
				if(s.charAt(0) == ch) count++;
				if(s.charAt(1) == ch) count++;
				if(count == 2) break;
			}
			// 모두 영문으로 되어있으면
			if(count == 2) {
				if(who == 1) list1.add(s);
				else list2.add(s);
			}
		}
	}

}

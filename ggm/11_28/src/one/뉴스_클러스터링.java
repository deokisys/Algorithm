package one;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/*
 * 자카드 유사도
 * 두 집합 A, B 사이의 자카드 유사도 J(A, B)는 두 집합의 교집합 크기를 두 집합의 합집합 크기로 나눈 값으로 정의
 * A = {1,2,3}, B = {2,3,4} A^B = {2,3} AUB = {1,2,3,4} 유사도= 2/4 = 0.5
 * 두개의 집합 모두 공집합일 경우 유사도는 1
 * 
 * 
 */

/*
 * 두개의 문자열에 대해 맵으로 만들고 각 키값에 대해 몇번 나오는지 value 값으로 넣어준다.
 * 교집합을 구할 때 하나의 맵에 대하여 key가 있는 지 확인하여 있으면 두개의 value 값을 비교하여 작은 값을 교집합의 total에 누적합 시켜줌 
 * 합집합을 구할때 key 값이 없으면 합집합의 total에 누적합 있다면 value중에 큰것을 total에 누적합 시켜준다.
 * 
 */
public class 뉴스_클러스터링 {

	public static void main(String[] args) {
		
		String str1 = "E=M*C^2";
		String str2 = "E=M*C^2";
		System.out.println(solution(str1, str2));

	}
	
	public static int solution(String str1, String str2) {
        int answer = 0;
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        
        
        String pattern = "^[a-z]*$";
        for(int i = 0; i < str1.length()-1; i++) {
        	String tmp = str1.substring(i, i+2);
        	if(Pattern.matches(pattern, tmp)) {
//        		System.out.println(tmp);
        		if(map1.get(tmp) == null) {
        			map1.put(tmp, 1);
        		}
        		else {
        			map1.put(tmp, map1.get(tmp) + 1);
        		}
        	}
        	
        }
        
        
        for(int i = 0; i < str2.length()-1; i++) {
        	String tmp = str2.substring(i, i+2);
        	if(Pattern.matches(pattern, tmp)) {
//        		System.out.println(tmp);
        		if(map2.get(tmp) == null) {
        			map2.put(tmp, 1);
        		}
        		else {
        			map2.put(tmp, map2.get(tmp) + 1);
        		}
        	}
        	
        }
        
        int intersectionTotal = 0;
        int unionTotal = 0;
        
        if(map1.keySet().size() == 0 && map2.keySet().size() == 0) {
        	return answer = 1;
        }
        
        
        for(String map1Key : map1.keySet()) {
        	if(map2.get(map1Key) != null) {
        		intersectionTotal += Math.min(map2.get(map1Key), map1.get(map1Key));
        		unionTotal += Math.max(map2.get(map1Key), map1.get(map1Key));
        	}
        	else {
        		unionTotal += map1.get(map1Key);
        	}
        }
        
       
        
        for(String map2Key : map2.keySet()) {
        	if(map1.get(map2Key) == null) {
        		unionTotal += map2.get(map2Key);
        	}
        }
        
        
//        System.out.println(intersectionTotal);
//        System.out.println(unionTotal);
        
        double jacard = (double)intersectionTotal / (double)unionTotal;
        
        answer = (int)(jacard * 65536);
        
        return answer;
    }

}

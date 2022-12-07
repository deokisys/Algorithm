package one;

public class 가장_긴_펠린드롬 {

	public static void main(String[] args) {
		
		String s = "abacde";
		
		System.out.println(solution(s));

	}
	
	public static int solution(String s)
    {
        int answer = 0;
        
        
        /*
         * 가장 긴 펠린드롬 부터 가장 짧은 펠린드롬을 만들어 비교 해보기
         * 만일 나오면 그 즉시 종료 
         */
        
        // 가장 긴 길이 저장
        // 
        // 1씩 감소하여 
        int n = s.length();
        
        for(int k = n; k>=2; k--) {
        	for(int i = 0; i < s.length() - k; i++) {
        		boolean flg = true;
//        		System.out.println(flg);
        		
        		for(int j = 0; j < k/2; j++) {
        			if(s.charAt(i + j) != s.charAt(i + k - j -1)) {
        				flg = false;
        				break;
        			}
        		}
        		
        		if(flg) return k;
        		
        		
        	}
        }
        
        
        

        return 1;
    }
	
	

}

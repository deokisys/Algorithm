class Solution {
    static int[] select;
    static int answer;
    
    public int solution(int[] numbers, int target) {
        select = new int[numbers.length];
        per(0, numbers, target);
        return answer;
    }
    
    public static void per(int cnt, int[] numbers, int target) {
		if(cnt == numbers.length) {
			int num = 0;
			for(int i = 0; i < select.length; i++) {
				if(select[i] == 0) {
					num += numbers[i];
				} else {
					num -= numbers[i];
				}
			}
			
			if(num == target) answer++;
			return;
		}
		
		// 0은 +, 1은 -
		for(int i = 0; i < 2; i++) {
			select[cnt] = i;
			per(cnt+1, numbers, target);
		}
	}
}
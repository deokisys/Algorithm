package one;

public class target_number {

	// 
	static int cnt;

	public static void main(String[] args) {

		int[] numbers = { 4, 1, 2, 1 };
		int target = 4;

		System.out.println(solution(numbers, target));
	}

	public static int solution(int[] numbers, int target) {


		cnt = 0;
		
		dfs(numbers, target, 0, 0);
		

		int answer = cnt;
		return answer;
	}

	private static void dfs(int[] numbers, int target, int idx, int total) {
		
		

		
		
		if(idx == numbers.length) {
			if (total == target) {
//				System.out.println(total + " " + idx);
				cnt++;
			}
			return;
		}
		
		
		dfs(numbers, target, idx + 1, total - numbers[idx]);
		dfs(numbers, target, idx + 1, total + numbers[idx]);
	
	}

}

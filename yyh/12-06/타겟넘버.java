package 타겟넘버;

public class 타겟넘버 {

	private static int answer;

	public int solution(int[] numbers, int target) {
		answer = 0;

		dfs(0, 0, numbers, target);

		return answer;
	}

	/**
	 * @methodName : dfs
	 * @description : 타겟 넘버를 만들기 위한 완전탐색 dfs 메소드
	 * @param idx
	 * @param sum
	 * @param numbers
	 * @param target
	 *
	 * @author : Younghun Yu
	 * @date : 2022.12.05
	 */
	private void dfs(int idx, int sum, int[] numbers, int target) {
		if (idx == numbers.length) {
			if (sum == target) {
				answer++;
			}
			return;
		}

		dfs(idx + 1, sum + numbers[idx], numbers, target);
		dfs(idx + 1, sum - numbers[idx], numbers, target);
	}
}

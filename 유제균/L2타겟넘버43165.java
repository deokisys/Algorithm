class Solution {
    static int answer = 0;

    public void dfs(int current, int index, int[] numbers, int target) {
        if (index == numbers.length) {
            if (current == target)
                answer++;
            return;
        }

        dfs(current + numbers[index], index + 1, numbers, target);
        dfs(current - numbers[index], index + 1, numbers, target);

    }

    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        return answer;
    }
}
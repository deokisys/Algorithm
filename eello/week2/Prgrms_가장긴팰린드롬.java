class Solution {
    
    public int solution(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans = Math.max(ans, palindrome(s, i - 1, i + 1));
            ans = Math.max(ans, palindrome(s, i, i + 1));
        }
        
        return ans;
    }

    private int palindrome(String s, int left, int right) {
        while (0 <= left && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
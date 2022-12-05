import java.util.ArrayList;
import java.util.List;

class Solution {
    
    public int solution(String str1, String str2) {
        double j = J(str1, str2);
        return (int) (j * 65536);
    }

    // 자카드 유사도
    private double J(String str1, String str2) {
        // 각 문자열을 2글자씩 묶어서 집합을 만듦
        List<String> set1 = makeSet(str1);
        List<String> set2 = makeSet(str2);

        // set1과 set2가 모두 공집합이면 리턴 1
        if (set1.size() == 0 && set2.size() == 0) {
            return 1;
        }

        double hap = set1.size() + set2.size();
        double gyo = 0;

        for (String elem : set1) {
            // set1과 set2의 공통요소이면
            if (set2.contains(elem)) {
                gyo++; // 교집합 개수 증가
                set2.remove(elem); // 중복되는 요소 제거
            }
        }

        // 합집합의 크기 = set1의 크기 + set2의 크기 - 교집합의 크기
        return gyo / (hap - gyo);
    }

    private List<String> makeSet(String str) {
        // 입력 문자열 전체를 소문자로 변환
        String lowerCase = str.toLowerCase();

        List<String> set = new ArrayList<>();
        for (int i = 0; i < lowerCase.length() - 1; i++) {
            // 현재 글자와 다음 글자가 a~z이면
            if (isAlphabet(lowerCase.charAt(i)) && isAlphabet(lowerCase.charAt(i + 1))) {
                // 집합에 문자열 추가
                set.add("" + lowerCase.charAt(i) + lowerCase.charAt(i + 1));
            }
        }

        return set;
    }

    private boolean isAlphabet(char ch) {
        // 입력 char이 소문자인 경우 true
        return 'a' <= ch && ch <= 'z';
    }
}
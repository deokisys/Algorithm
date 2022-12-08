package programmers;

public class 추석트래픽 {

	public static void main(String[] args) {
		String[] lines = {
				"2016-09-15 20:59:57.421 0.351s",
				"2016-09-15 20:59:58.233 1.181s",
				"2016-09-15 20:59:58.299 0.8s",
				"2016-09-15 20:59:58.688 1.041s",
				"2016-09-15 20:59:59.591 1.412s",
				"2016-09-15 21:00:00.464 1.466s",
				"2016-09-15 21:00:00.741 1.581s",
				"2016-09-15 21:00:00.748 2.31s",
				"2016-09-15 21:00:00.966 0.381s",
				"2016-09-15 21:00:02.066 2.62s"
				};
		System.out.println(solution(lines));
	}

	public static int solution(String[] lines) {
        int answer = 0;
        
        for(int i = 0; i < lines.length; i++) {
        	int count = 1;
        	
        	// 시간 -> 초
    		int hour = Integer.parseInt(lines[i].substring(11, 13)) * 3600;
    		// 분 -> 초
    		int min = Integer.parseInt(lines[i].substring(14, 16)) * 60;
    		// 초
    		double sec = Double.parseDouble(lines[i].substring(17, 23));
    		// 끝 시간 -> 시간 + 분 + 초 -> 소수점 제거하기 위해 1000 곱함
    		int time = (int) ((hour + min + sec) * 1000);
    		
        	for(int j = i+1; j < lines.length; j++) {
        		// 시간 -> 초
        		int newHour = Integer.parseInt(lines[j].substring(11, 13)) * 3600;
        		// 분 -> 초
        		int newMin = Integer.parseInt(lines[j].substring(14, 16)) * 60;
        		// 초
        		double newSec = Double.parseDouble(lines[j].substring(17, 23));
        		// 끝 시간
        		int newTime = (int) ((newHour + newMin + newSec) * 1000);
        		// 처리시간
        		int T = (int) (Double.parseDouble(lines[j].substring(24, lines[j].length()-1)) * 1000);
        		// 시작 시간
        		int start = newTime - T + 1;
        		
        		// 끝나는 지점으로부터 오름차순으로 정렬되어 있으므로 
        		// 현재 1초의 시간 a ~ a+999에서 다음 비교하려는 구간의 시작이 a+999보다 뒤에 있으면 X, 나머지는 다 포함
        		if((time+999) < start) continue;
        		count++;
        	}
        	
        	answer = Math.max(answer, count);
        }
        return answer;
    }
	
}

package one;

import java.util.Arrays;

/*
 * 장애 대비용 서버 증설 여부 결정하기 위해 작년 추석 기간인 9월 15일 로그 데이터를 분석
 * 초당 최대 처리량을 계산해봄 1초간 처리하는 요청의 최대 개수
 * 
 * 
 * 
 * 
 */
public class 추석_트래픽 {

	public static void main(String[] args) {
		
		
		/*
		 * 입력 형식: 응답완료 시간 S: 2016-09-15 hh:mm:ss.sss 형식
		 * 처리시간 T: x.xxxs 형식 
		 * 
		 * 2016-09-15 03:10:33.020 0.011s은 
		 * "2016년 9월 15일 오전 3시 10분 33.010초"부터 
		 * "2016년 9월 15일 오전 3시 10분 33.020초"까지 
		 * "0.011초" 동안 처리된 요청을 의미한다. (처리시간은 시작시간과 끝시간을 포함)
		 * 
		 * 서버 타임아웃 3초 적용
		 * 
		 * 
		 * 초당 최대 처리량 리턴
		 */
		String[] lines = {"2016-09-15 20:59:57.421 0.351s",
				"2016-09-15 20:59:58.233 1.181s",
				"2016-09-15 20:59:58.299 0.8s",
				"2016-09-15 20:59:58.688 1.041s",
				"2016-09-15 20:59:59.591 1.412s",
				"2016-09-15 21:00:00.464 1.466s",
				"2016-09-15 21:00:00.741 1.581s",
				"2016-09-15 21:00:00.748 2.31s",
				"2016-09-15 21:00:00.966 0.381s",
				"2016-09-15 21:00:02.066 2.62s"};
		
		System.out.println(solution(lines));

	}
	public static int solution(String[] lines) {
        int answer = 0;
        
        
        double[] processTimes = new double[lines.length];
        double[][] times = new double[lines.length][2];
        
        for(int i = 0; i < lines.length; i++) {
        	String[] line = lines[i].split(" ");
        	
        	String[] time = line[1].split(":");
        	
        	times[i][1] = Double.parseDouble(time[0]) * 3600 + Double.parseDouble(time[1]) * 60 + Double.parseDouble(time[2]);
//        	System.out.println(Arrays.toString(time));
//        	System.out.println(times[i][1]);
//        	System.out.println(line[2]);
        	
        	processTimes[i] = Double.parseDouble(line[2].replaceAll("s", ""));
//        	System.out.println(processTimes[i]);
        	
        	times[i][0] = Math.round((times[i][1] - processTimes[i] + 0.001) * 1000) / 1000.0;
        	
        	
        	
        }
        
        double minTime = times[0][0];
        double maxTime = times[lines.length-1][1];
        
        
        
        int cnt = 0;
        
//        while(minTime <= maxTime) {
//        	double endTime = minTime + 1.0;
//        	cnt = 0;
//        	
//        	
//        	for(int i = 0; i < lines.length; i++) {
//        		if(minTime <= times[i][0] && endTime >= times[i][0]) {
//        			cnt++;
//        		}
//        		else if(minTime <= times[i][1] && endTime >= times[i][1]) {
//        			cnt++;
//        		}
//        		else if(minTime >= times[i][0] && endTime <= times[i][1]) {
//        			cnt++;
//        		}
//        	}
//        	
//        	answer = Math.max(answer, cnt);
////        	System.out.println(minTime + " " + endTime);
//        	minTime += 0.001;
//        }
        
        return answer;
    }

}

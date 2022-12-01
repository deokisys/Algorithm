import java.util.*;
import java.io.*;

class Solution {
    class Time{
        int start,end;
        Time(int s,int e){
            this.start = s;
            this.end = e;
        }
    }
    public int solution(String[] lines) {
        int answer = 1;
        //1초간 처리하는 요청의 최대 개수
        
        //소수점셋째자리까지 기록
        //시작-끝 시간을 기록
        //끝을 기준으로 1초후를 확인
        Time[] logs = new Time[lines.length];
        int idx = 0;
        for(String line:lines){
            String[] times = line.split(" ");
            //1과2만 사용
            
            int sec = time2sec(times[1]);
            String termStr = times[2].replace("s","");
            double tsec = Double.parseDouble(termStr);
            tsec*=1000;
            int termSec=(int)tsec;
            
            logs[idx++] = new Time(sec-termSec+1,sec);
        }
        
        //2중 반복
        
        for(int i=0;i<logs.length;i++){
            //i의 끝을 기준
            int cnt = 1;
            for(int j=0;j<logs.length;j++){
                if(i==j) continue;
                
                if(logs[i].end<=logs[j].start&&logs[j].start<=logs[i].end+999){
                    cnt+=1;
                }else if(logs[i].end<=logs[j].end&&logs[j].end<=logs[i].end+999){
                    cnt+=1;
                }else if(logs[j].start<logs[i].end&&logs[i].end<logs[j].end){
                    cnt+=1;
                }
            }
            
            if(answer<cnt){
                answer = cnt;
            }
        }
        
        
        
        return answer;
    }
    public int time2sec(String time){
        String[] t = time.split(":");
        int sec = 0;
        
        sec+=Integer.parseInt(t[0])*60*60*1000;
        sec+=Integer.parseInt(t[1])*60*1000;
        
        double tsec = Double.parseDouble(t[2]);
        tsec*=1000;
        sec+=(int)tsec;
        
        return sec;
    }
}
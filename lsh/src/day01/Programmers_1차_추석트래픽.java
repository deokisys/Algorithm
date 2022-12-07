package day01;

/**
 * @author : sh Lee
 * @date : 22. 12. 5.
 */
public class Programmers_1차_추석트래픽 {

    // HH:mm:SS -> sec
    static int timeToSec(String time){

        String[] timeArray = time.split(":");

        int hour = Integer.parseInt(timeArray[0]) * 60* 60;
        int minute = Integer.parseInt(timeArray[1]) * 60;
        int second = Integer.parseInt(timeArray[2]);

        return hour + minute + second;
    }

    public int solution(String[] lines) {

        //전부 초로 바꿔서 계산.
        //소수점은 3자리까지이므로 *1000을 하고, 1초는 1000으로 생각해서 계산.

        TimeNode[] timeNodes = new TimeNode[lines.length];//각 시간을 시작, 끝 시간으로 분리해서 클래스에 넣고 배열에 저장.
        for(int i = 0; i < lines.length; i++){
            String line = lines[i];

            String[] tempLine = line.split(" ");//공백을 기준으로 나눔

            String[] tempTime = tempLine[1].split("[.]");//.을 기준으로 나눠서 시분초만 저장

            //종료시간 저장.
            int endTime = timeToSec(tempTime[0]) * 1000 + Integer.parseInt(tempTime[1]);

            //걸린시간 추출 => 종료시간과 이것을 이용해서 시작시간을 알아낼 수 있음.
            int processTime = (int)(Double.parseDouble(tempLine[2].substring(0,tempLine[2].length()-1)) * 1000.0);

            //시작시간 저장. => 시작시간과 끝시간을 포함하기 때문에 +1 해줘야 됨.
            int startTime = endTime - processTime + 1;

            //객체 배열에 저장.
            timeNodes[i] = new TimeNode(startTime,endTime);

        }

        int maxProcess = Integer.MIN_VALUE;//최대 처리량 저장.
        for(int i = 0; i < timeNodes.length; i++){
            TimeNode currentNode = timeNodes[i];
            int count = 1;

            for(int j = i+1; j < timeNodes.length; j++){

                TimeNode nextNode = timeNodes[j];

                //다음 비교 노드의 시작 시간이 현재 노드의 기준 시간 + 1
                if(nextNode.startSec < currentNode.endSec + 1000){
                    count++;
                }
            }

            maxProcess = Math.max(maxProcess,count); //최대 처리량 업데이트
        }

        return maxProcess;

    }

    static class TimeNode{
        int startSec, endSec;

        TimeNode(int startSec,int endSec){
            this.startSec = startSec;
            this.endSec = endSec;
        }
    }
}

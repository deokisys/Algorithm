import java.util.*;
//코어별로 작업 처리
//앞의 코어부터 처리
//처리해야될 작업 개수n, 각 코어 처리시간 cores
//마지막 작업을 처리하는 코어의 번호
class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
            
        answer = timeout(n,cores);
        
        return answer;
    }
    
    public int timeout(int n, int[] cores){
        int answer = 0;
        int[] check = new int[cores.length];
        for(int i=0;i<cores.length;i++){
            check[i] = cores[i];
        }
        
        while(n>0){
            for(int c=0;c<check.length;c++){
                if(check[c]==cores[c]){
                    n-=1;
                }
                if(n==0){
                    answer = c+1;
                    break;
                }
                //System.out.println(Arrays.toString(check));
                check[c]-=1;
                if(check[c]==0){
                    check[c] = cores[c];
                }
            }
        }
        return answer;
    }
}
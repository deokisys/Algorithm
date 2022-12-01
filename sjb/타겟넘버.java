import java.lang.*;
class Solution{
    static int cnt;
    public int solution(int[] numbers, int target) {
        int answer = 0;
        cal(numbers,target,0,0);
        answer = cnt;
        return answer;
    }
    public void cal(int[] numbers,int target, int sum, int idx){
        if(numbers.length==idx){
            if(sum==target){
                cnt+=1;
            }
            return;
        }
        
        int n = numbers[idx];
        
        cal(numbers,target,sum-n,idx+1);
        cal(numbers,target,sum+n,idx+1);
    }
}
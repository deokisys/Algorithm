class Solution
{
    public int solution(String s)
    {
        int answer = 1;
        
        char[] charArr = s.toCharArray();
        
        for(int i=0;i<charArr.length;i++){
            int left = i-1;
            int right = i+1;
            while(left>=0&&right<charArr.length){
                if(charArr[left]==charArr[right]){
                    answer = Math.max(right-left+1,answer);
                    left-=1;
                    right+=1;
                }else{
                    break;
                }
            }
        }
        
        for(int i=0;i<charArr.length-1;i++){
            if(charArr[i]==charArr[i+1]){
                answer = Math.max(2,answer);
                int left = i-1;
                int right = i+2;
                while(left>=0&&right<charArr.length){
                    if(charArr[left]==charArr[right]){
                        answer = Math.max(right-left+1,answer);
                        left-=1;
                        right+=1;
                    }else{
                        break;
                    }
                }
                
            }
        }
        
        return answer;
    }
}
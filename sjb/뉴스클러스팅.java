import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        //유사도는 교집합/합집합
        //다중집합은 최소갯수/최대갯수
        
        //영문자로 된 글자 쌍만 유효, 공백, 숫자 특문은 버린다.
        //대소문자 무시
        
        ArrayList<String> strArr1 = toArray(str1);
        ArrayList<String> strArr2 = toArray(str2);
        
        HashMap<String,Integer> map1 = toMap(strArr1);
        HashMap<String,Integer> map2 = toMap(strArr2);
        
        HashMap<String,Integer> totalMap = new HashMap<>();
        int size = 0;
        int interSize = 0;
        
        for(String str:map1.keySet()){
            size+=map1.get(str);
            totalMap.put(str,map1.get(str));
        }
        
        for(String str:map2.keySet()){
            if(totalMap.containsKey(str)){
                //둘중 큰값이 size, 작은값은 inter로
                if(totalMap.get(str)>map2.get(str)){
                    //이미 큰값인경우
                    interSize+=map2.get(str);
                }else{
                    interSize+=totalMap.get(str);
                    size+=(map2.get(str)-totalMap.get(str));
                }
                
            }else{
                size+=map2.get(str);
            }
        }
        
        double res = 0.0;
        if(size==0){
            res=1.0;
        }else{
            res = (double)interSize/size;
        }
        
        answer = (int)(res*65536);
        
        
        
        
        return answer;
    }
    public HashMap<String,Integer> toMap(ArrayList<String> list){
        HashMap<String,Integer> map = new HashMap<>();
        for(String str:list){
            if(!map.containsKey(str)){
                map.put(str,0);
            }
            map.put(str,map.get(str)+1);
        }
        
        return map;
    }
    public ArrayList<String> toArray(String str){
        ArrayList<String> strArr = new ArrayList<>();
        
        String tmp = "";
        for(int i=0;i<str.length();i++){
            char ch = Character.toLowerCase(str.charAt(i));
            if(ch>='a'&&ch<='z'){
                tmp+=ch;
                if(tmp.length()==2){
                    strArr.add(tmp);
                    tmp = ""+ch;
                }
            }else{
                tmp = "";
            }
        }
        
        
        return strArr;
    }
    
}
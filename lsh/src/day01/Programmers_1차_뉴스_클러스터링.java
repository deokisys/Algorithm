package day01;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author : sh Lee
 * @date : 22. 12. 8.
 */
public class Programmers_1차_뉴스_클러스터링 {

    //문자열 다중 집합 만들기 - 중복을 허용하기 때문에 개수를 체크해서 구분하기 위해 Map 사용
    static Map<String,Integer> makeStrSet(String str){
        Map<String,Integer> returnMap = new HashMap<>();

        str = str.toUpperCase();//대소문자 구분 안함

        for(int i = 0; i < str.length()-1; i++){

            char firstChar = str.charAt(i);
            char secondChar = str.charAt(i+1);
            //대문자일때만 다중집합의 원소가 될 수 있음.
            if(firstChar >= 'A' && firstChar <= 'Z' && secondChar >= 'A' && secondChar <= 'Z'){

                String temp = "" + firstChar + secondChar;

                //기존에 나왔던 단어면 개수만 증가.
                if(returnMap.containsKey(temp)){
                    returnMap.put(temp, returnMap.get(temp) + 1);
                }
                else{
                    returnMap.put(temp,1);
                }
            }
        }

        return returnMap;
    }

    public int solution(String str1, String str2) {
        int answer = -1;

        Map<String, Integer> firstMaps = makeStrSet(str1); //첫번째 문자의 다중집합.
        Map<String, Integer> secondMaps = makeStrSet(str2); //두번째 문자의 다중집합.

        int intersectionCount = 0; //교집합의 원소수
        int unionCount = 0; //합집합의 원소수

        Set<String> searchKeys = new HashSet<>();//교집합과 합집합을 구하기 위해, 두 다중집합을 중복없이 합침
        searchKeys.addAll(firstMaps.keySet());
        searchKeys.addAll(secondMaps.keySet());

        for(String searchKey : searchKeys){

            //두 다중집합에 모두 존재할 경우.
            if(firstMaps.containsKey(searchKey) && secondMaps.containsKey(searchKey)){
                //합집합 개수는 둘 중 큰쪽으로.
                //교집합은 둘중 작은 쪽으로.

                if(firstMaps.get(searchKey) >= secondMaps.get(searchKey)){
                    unionCount += firstMaps.get(searchKey);
                    intersectionCount += secondMaps.get(searchKey);
                }
                else{
                    unionCount += secondMaps.get(searchKey);
                    intersectionCount += firstMaps.get(searchKey);
                }
            }

            //첫번째 다중집합에만 존재할 경우.
            else if(firstMaps.containsKey(searchKey)){
                unionCount += firstMaps.get(searchKey);//합집합 개수에만 추가.
            }

            //두번째 다중집합에만 존재할 경우.
            else if(secondMaps.containsKey(searchKey)){
                unionCount += secondMaps.get(searchKey);//합집합 개수에만 추가.
            }
        }

        if(unionCount == 0 && intersectionCount == 0) answer = 65536;
        else answer = (int)(((double) intersectionCount / (double) unionCount) * 65536.0);

        return answer;
    }
}

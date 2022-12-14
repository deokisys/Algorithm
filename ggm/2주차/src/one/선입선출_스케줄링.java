package one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 선입선출_스케줄링 {

	public static void main(String[] args) {
		
		// 처리해야할 일의 갯수
		int n = 2;
		
		// 코어당 작업 시간
		int[] cores = {1,2,3};
		
		System.out.println(solution(n, cores));
	}
	
	public static int solution(int n, int[] cores) {
        int answer = 0;
        
        
       
        
        
        // while문을 돌려 n이 0이 되면 해당 코어의 idx를 answer에 넣어준다.
        // timer를 통해 각 코어들이 배수에 해당하는지 판단한다.
        
        
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < cores.length; i++) {
    		min = Math.min(min, cores[i]);
    	}
        
        
        
        int timer = min;
        n = n - cores.length;

        while(n != 0) {
//        	System.out.println("t: " + timer);
        	
        	for(int i = 0; i < cores.length; i++) {
        		if(timer % cores[i] == 0) {
        			
        			
//        			System.out.print(cores[i] + " ");
        			
        			if(n != 0) {
        				n--;
        			}
        		}
        		if(n == 0) {
        			answer = i;
        			break;
        		}
        	}
        	
//        	System.out.println();
        	timer++;
        	
        	
        }
        
        
        return answer+1;
    }

}

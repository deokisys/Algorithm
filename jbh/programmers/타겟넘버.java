package programmers;

import java.io.*;
import java.util.*;

public class 타겟넘버 {
	static int[] numbers = {4, 1, 2, 1};
	static int target = 4, answer;
	static int[] select = new int[4];
	
	public static void main(String[] args) {
		per(0);
		System.out.println(answer);
	}
	
	// 중복순열로 등호 뽑기
	public static void per(int cnt) {
		if(cnt == numbers.length) {
			int num = 0;
			for(int i = 0; i < select.length; i++) {
				if(select[i] == 0) {
					num += numbers[i];
				} else {
					num -= numbers[i];
				}
			}
			
			if(num == target) answer++;
			return;
		}
		
		// 0은 +, 1은 -
		for(int i = 0; i < 2; i++) {
			select[cnt] = i;
			per(cnt+1);
		}
	}

}

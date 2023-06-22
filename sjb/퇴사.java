import java.io.*;
import java.util.*;

public class 퇴사 {
    
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] T = new int[N];
        int[] P = new int[N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());//상담시간
            int p = Integer.parseInt(st.nextToken());//금액
            T[i] = t;
            P[i] = p;
        }


        int[] dp = new int[N];
        int max = 0;
        for(int i=0;i<N;i++){
            if(i+T[i]<=N){
                dp[i] = P[i];//초기화
                for(int j=i-1;j>=0;j--){
                    if(j+T[j]<=i){
                        dp[i] = Math.max(dp[i],dp[j]+P[i]);
                    }
                }
            }else{
                if(i==0){
                    dp[i] = 0;
                }else{
                    dp[i] = dp[i-1];
                }
            }
            max = Math.max(max,dp[i]);//최대값을 별도로 구해야한다@
            //그냥 누적하지 않고도 이미 중간에 최대를 만드는 경우가 있음
            //그런 최대인 경우를 찾기위해 별도로 max를 구해서 저장한다.
            // System.out.println(Arrays.toString(dp));
        }
        System.out.println(max);


    }
}

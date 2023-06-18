import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//8:27
//코딩완료 10:12 - 1시간 40분
//태케완료 11:23
//놓친거 확인 12:39
public class 메이즈러너 {

    static class Pos{
        int r,c;
        Pos(){}
        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    static int[][] map;
    static int N,M; //맵크기, 사람수
    static int count;//이동횟수
    static Pos out;//출구


    //참가자 정보 저장
    static ArrayList<Pos> list = new ArrayList<>();
    public static void main(String[] args) throws Exception{


        //1,1로 시작
        //빈칸
            //이동가능
        //벽
            //참가자가 이동할 수 없는 칸
            //1~9 내구도
            //회전시 내구도 1
            //0이면 빈칸
        //출구 - 도착시 탈출

        //1초
            //모든 참가자 한칸 이동
            //길이는 x-x + y-y
            //벽이 없는곳으로
            //현재칸보다 출구까지의 최단거리가 가까워야 함
            //움직이는거리
                //움직일수 있는 칸 2개면 상하 우선
                //못움직이면 움직X
            //한칸에 여러 모험가 존재가능
        //사각형 회전
            //출구에서 참가자까지의 작은 정사각형?
            //정사각형이 2개 이상이면 R좌표가 적은것 우선, C가 작은것 우선
            //90도 시계 회전, 회전된 벽은 1씩 깍임
        //K초동안 반복
            //그전에 탈출하면 게임끝
            //참가자들의 이동거리 합, 축구좌표를 출력



        //전체 시간 확인
            //100번 반복한다.
            //참가자 거리 및 이동시키기
                //10*10
            //작은 사각형 찾기
                //이동시킬때 저장한 위치로 판별
                //10
            //회전
                //10*10
        //10000?


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r-1][c-1] -=1;//아니 좌표가 겹치자나 미친
        }

        //출구
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        map[r-1][c-1] = -99;//확인용
        out = new Pos(r-1,c-1);



        //시작
        for(int k=0;k<K;k++){
            //인원확인
            if(M==0) break;
            //이동
            // print();
            move();
            // System.out.println("이동");
            // print();
            if(list.size()==0) break;
            Pos lt = new Pos(N,N); // 왼쪽위
            Pos rb = new Pos(0,0);// 오른쪽 아래
            //사각형 찾기
            findSquare(lt,rb);

            //회전진행
            turn(lt.r,lt.c,rb.r,rb.c);

            // System.out.println("최종");
            // print();
        }

        System.out.println(count);
        System.out.println((out.r+1)+" "+(out.c+1));
    }

    private static void print() {
        System.out.println("-----------");
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println("");
        }
    }


    //거리계산
    static public int distance(int r, int c){
        return Math.abs(r-out.r) +Math.abs(c-out.c);
    }

    //이동
    static public void move(){
        //맵을 확인
        //-1일때 출구 위치가 최단이 되는 거리 찾기
            //출구와 r이나c가 같으면 직선으로 확인
            //출구와 대각선일때
                //좌우 or 상하 를 확인
                //상하 이동이 먼저 가능하면 해당 좌표로 이동
                //이동시 count+1
        int[][] newMap = new int[N][N];
        list = new ArrayList<>();
        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                //음수는 몇명이 거기에 있는지이다.
                if(map[i][j]!=-99&&map[i][j]<0){
                    //거리비교
                    int center = distance(i,j);

                    boolean isMove = false;
                    for(int d=0;d<4;d++){
                        int zr = i+dr[d];
                        int zc = j+dc[d];
                        if(zr==out.r&&zc==out.c){
                            //도착함
                            isMove = true;
                            count+=(-map[i][j]);
                            M-=(-map[i][j]);
                            break;
                        }
                        if(zr>=0&&zc>=0&&zr<N&&zc<N&&map[zr][zc]<=0){
                            //이동이 가능하다.
                            if(distance(zr, zc) < center){
                                //만약 거리가 더 짧으면 거기로 이동
                                isMove = true;
                                newMap[zr][zc] += map[i][j];//
                                count+=(-map[i][j]);
                                list.add(new Pos(zr,zc));
                                break;
                            }
                        }
                    }
                    if(!isMove){
                        newMap[i][j] += map[i][j];//그냥 가만히
                        list.add(new Pos(i,j));
                    }
                }else{
                    newMap[i][j] += map[i][j];
                }
            }   
        }

        map = newMap;
    }

    //사각형찾기
    static public void findSquare(Pos lt, Pos rb){
        //출구를 기준으로 사각형 찾기
        int minN = 11; //최소 사각형 크기
        //오른쪽 또는 아래에 있는 좌표를 찾는다.
        //각 좌표들의 가장 오른쪽, 가장 아래쪽 좌표를 조합한것을 하나 만든다.
        //그 좌표를 기준으로 왼쪽 위 좌표를 만든다.
        //-좌표는 0으로 만들어서 가장 왼쪽 위 좌표를 만든다.
        boolean isFind = false;
        for(int i=0;i<list.size();i++){
            int tmpN = Math.max(Math.abs(list.get(i).r-out.r),Math.abs(list.get(i).c-out.c));
            if(tmpN<=minN){
                int tmpR = Math.max(out.r,list.get(i).r);//가장 아래 좌표
                int tmpC = Math.max(out.c,list.get(i).c);//가장 오른쪽 좌표

                //가장 왼쪽위 좌표
                int r = tmpR-tmpN<0?0:tmpR-tmpN;
                int c = tmpC-tmpN<0?0:tmpC-tmpN;

                //기존 작은 사각형인지 확인
                if(tmpN<minN){
                    minN = tmpN;
                    lt.r = r;
                    lt.c = c;
                    rb.r = r+minN;
                    rb.c = c+minN;
                    isFind = true;
                }else{
                    //기존 lt보다 왼쪽 위인지 확인
                    if(r<lt.r){
                        lt.r = r;
                        lt.c = c;
                        rb.r = r+minN;
                        rb.c = c+minN;
                        isFind = true;
                    }else if(r==lt.r){
                        if(c<lt.c){
                            lt.r = r;
                            lt.c = c;
                            rb.r = r+minN;
                            rb.c = c+minN;
                            isFind = true;
                        }
                    }
                }

            }
        }
        if(!isFind){
            // System.out.println("없는데요?");
            // print();
        }
    }

    //회전
    static public void turn(int r1, int c1, int r2,int c2){
        //왼족위r1,c1
        //오른쪽 아래 r2,c2

        int n = r2-r1+1;
        //해당 크기의 사각형을 만든다.
        int[][] copy = new int[n][n];
        //값만 옮긴다.
        for(int i=r1;i<=r2;i++){
            for(int j=c1;j<=c2;j++){
                copy[i-r1][j-c1] = map[i][j];
            }
        }
        //회전을 진행 + -1씩 감소
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                map[j+r1][n-i-1+c1] = copy[i][j]>0?copy[i][j]-1:copy[i][j];

                if(copy[i][j]==-99){
                    //출구이면 출구 위치 갱신
                    out.r = j+r1;
                    out.c = n-i-1+c1;
                }
            }
        }
    }

}

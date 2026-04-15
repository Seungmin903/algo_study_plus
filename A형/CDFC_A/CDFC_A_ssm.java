//시간 : 93 ms
//전략 : 시뮬레이션 / 조건 분기
//풀이 : 주석으로 대체

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class A_ssm {
	//방향 배열(동남서북)
	static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
	//현재 위치 변수
	static int dir = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T;tc++) {
			//격자 크기
			int N = Integer.parseInt(br.readLine());
			//사과 위치 저장할 배열(1~9)
			int[][] apple = new int[10][2];
			
			// 
			for(int i =0;i<N;i++) {
				String line = br.readLine()	;
				for(int j =0;j<N;j++) {
					
					int a = line.charAt(j) -'0';
					//만약 현재위치에 사과가 등장했다면
					if(a != 0) {
						// 사과 위치 저장
						apple[a][0] = i;
						apple[a][1] = j;						
					}
				}
			}
			//현재 방향 : 동쪽 초기화
			dir = 0;
			// 현재 위치 & 회전 횟수 초기화
			int nx =0;
			int ny =0;
			int rotate = 0;
			
			for(int i =1;i<=9;i++) {
				//만약 등장한 모든 사과를 탐색했다면 빠져나오기
				if(apple[i][0] == 0 && apple[i][1] == 0) break;
				
				//사과별로 시뮬레이션
				while(true) {
					//동
					if(dir == 0) {
						if(apple[i][1] > ny) {
							ny = apple[i][1];
						}
						else {
							dir = (dir + 1) % 4;
							rotate += 1;
						}
					}
					//남
					else if( dir == 1){
						if(apple[i][0] > nx) {
							nx = apple[i][0];
						}
						else {
							dir = (dir + 1) % 4;
							rotate += 1;
						}
					}
					//서
					else if(dir  == 2) {
						if(apple[i][1] < ny) {
							ny = apple[i][1];
						}
						else {
							dir = (dir + 1) % 4;
							rotate += 1;
						}
					}
					//북
					else if( dir == 3) {
						if(apple[i][0] < nx) {
							nx = apple[i][0];
						}
						else {
							dir = (dir + 1) % 4;
							rotate += 1;
						}
					}
					//만약 사과 위치로 도착했다면 빠져나오기
					if(nx == apple[i][0] && ny == apple[i][1])break;
				}
			}
			sb.append(rotate).append("\n");
		}
		System.out.println(sb.toString());
	}

}

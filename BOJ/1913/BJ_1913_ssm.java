//**시간**: 328ms
//**메모리** : 69408KB
//**전략** : 구현
//
//1. 숫자를 N*N에서 시작하여 1까지 채워넣는다
//2. 만약 다음 위치가 배열을 벗어나거나, 이미 채워져 있으면 반 시계방향으로 방향 돌리기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1913_ssm {
	static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
	static int dir = 0 ;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int goalNum = Integer.parseInt(br.readLine());
		int gX = 0, gY = 0;
		int[][] arr = new int[N][N];
		
		
		int num = N*N;
		int nx =0, ny =0;
		
		while(num > 0) {
			arr[nx][ny] = num;
			if(num == goalNum) {
				gX = nx+1;
				gY = ny+1;
			}
			num -= 1;
			if(nx + dx[dir] >= N || nx + dx[dir] < 0 || ny + dy[dir] >= N || ny + dy[dir] <0 || arr[nx + dx[dir]][ny+ dy[dir]] != 0) {
				dir = (dir + 1)%4;
			}
			nx += dx[dir];
			ny += dy[dir];
		}
		for(int i =0; i<N; i++) {
			for(int j =0; j<N;j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		sb.append(gX).append(" ").append(gY);
		System.out.println(sb.toString());
	}
}

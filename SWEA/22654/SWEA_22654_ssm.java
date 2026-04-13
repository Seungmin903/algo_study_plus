//시간 : 93ms
//메모리 : 25,600KB
//전략 : 시뮬레이션
//풀이 : 주석으로 대체

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_22654_ssm {
	//현재 map 크기
	static int N;
	//주어진 맵
	static char[][] map;

	//시계방향으로 저장된 위치 배열
	static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };
	//현재 바라보는 방향
	static int dir = 0;
	//시작점
	static int sx, sy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			//맵 입력
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j);
					//시작점 따로 저장
					if (map[i][j] == 'X') {
						sx = i;
						sy = j;
					}
				}
			}
			// 주어질 커맨드 개수
			int Q = Integer.parseInt(br.readLine());
			for (int i = 0; i < Q; i++) {
				//새 커맨드 시작시 방향 초기화(위)
				dir = 0;
				//현재위치 시작점으로 초기화
				int nx = sx;
				int ny = sy;
				
				st = new StringTokenizer(br.readLine());
				int C = Integer.parseInt(st.nextToken());
				String command = st.nextToken();
				//주어진 커맨드 길이동안
				for (int nth = 0; nth < C; nth++) {
					char nCom = command.charAt(nth);
					//현재 커맨드 = R
					if (nCom == 'R') {
						// 방향 시계방향으로 바꿈
						dir = (dir + 1) % 4;
						//현재 커맨드 = L
					} else if (nCom == 'L') {
						//방향 반시계방향으로 바꿈
						dir = (dir + 3) % 4;
						//현재 커맨드 = A
					} else if (nCom == 'A') {
						//만약 맵을 벗어나지 않고, 앞에 장애물(나무)가 없다면
						if(nx + dx[dir] >= 0 && nx + dx[dir] < N && ny + dy[dir] >= 0 && ny + dy[dir] < N && map[nx + dx[dir]][ny + dy[dir]] != 'T') {
							//바라보는 방향으로 한 칸 가기(현재위치 설정)
							nx += dx[dir];
							ny += dy[dir];
						}
					}
				}
				//커맨드가 다 끝나고 현재 서있는 위치가 원하는 도착지이면 1, 아니면 0 저장
				if(map[nx][ny] == 'Y') {
					sb.append(1).append(" ");
				}
				else {
					sb.append(0).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}

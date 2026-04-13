import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1873_ssm {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int[] tank = new int[2];
			char[][] map = new char[H][W];
			int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
			int dir = 0;

			// 맵 입력받기
			for (int i = 0; i < H; i++) {
				String li = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = li.charAt(j);
					if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
						tank[0] = i;
						tank[1] = j;
						if (map[i][j] == '^') {
							dir = 0;
						} else if (map[i][j] == 'v') {
							dir = 1;
						} else if (map[i][j] == '<') {
							dir = 2;
						} else {
							dir = 3;
						}
						//위치 입력 받은 후 추후 맵 출력을 위해 평지로 변경
						map[i][j] = '.';
					}
				}
			}

			// 입력 줄 길이
			int N = Integer.parseInt(br.readLine());

			// 커맨드
			String command = br.readLine();

			/* 입력 끝 */

//			U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
//			D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
//			L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
//			R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
//			S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
			
			for (int i = 0; i < N; i++) {
				//현재 커맨드
				char com = command.charAt(i);
				// 현재 전차 위치
				int nx = tank[0],ny = tank[1];
				
				//포탄 발사시
				if (com == 'S') {
					//맵을 벗어날때까지
					while(nx >= 0 && nx < H && ny >=0 && ny < W) {
						//만약 벽돌 벽 이라면
						if(map[nx][ny] == '*') {
							//파괴 후 포탄소멸
							map[nx][ny] = '.';
							break;
						}
						//강철 벽이면
						else if(map[nx][ny] == '#') {
							// 포탄 소멸
							break;
						}
						// 아무일도 일어나지 않았다면 다음칸 보기
						nx += dx[dir];
						ny += dy[dir];
					}
					//포탄 처리 완료했다면 다음 커맨드 보기
					continue;

				}
				//커맨드에 따른 방향 변경
				if (com == 'U') {
					dir = 0;
				} else if (com == 'D') {
					dir = 1;
				} else if (com == 'L') {
					dir = 2;
				} else if (com == 'R') {
					dir = 3;
				}
				//방향 변경 후, 만약 해당 방향 앞이 맵을 벗어나지 않고 평지라면 현재 위치 변경
				nx += dx[dir];
				ny += dy[dir];
				if(nx >= 0 && nx < H && ny >=0 && ny < W && map[nx][ny] == '.') {
					tank[0] = nx;
					tank[1] = ny;
				}
			}
			//최종 방향에 따른 맵에 전차 배치하기
			if(dir == 0) {
				map[tank[0]][tank[1]] = '^';
			}else if (dir == 1) {
				map[tank[0]][tank[1]] = 'v';
			} else if (dir == 2) {
				map[tank[0]][tank[1]] = '<';
			} else if (dir == 3) {
				map[tank[0]][tank[1]] = '>';
			}
			//맵 출력
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}

		}
		System.out.println(sb.toString());

	}

}

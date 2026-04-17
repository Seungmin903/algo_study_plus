import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class CDFC_B_ssm {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static boolean[][] eaten;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			eaten = new boolean[N][N];
			int result = 0, sx = 0 , sy = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 2) {
						sx = i;
						sy = j;
						map[i][j] = 0;
					}
				}
			}
			move(sx, sy, 0);
			//총 먹은 알 개수 세기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (eaten[i][j]) {
						result += 1;
					}
				}
			}
			System.out.println(result);
		}

	}
	
	//DFS + 백트래킹
	static void move(int x, int y, int idx) {
		//3번 이동했으면 return
		if (idx == 3) {
			return;
		}
		//4방 탐색
		for (int i = 0; i < 4; i++) {
			
			int dir = i;
			int one = 0;
			int nx = x ;
			int ny = y ;

			while (true) {
				nx += dx[dir];
				ny += dy[dir];
				if(nx < 0 || nx >= N || ny < 0 || ny >= N)break;
				//
				if (map[nx][ny] == 1) {
					one += 1;
					//첫번째 알이면 못 잡고 넘어가야 하므로
					if(one == 1) {
						continue;
					}
					// 1개의 알 뒤에 또 다른 알이 나온다면 그 알은 잡을 수 있으므로 (+ 방문 했으면 넘어가기)
					else if (one == 2 && !visited[nx][ny]) {
						map[nx][ny] = 0;
						visited[nx][ny] = true;
						eaten[nx][ny] = true;
						move(nx, ny, idx + 1);
						visited[nx][ny] = false;
						map[nx][ny] = 1;
						break;
					}
				} 
				//앞에 알이 이미 있으면 그 뒤로는 다 방문할 수 있으므로
				if (one == 1 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					move(nx, ny, idx + 1);
					visited[nx][ny] = false;
				}
			}

		}

	}

}
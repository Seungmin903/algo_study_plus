//**시간**: 84ms
//**메모리** :12636KB
//**전략** : DFS
//
//1. 전체 배열을 돌면서 만약 방문하지 않은 곳이라면 방문
//2. 나보다 큰 숫자가 8칸 안에 있으면 isPeak = false (봉우리가 아님)
//3. 나랑 같은 숫자가 8칸 안에 있고 방문한 적없으면 방문 (DFS) - 같은 높이끼리 묶기
//4. 나보다 작은숫자밖에 없으면 결과 +1
//5. 결과 출력
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_1245_ssm {
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 }, dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static int[][] farm;
	static boolean[][] visited;
	static int N, M;
	static boolean isPeak;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		farm = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				farm[i][j] = Integer.parseInt(st.nextToken());
			}

		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] == false) {
					isPeak = true;
					DFS(i, j);
					if (isPeak)
						result += 1;

				}
			}
		}
		System.out.println(result);
	}

	static void DFS(int x, int y) {
		int num = farm[x][y];
		visited[x][y] = true;

		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && nx < N && ny >= 0 && ny < M ) {
				if (farm[nx][ny] > num) {
					isPeak = false;
				} else if (farm[nx][ny] == num) {
					if(visited[nx][ny] == false) {
						visited[nx][ny] = true;
						DFS(nx, ny);
						
					}
				}
			}
		}
	}

}

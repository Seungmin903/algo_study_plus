package p0411;

import java.util.*;
import java.io.*;

public class SWEA5105 {
	static int N, startX, startY;
	static int[][] map;
	static int[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());

		for (int t = 1; t <= TC; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new int[N][N];
			for(int i=0; i<N; i++) {
				Arrays.fill(visited[i], -1);
			}
			
			for (int r = 0; r < N; r++) {
				String lines = br.readLine();

				for (int c = 0; c < N; c++) {
					map[r][c] = lines.charAt(c) - '0';

					if (map[r][c] == 2) {
						startX = r;
						startY = c;
					}
				}
			}

			int cnt = BFS(startX, startY);

			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	private static int BFS(int sx, int sy) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { sx, sy });
		visited[sx][sy] = 0;

		while (!q.isEmpty()) {
			int[] now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dr[i];
				int ny = now[1] + dc[i];
				
				// 범위 내이며, 방문하지 않았다면,
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == -1) {
					if (map[nx][ny] == 3) { // 도착지에 도착했다면
						return visited[now[0]][now[1]];
					}

					if (map[nx][ny] == 0) {  // 길이라면
						visited[nx][ny] = visited[now[0]][now[1]] + 1;
						q.add(new int[] { nx, ny });
					}
				}
			}

		}

		return 0;
	}

}

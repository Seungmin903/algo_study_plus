package BOJ;

/*
 * 시간 : 80ms
 * 메모리 : 12, 548kb
 * 전략 : bfs
 * 1. R, G, B값을 배열로 저장
 * 2. 일반 사람의 경우, R/G/B를 모두 다른 그룹으로 BFS 진행 -> bfs()
 * 3. 적록 색약의 경우, R과 G는 같은 그룹으로 생각하고 BFS 진행 -> bfsV2()
 * 4. 중요!!!
 * 	=> BFS 진행할 때, 큐에 넣기 전에 방문 표시를 하자. (큐에서 꺼낼 때 방문 표시하면 이미 큐에 중복으로 들어가기 때문)
 */

import java.util.*;
import java.io.*;

public class BOJ10026 {
	static int N;
	static int RGB, notRGB;
	static char[][] map;
	static char[][] mapV2;
	static boolean[][] visited;
	static Queue<Color> q = new LinkedList<>();

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		for (int r = 0; r < N; r++) {
			String lines = br.readLine();
			for (int c = 0; c < N; c++) {
				map[r][c] = lines.charAt(c);
			}
		}

		// 일반 사람
		RGB = 0;
		visited = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c]) {
					q.add(new Color(r, c, map[r][c]));
					visited[r][c] = true;
					BFS();
					RGB++;
				}
			}
		}

		// 적록색약
		notRGB = 0;
		visited = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c]) {
					q.add(new Color(r, c, map[r][c]));
					visited[r][c] = true;
					BFS_v2();
					notRGB++;
				}
			}
		}

		sb.append(RGB).append(" ").append(notRGB);

		System.out.println(sb);
	}

	private static void BFS_v2() {
		while (!q.isEmpty()) {
			Color now = q.poll();
			char color = now.c;

			for (int i = 0; i < 4; i++) {
				int nr = now.x + dr[i];
				int nc = now.y + dc[i];

				if (nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc]) {
					// R과 G는 같은 것으로 보기
					if (color == 'R' || color == 'G') {
						if (map[nr][nc] == 'R' || map[nr][nc] == 'G') {
							q.add(new Color(nr, nc, color));
							visited[nr][nc] = true;
						}
					} else {
						if (map[nr][nc] == color) {
							q.add(new Color(nr, nc, color));
							visited[nr][nc] = true;
						}
					}
				}
			}
		}
	}

	private static void BFS() {
		while (!q.isEmpty()) {
			Color now = q.poll();
			char color = now.c;

			for (int i = 0; i < 4; i++) {
				int nr = now.x + dr[i];
				int nc = now.y + dc[i];

				if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
					if (!visited[nr][nc] && map[nr][nc] == color) {
						q.add(new Color(nr, nc, color));
						visited[nr][nc] = true;
					}
				}
			}
		}
	}

	public static class Color {
		int x, y;
		char c;

		public Color(int x, int y, char c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}
}

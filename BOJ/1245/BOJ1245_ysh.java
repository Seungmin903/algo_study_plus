package P0317;

/*
 * 시간 : 144ms
 * 메모리 : 12,836kb
 * 전략 : dfs
 * 1.map 입력
 * 2. 현재 map의 최고 높이 좌표 찾기
 * 3. 8방 탐색으로 이전 높이 이하이면, 현재 좌표와 높이를 가지고 다음 dfs로 재귀
 * 4. 모든 탐색이 끝나면, 다시 현재를 기준으로 방문하지 않았으며 map 값이 가장 높은 좌표를 찾아 3번을 반복
 */

import java.io.*;
import java.util.*;

public class BOJ1245 {
	static int N, M, maxH, cnt, newX, newY;
	static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
	static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		maxH = Integer.MIN_VALUE;
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N][M];
		cnt = 0;

		while(true) {
			newX = -1;
			newY = -1;
			
			findMax();

			if(newX==-1 || newY==-1) break;
			
			cnt++;
			dfs(newX, newY, map[newX][newY]);
				
		}
		
		System.out.println(cnt);
	}
	
	private static void findMax() {
		int max = Integer.MIN_VALUE;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(!visited[r][c] && max<map[r][c]) {
					max = map[r][c];
					newX = r;
					newY = c;
				}
			}
		}
	}

	public static void dfs(int x, int y, int prev) {
		
		visited[x][y] = true;
		
		for(int i=0; i<8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx>=0 && ny>=0 && nx<N && ny<M) {
				if(!visited[nx][ny]) {
					if(prev>=map[nx][ny]) {
						visited[nx][ny] = true;
						dfs(nx, ny, map[nx][ny]);
					}
				}
			}
		}
	}
}

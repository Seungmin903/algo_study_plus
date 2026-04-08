package p0408;

/*
 * 시간 : 84ms
 * 메모리 : 26,112kb
 * 전략 : BFS
 * 1. 시작점 좌표를 받음
 * 2. 시작점부터 0인 곳으로 4방 탐색 BFS
 * 3. 방문한 곳은 1로 바꾸기
 * 4-1. 다음 방문하는 곳이 '3'이면 도착이므로 1
 * 4-2. 큐가 비었는데도 3을 못 만나면 0
 */

import java.util.*;
import java.io.*;

public class SWEA1226 {
	static int N;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = 10;
		
		for(int t=1; t<=TC; t++) {
			sb.append("#").append(Integer.parseInt(br.readLine())).append(" ");
			
			//16*16
			N = 16;
			map = new int[N][N];
			
			// 시작점, 도착점 좌표
			int startX = -1;
			int startY = -1;
			
			for(int r=0; r<N; r++) {
				String lines = br.readLine();
				for(int c=0; c<N; c++) {
					map[r][c] = lines.charAt(c) - '0';
					
					if(map[r][c]==2) {
						startX = r;
						startY = c;
					}
				}
			}
			
			if(BFS(startX, startY)) sb.append(1).append("\n");
			else sb.append(0).append("\n");
		}
		System.out.println(sb);
	}
	private static boolean BFS(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		map[x][y] = 1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now[0] + dr[i];
				int ny = now[1] + dc[i];
				
				// 범위 유효성 검사
				if(nx>=0 && ny>=0 && nx<N && ny<N) {
					if(map[nx][ny]==3) return true;
					if(map[nx][ny]==0) {  // 아직 가지 않은 길
						map[nx][ny] = 1;
						q.add(new int[] {nx, ny});
					}
				}
			}
		}
		
		return false;
	}

}

package p0415;

/*
 * 시간 : 93ms
 * 메모리 : -
 * 전략 : 구현
 * 1. 맵 입력 받기 -> 0이 아닌 가장 큰 수가 사과 수
 * 2. 사과 번호 순으로 좌표 저장
 * 3. 사과 좌표를 하나씩 꺼내 검색
 * 3-1. 현재 위치를 기준으로 방향이 0 또는 2이면 y좌표 이동
 * 3-2. 현재 위치를 기준으로 방향이 1 또는 3이면 x좌표 이동
 * 4-1. 사과 좌표에 도달하지 않았으면 방향 90도 회전, 회전 수 증가
 * 4-2. 사과 좌표에 도달했으면 반복문 증가 -> 다음 사과로
 * 5. 회전 수 출력 
 */

import java.util.*;
import java.io.*;

public class CDFC_apple {
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int M = Integer.MIN_VALUE;  // 사과 개수
			for(int r=0; r<N; r++) {
				String lines = br.readLine();
				for(int c=0; c<N ;c++) {
					map[r][c] = lines.charAt(c) - '0';
					M = Math.max(M, map[r][c]);
				}
			}
			
			// 사과 좌표 저장
			ArrayList<Points> apples = new ArrayList<>();
			for(int i=1; i<=M; i++) {
				for(int r=0; r<N; r++) {
					for(int c=0; c<N ;c++) {
						if(map[r][c] == i) {
							apples.add(new Points(r, c));
						}
					}
				}
			}
			
			// 사용자 초기 위치는 (0, 0)
			int nx = 0;
			int ny = 0;
			int cnt = 0;
			int dir = 0;
			// 사과 개수 만큼 반복
			for(int i=0; i<M; i++) {
				int ax = apples.get(i).x;
				int ay = apples.get(i).y;
				while(true) {
					if((dir == 1 && nx<ax) || (dir == 3 && nx>ax)) nx = ax;
					if((dir == 0 && ny<ay) || (dir == 2 && ny>ay)) ny = ay;
					
					if(nx == ax && ny == ay) {   // 사과에 도착
						break;
					}
					dir = (dir + 1) % 4;
					cnt++;
				}				
			}
			
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	public static class Points {
		int x, y;

		public Points(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

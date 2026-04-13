package p0411;

/*
 * 시간 : 78ms
 * 메모리 : 25,344kb
 * 전략 : 구현
 * 1. 맵 입력 받으면서 X의 좌표 저장
 * 2. 각 턴이 시작되면 현재 좌표는 X 좌표로, 바라보는 방향(dir)은 0으로 초기화
 * 3. 커맨드 분기
 * 3-1. 'A'는 앞으로 이동 -> 해당 좌표의 값이 좌표 내이며 'T'가 아니라면 좌표 갱신
 * 3-2. 'R'은 dir을 1 증가시키는데, 0~4 범위 내로 들어오게 %4 이용
 * 3-3. 'L'은 dir을 1 감소시키는데, 0~4 범위 내로 들어오게 %4 이용
 * 4. 각 턴 별로 최종 좌표 값이 'Y'라면 1 출력, 아니면 0 출력
 */

import java.util.*;
import java.io.*;

public class SWEA22654 {
	// 상-우-하-좌
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int N, carX, carY, dir, nowX, nowY;
	static char[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int t=1; t<=TC; t++) {
			sb.append("#").append(t).append(" ");
			
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			
			carX = -1;
			carY = -1;
			
			for(int r=0; r<N; r++) {
				String lines = br.readLine();
				for(int c=0; c<N; c++) {
					map[r][c] = lines.charAt(c);
					if(map[r][c]=='X') {  // 시작 RC카 좌표
						carX = r;
						carY = c;
					}
				}
			}
			
			
			
			int Q = Integer.parseInt(br.readLine());
			for(int i=0; i<Q; i++) {
				st = new StringTokenizer(br.readLine());
				int len = Integer.parseInt(st.nextToken());
				
				nowX = carX;
				nowY = carY;
				dir = 0;  // 기본 방향: '위'
				
				String cmds = st.nextToken();
				for(int k=0; k<len; k++) {
					int now = cmds.charAt(k);
					
					if(now=='A') {  // 직진
						move();
					}
					else if(now=='L') {  // 좌회전
						dir = (dir-1+4) % 4;
					}
					else if(now=='R') {  // 우회전
						dir = (dir+1+4) % 4;
					}
					
				}
				
				if(map[nowX][nowY]=='Y') sb.append(1).append(" ");
				else sb.append(0).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	private static void move() {
		// 한 칸 직진할 때의 좌표
		int newX = nowX + dr[dir];
		int newY = nowY + dc[dir];
		
		// 범위 유효하지 않으면 무시
		if(newX<0 || newY<0 || newX>=N || newY>=N) return;
		
		// 갈 수 있는 곳이라면 직진
		if(map[newX][newY] != 'T') {  // 나무만 아니면 이동 가능
			nowX = newX;
			nowY = newY;			
		}
	}

}

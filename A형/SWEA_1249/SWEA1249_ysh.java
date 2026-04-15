package p0413;

/*
 * 시간 : 111ms
 * 메모리 : 26,496kb
 * 전략 : 다익스트라
 * 1. 시작점인 (0, 0)부터 우선순위 큐에 넣기
 * 2. cost가 적은 곳 부터 진행 (compareTo 오버라이드)
 * 3. 4방향 탐색으로 가면서 [지금까지의 최단 거리 + 새로운 거리] 와 기존 최단 거리를 비교해서 더 작은 값으로 갱신
 * 4. 도착점 dist값 출력
 */

import java.util.*;
import java.io.*;

public class SWEA1249 {
	static int N; 
	static int[][] map, dist;
	static boolean[][] visited;
	static PriorityQueue<Node> pq;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dist = new int[N][N];
			visited = new boolean[N][N];
			
			for(int r=0; r<N; r++) {
				String lines = br.readLine();
				for(int c=0; c<N; c++) {
					map[r][c] = lines.charAt(c) - '0';
				}
			}
			
			for(int i=0; i<N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			
			pq = new PriorityQueue<>();
			// 시작점 (0, 0) - 도착점 (N-1, N-1)
			dist[0][0] = 0;
			visited[0][0] = true;
			pq.add(new Node(0, 0, 0));
			
			while(!pq.isEmpty()) {
				Node now = pq.poll();
				visited[now.r][now.c] = true;
				
				if(now.dist > dist[now.r][now.c]) continue;
				
				for(int i=0; i<4; i++) {
					int nr = now.r + dr[i];
					int nc = now.c + dc[i];
					
					if(nr>=0 && nc>=0 && nr<N && nc<N && !visited[nr][nc]) {
						int newDist = now.dist + map[nr][nc];
						if(dist[nr][nc]>newDist) {
							dist[nr][nc] = newDist;
							pq.add(new Node(nr, nc, newDist));
						}
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(dist[N-1][N-1]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static class Node implements Comparable<Node>{
		int r, c, dist;
		public Node(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.dist - n.dist;
		}
	}
}


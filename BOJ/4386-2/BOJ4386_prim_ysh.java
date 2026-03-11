package algo_study_plus;

/*
 * 시간 : 112ms
 * 메모리 : 13292kb
 * 전략 : 조합, Prim
 * 1. 모든 별의 좌표 받기
 * 2. 인덱스를 별 번호라 생각하고 n개의 별을 2개 고르는 조합을 찾기
 * 3. 얻은 조합별 두 별의 거리를 계산하여 (별1, 별2, 거리)로 그래프 생성
 * 4. Prim으로 모든 노드를 선택하되, 간선의 가중치가 최소가 되는 경우의 가중치 계산
 * 4-1. 우선 순위 큐에 시작점의 번호와 거리 초기화
 * 4-2. 큐가 비어있지 않다면, 큐에서 꺼낸 노드로 방문 안했는지 확인 -> 방문 및 가중치 누적
 * 4-3. 4-2에서 꺼낸 노드의 인접 노드 중 방문하지 않은 노드를 큐에 넣기
 * 4-4. 4-2와 4-3을 큐가 빌 때까지 반복
 * 
 */

import java.io.*;
import java.util.*;

public class BOJ4386_prim {
	public static class Point {
		double x, y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Edge implements Comparable<Edge>{
		int to;
		double dist;

		public Edge(int to, double dist) {
			this.to = to;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Edge e) {
			return Double.compare(this.dist, e.dist);
		}
	}

	static int N;
	static ArrayList<Edge>[] graph;
	static ArrayList<Point> stars;
	static int[] starComb;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		graph = new ArrayList[N+1];
		for(int i=0; i<N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		stars = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double starX = Double.parseDouble(st.nextToken());
			double starY = Double.parseDouble(st.nextToken());
			stars.add(new Point(starX, starY));
		}

		// 별을 2개 고르는 조합과 해당 거리 구하기
		starComb = new int[2];
		combi(0, 0);
		
		visited = new boolean[N];
		
		//prim 알고리즘
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(0, 0));  // 임의로 0번 별 시작
		
		double result = 0;
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(visited[cur.to]) continue;
			
			result += cur.dist;
			visited[cur.to] = true;
			
			for(Edge next : graph[cur.to]) {
				if(!visited[next.to]) {
					pq.offer(next);
				}
			}
		}
		
		System.out.printf("%.2f", result);
	}

	// N개 중에 2개 고르는 조합 구하기
	private static void combi(int idx, int start) {
		if(idx == 2) {
			calDist(starComb);
			return;
		}
		
		for(int i=start; i<N; i++) {
			starComb[idx] = i;
			combi(idx + 1, i+1);
		}
	}

	// 별 조합들의 거리 계산 및 그래프 생성
	private static void calDist(int[] starList) {
		// 별들의 거리 계산
		int starIdx = starList[0];
		int starNext = starList[1];

		// 두 별의 거리 계산
		double x1 = stars.get(starIdx).x;
		double y1 = stars.get(starIdx).y;
		double x2 = stars.get(starNext).x;
		double y2 = stars.get(starNext).y;

		double starDist = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
		
		// 그래프 생성
		graph[starIdx].add(new Edge(starNext, starDist));
		graph[starNext].add(new Edge(starIdx, starDist));
	}
}

package p0312;

/*
 * 시간 : 1,580ms
 * 메모리 : 348,992kb
 * 전략 : dijkstra
 * 1. 입력 받으면서 그래프 생성 - 단방향 그래프인 것을 유의, 가중치가 없으므로 모두 1로 통일
 * 2. dist 배열 생성해서 모두 매우 큰 값으로 초기화
 * 3. 우선순위 큐에 시작점부터 넣음
 * 4. 큐가 비어있지 않다면 다음을 반복
 * 4-1. 꺼낸 노드가 방문했던 곳이 아니면 계속
 * 4-2. 인접 노드를 확인해서 다음 가려는 곳의 dist값과 현재 노드를 거쳐 새로 추가되는 값을 합한 값을 비교해 더 작은 값으로 갱신
 * 5. 생성된 배열을 순회하며 K와 동일한 곳이 있다면 출력 -> 하나도 없으면 -1 출력
 */

import java.io.*;
import java.util.*;

public class BOJ18352  {
	public static class Edge implements Comparable<Edge>{
		int to, weight;
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.weight - e.weight;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		List<Edge>[] graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			graph[A].add(new Edge(B, 1));  // 가중치 없는 그래프라 모두 1
		}

		int[] dist = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			dist[i] = (int) 1e9;
		}
		
		dist[X] = 0;  // 시작점
		pq.add(new Edge(X, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			int minIdx = cur.to;
			int minDist = cur.weight;
			
			if(minDist != dist[minIdx]) continue;

			// 인접 노드 확인
			for(Edge next : graph[minIdx]) {
				// 다음에 가려는 곳의 거리가 현재 노드를 거쳐 가는 것보다 크다면 갱신
				if(dist[next.to] > dist[minIdx]+next.weight) {
					dist[next.to] = dist[minIdx]+next.weight;
					pq.add(new Edge(next.to, dist[next.to]));
				}
			}
		}

		boolean flag = false;
		for (int i = 1; i <= N; i++) {
			if (dist[i] == K) {
				flag = true;
				sb.append(i).append("\n");
			}
		}
		if(!flag) System.out.println(-1);
		else System.out.println(sb);
	}

}

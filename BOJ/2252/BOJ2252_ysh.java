package p0330;

/*
 * 시간 : 352ms
 * 메모리 : 44,460kb
 * 전략 : 위상정렬
 * 1. 방향 그래프 생성하면서 인접차수 배열 생성
 * 2. 인접차수가 0인 곳은 q에 넣음
 * 3. 큐에서 꺼내 인접 노드를 확인해 해당 노드의 인접차수 감소 + 큐에서 꺼낼 때 방문 표시
 * 4. 인접 노드의 인접 차수가 0이 되었으면, 그 노드를 큐에 넣고 2번 다시 반복
 * 5. 모든 인접 노드를 확인했으면, 다시 방문하지 않은 인접차수가 0인 배열에 대해 진행
 */

import java.util.*;
import java.io.*;

public class BOJ2252 {
	static int N, M;
	static int[] degree;
	static boolean[] visited;
	static ArrayList<Integer>[] graph;
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		degree = new int[N+1];
		visited = new boolean[N+1];
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			graph[s].add(e);
			degree[e]++;
		}
		
		for(int i=1; i<=N; i++) {
			if(degree[i]==0  && !visited[i]) {
				q.add(i);
				
				while(!q.isEmpty()) {
					int now = q.poll();
					visited[now] = true;
					sb.append(now).append(" ");
					
					for(int next : graph[now]) {
						degree[next]--;  // 꺼냈으니까 간선 사라짐
						
						if(degree[next]==0 && !visited[next]) {  // 방문하지 않고, 진입차수가 0이 되면 큐에 넣기
							
							q.add(next);
						}
					}
				}
			}
		}
		
		System.out.println(sb);
	}
}

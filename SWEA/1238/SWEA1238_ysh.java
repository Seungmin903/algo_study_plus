package p0411;

/*
 * 시간 : 94ms
 * 메모리 : 26,752kb
 * 전략 : BFS
 * 1. (시작 -> 도착) 입력 받아 그래프 생성
 * 2. 방문 배열을 -1으로 초기화
 * 3. 시작점부터 그래프 탐색(BFS) 시작
 * 4. 큐에서 꺼낸 노드의 연결 노드 중 방문하지 않은 것이 있다면, 방문 표시 후 큐에 넣기
 * 	=> 방문 표시는 이전 방문값 + 1
 * 5. 방문 배열을 순회하면서, 최대값을 가진 최대 인덱스 출력
 */

import java.util.*;
import java.io.*;

public class SWEA1238 {
	static int N;
	static ArrayList<Integer>[] graph;
	static int[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = 10;
		
		for(int t=1; t<=T; t++) {
			sb.append("#").append(t).append(" ");
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[101];
			for(int i=1; i<=100; i++) {
				graph[i] = new ArrayList<>();
			}

			visited = new int[101];
			Arrays.fill(visited, -1);
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N/2; i++) {
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				graph[s].add(e);
			}
			
			BFS(start);
			
			int maxCnt = Integer.MIN_VALUE;
			int number = 0;
			for(int i=1; i<=100; i++) {
				if(maxCnt<=visited[i]) {
					maxCnt = visited[i];
					number = i;
				}
			}
			
			sb.append(number).append("\n");
		}
		
		System.out.println(sb);
	}
	private static void BFS(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = 0;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			// 다음 노드로 가면서 1증가
			for(int next : graph[now]) {
				if(visited[next] == -1) {  // 아직 방문하지 않았다면
					visited[next] = visited[now] + 1;
					q.add(next);
				}
			}
			
		}
	}

}

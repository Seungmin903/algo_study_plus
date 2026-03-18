package p0318;

/*
 * 시간 : 492ms
 * 메모리 : 47276kb
 * 전략 : kruskal
 * 1. 시작점, 도착점, 가중치를 하나의 Edge라는 클래스로 묶어 저장
 * 2. 가중치를 기준으로 오름차순 정렬
 * 3. 현재 선택되지 않은 최소 가중치를 가진 간선부터 kruskal 진행
 * 4. union이 true면 해당 간선에 대해 사이클이 발생하지 않으므로 가중치 누적 합, 간선 선택
 * 5. 선택한 간선 수가 노드의 수 -1 라면, 모두 선택한 것이므로 종료
 */

import java.io.*;
import java.util.*;

public class BOJ1922 {
	static ArrayList<Edge>[] arr;
	static int N, M;
	static Edge[] edgeList;
	static int[] uf;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		edgeList = new Edge[M];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(start, end, weight);
		}
		
		Arrays.sort(edgeList);

		uf = new int[N+1];
		int totalW = 0;
		int edgeCnt = 0;
		for(int i=1; i<=N; i++) {
			uf[i] = i;
		}
		
		for(Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				totalW += edge.weight;
				edgeCnt++;
				
				if(edgeCnt == N-1) break;
			}
		}

		System.out.println(totalW);
	}
	
	private static boolean union(int from, int to) {
		int nodeF = find(from);
		int nodeT = find(to);
		
		if(nodeF == nodeT) return false;
		
		uf[nodeF] = nodeT;
		return true;
	}

	private static int find(int x) {
		if(uf[x] == x) return x;
		int newN = find(uf[x]);
		uf[x] = newN;		
		return newN;
	}

	public static class Edge implements Comparable<Edge>{
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.weight - e.weight;
		}
	}
}

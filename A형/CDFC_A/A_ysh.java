package p0415;

import java.util.*;
import java.io.*;

public class BOJ1504 {
	static ArrayList<Edge>[] graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken()); 
		int E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[s].add(new Edge(e, w));
		}
		
		// 꼭 지나야 할 정점들
		int[] nodes = new int[2];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<2; i++) {
			nodes[i] = Integer.parseInt(st.nextToken());
		}
		
		
		
		
	}
	
	public static class Edge implements Comparable<Edge>{
		int to, w;
		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.w - e.w;
		}
	}
}

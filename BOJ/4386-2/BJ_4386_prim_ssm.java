//**시간**: 216ms
//**메모리** :19956KB
//**전략** : prim
//
//1. 모든 간선 입력받기
//2. priorityqueue 이용하여 가중치가 가장 작은것 부터 방문
//3. 만약 방문한 노드 있으면 continue
//4. 소수 둘째자리까지 출력


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_4386_prim_ssm {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		double result =0;
		int N = Integer.parseInt(br.readLine());
		List<Edge>[] edgelist = new ArrayList[N];
		PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) -> Double.compare(a.dist, b.dist));
		double[][] arr = new double[N][2];
		boolean[] visited = new boolean[N];
		for(int i =0; i<N;i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Double.parseDouble(st.nextToken());
			arr[i][1] = Double.parseDouble(st.nextToken());
			
		}
		for(int i =0;i<N;i++) {
			edgelist[i] = new ArrayList<>();
		}
		
		for(int i =0; i<N-1;i++) {
			for(int j =i+1;j<N;j++) {
				double x = arr[i][0] - arr[j][0];
				double y = arr[i][1] - arr[j][1];
				
				double len = Math.sqrt(x*x + y*y);
				edgelist[i].add(new Edge(j,len));
				edgelist[j].add(new Edge(i, len));
			}
			
		}
		pq.add(new Edge(0,0));
		while(!pq.isEmpty()) {
			Edge star = pq.poll();
			if(visited[star.end]) continue;
			visited[star.end] = true;
			result += star.dist;
			for(Edge e : edgelist[star.end]) {
				if(visited[e.end] == false) {
					pq.add(e);
				}
			}
		}
		System.out.printf("%.2f",result);
	}
	static class Edge{
		int end;
		double dist;
		public Edge(int end, double dist) {
			super();
			this.end = end;
			this.dist = dist;
		}
		
	}

}

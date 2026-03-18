//**시간**: 612ms
//**메모리** : 47612 KB
//**전략** : kruscal
//
//1. 간선을 모두 입력받는다
//2. 간선의 가중치가 작은 순으로 배열을 정렬한다
//3.  만약 집합끼리 합쳐진다면 결과에 가중치를 더해주고, 합쳐지지 않는다면(이미 같은 집합) 넘어간다
//4. 최소 가중치 출력
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1922_ssm {
	static int[] P;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static Edge[] Edgelist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		P = new int[N+1];
		Edgelist = new Edge[M];
		for(int i =0; i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			Edgelist[i] = new Edge(f,t,c);
		}
		for(int i =0 ;i<=N;i++) {
			P[i] =i;
		}
		Arrays.sort(Edgelist);
		int comCost =0;
		for(Edge e : Edgelist) {
			if(Union(e.from,e.to)) {
				comCost += e.cost;
			}
		}
		System.out.println(comCost);
		
	}
	static class Edge implements Comparable<Edge>{
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		public int compareTo(Edge e) {
			return Integer.compare(this.cost, e.cost);
		}
		
	}
	static boolean Union(int a, int b) {
		int Pa = Find(a);
		int Pb = Find(b);
		if(Pa == Pb) return false;
		P[Pb] = Pa;
		return true;
	}
	static int Find(int a) {
		if(a == P[a]) return a;
		
		else return Find(P[a]);
	}

}

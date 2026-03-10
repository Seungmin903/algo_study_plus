//시간: 80 ms
//메모리 : 12348 kb
//전략 : kruskal

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_4386 {
	static int[] starNum;
	static PriorityQueue<Edge> edgelist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

			double result = 0;

			int N = Integer.parseInt(br.readLine());

			double[][] star = new double[N][2];

			starNum = new int[N];

			edgelist = new PriorityQueue<>();
			
			for(int a =0; a<N;a++) {
				st = new StringTokenizer(br.readLine());
				star[a][0] = Double.parseDouble(st.nextToken());
				star[a][1] = Double.parseDouble(st.nextToken());			
			
			}
			// 연결확인용 초기화
			for(int i =0; i<N;i++) {
				starNum[i] = i; 
			}
			
			for(int i =0; i<N-1;i++) {			
				for(int j =i+1; j<N;j++) {
					double len = Math.sqrt((star[i][0] - star[j][0])*(star[i][0] - star[j][0]) + (star[i][1] - star[j][1])*(star[i][1] - star[j][1]));
					edgelist.add(new Edge(i,j,len));
					
				}
			}

			int count = 0;
			// 모두 볼때까지 돌기
			while(!edgelist.isEmpty()){
				Edge e = edgelist.poll();
				//만약 같은 그룹(사이클 발생 x)이라면
				if(Union(e.s,e.e)) {
					count+=1;
					result += e.w;
				}
				//만약 최대 간선수라면 빠져나오기
				if(count == N-1) {
					break;
				}
			}

			System.out.println(String.format("%.2f", result));

	}

	static class Edge implements Comparable<Edge> {
		int s, e;
		double w;

		public Edge(int s, int e, double len) {
			super();
			this.s = s;
			this.e = e;
			this.w = len;
		}

		public int compareTo(Edge e) {
			return Double.compare(this.w, e.w);
		}
	}

	static int Find(int a) {
		if (starNum[a] == a) {
			return a;
		}
		return starNum[a] = Find( starNum[a]);
	}

	static boolean Union(int a, int b) {
		int aR = Find(a);
		int bR = Find(b);
		if (aR == bR) {
			return false;
		}
		starNum[bR] = aR;
		return true;
	}

}

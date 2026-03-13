//**시간**: 640ms
//**메모리** :66872KB
//**전략** : dijkstra
//
//1. 모든 입력값을 입력받는다
//2. 시작점(s)에서의 가중치, g에서 가중치, h에서 가중치를 입력받을 dist , dist_g,dist_h를 선언하고 최대값으로 초기화
//3. list에 양방향으로 간선을 입력받는다
//4. g-h 길이를 따로 입력받아 놓는다
//5. 목적지 후보를 입력받고, 오름차순으로 정렬한다
//6. 2번에서 선언해둔 배열에 각각을 시작점으로 정해 모두 가중치를 입력받는다
//7. 만약 시작점 ↔ 후보지 길이가
//    1. 시작점→g 길이 + g-h 길이 + h→후보지 길이
//    2. 시작점→h 길이 + g-h 길이 + g→후보지 길이
//    
//    둘 중 하나의 값과 같다면
//    
//8. 후보지를 결과에 저장 후, 공백으로 구분하여 출력

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_9370_ssm {
	static class Node{
		int from, to, cost;

		public Node(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
	}
	static PriorityQueue<Node> queue = new PriorityQueue<>((a,b)->Integer.compare(a.cost, b.cost));
	static List<Node>[] list;
	static int[] dist;
	static int[] dist_g;
	static int[] dist_h;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 0;tc<TC;tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int gh =0;
			
			dist = new int[n+1];
			dist_g = new int[n+1];
			dist_h = new int[n+1];

			for(int i =1;i<=n;i++) {
				dist[i] = 100000000;
				dist_g[i] = 100000000;
				dist_h[i] = 100000000;
			}
			list = new ArrayList[n+1];
			for(int i =1;i<=n;i++) {
				list[i] = new ArrayList<>();
			}
			for(int i =0; i<m;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				list[a].add(new Node(a,b,cost));
				list[b].add(new Node(b,a,cost));
				if((a == h || a == g) && (b == h || b == g)) {
					gh = cost;
				}
			}
			int[] cand = new int[t];
			for(int i =0;i<t;i++) {
				cand[i] = Integer.parseInt(br.readLine());
			}
			Arrays.sort(cand);
			
			dist[s] = 0;
			queue.add(new Node(0,s,0));
			Dijk(dist);
			
			dist_g[g] = 0;
			queue.add(new Node(0,g,0));
			Dijk(dist_g);
			
			dist_h[h] = 0;
			queue.add(new Node(0,h,0));
			Dijk(dist_h);

			
			for(int i =0; i<t;i++) {
				int num = cand[i];
				if((dist[g] +dist_h[num]+gh) == dist[num] || (dist[h] +dist_g[num]+gh)== dist[num]) {
					sb.append(num).append(" ");
				}
			}

			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	static void Dijk(int[] d) {
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			for(Node a : list[n.to]) {
				if(a.cost + d[a.from] < d[a.to]) {
					d[a.to] = a.cost + d[a.from];
					queue.add(a);
				}
			}
			
		}
	}

}

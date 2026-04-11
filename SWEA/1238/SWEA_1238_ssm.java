//시간 :103 ms
// 메모리 :26,496 kb

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1238_ssm {
	//연결되어있는 노드 저장용 리스트
	static List<Integer>[] people;
	//방문처리
	static boolean[] visited;
	//몇번째와 노드 숫자 저장할 node class
	static class Node{
		int nth, num;

		public Node(int nth, int num) {
			super();
			this.nth = nth;
			this.num = num;
		}
		
		//노드끼리 비교용
		public Node compareTo(Node n) {
			if(this.nth == n.nth) {
				return new Node(this.nth,Math.max(this.num, n.num));
			}
			else if(this.nth < n.nth) {
				return new Node(n.nth,n.num);
			}
			return new Node(this.nth,this.num);
		}
		
	}
	
	static Queue<Node> queue = new LinkedList<>();
	static Node max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int tc =1 ;tc<=10;tc++) {
			sb.append("#").append(tc).append(" ");
			people = new ArrayList[101];
			visited = new boolean[101];
			for(int i =0; i<=100;i++) {
				people[i] = new ArrayList<>();
			}
			st = new StringTokenizer(br.readLine());
			//2개씩 짝지어지기 때문에 길이 / 2
			int len = Integer.parseInt(st.nextToken()) / 2;
			int start = Integer.parseInt(st.nextToken());
			//시작점 큐에 저장
			queue.add(new Node(0,start));
			
			//가장 마지막에 연략받을 가장 큰 숫자 저장용 변수
			max = new Node(0,start);
			visited[start] = true;
			
			st = new StringTokenizer(br.readLine());
			for(int i =0;i<len;i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				people[from].add(to);		
			}
			BFS();
			
			
			
			sb.append(max.num).append("\n");
			queue.clear();
		}
		System.out.println(sb.toString());
		
	}
	static void BFS() {
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			int nth = n.nth;
			int num = n.num;
			//현재 꺼낸 node와 max 노드 비교
			max = max.compareTo(n);
			
			for(int a : people[num]) {
				//방문하지 않았던 노드들만 방문
				if(!visited[a]) {
					queue.add(new Node(nth+1,a));
					visited[a] = true;
				}
			}
		}
	}

}



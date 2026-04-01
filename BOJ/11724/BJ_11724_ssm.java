//**시간**: 568ms
//**메모리** :145308KB
//**전략** : DFS
//
//1. 연결되어있는 노드끼리 각 노드에 간선 저장
//2. 방문 안된 노드만 방문해서 DFS(연결된 요소 찾기)
//3. 총 연결요소 출력
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_11724_ssm {
	static int N,M;
	static boolean[] visited;
	static List<Integer>[] edge;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		edge = new ArrayList[N+1];
		for(int i =1 ;i<=N;i++) {
			edge[i] = new ArrayList<>();
		}
		
		for(int i =0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			edge[a].add(b);
			edge[b].add(a);
		}
		int group =0;
		for(int i =1;i<=N;i++) {
			if(visited[i]) continue;
			group += 1;
			DFS(i);
		}
		System.out.println(group);
	}
	static void DFS(int a) {
		visited[a] = true;
		for(int e : edge[a]) {
			if(!visited[e]) {
				DFS(e);
			}
		}
	}

}

//**시간**: 392ms
//**메모리** :45976KB
//**전략** : 위상 정렬
//
//1. 입력받으면서, 순서를 list 리스트에,  진입차수를 IN 배열에 저장
//2. 진입차수가 0인 학생을 큐에 넣음
//3. 진입차수 0부터 돌면서 다음번째  학생을 찾아간다
//4. 순서 출력
import java.io.*;
import java.util.*;

public class BJ_2252_ssm {
	static List<Integer>[] list;
	static int[] IN;
	static Queue<Integer> queue = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		IN = new int[N+1];
		list = new ArrayList[N+1];
		for(int i =1;i<=N;i++) {
			list[i] = new ArrayList<>();
		}
		for(int i =0;i<M;i++	) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			list[A].add(B);
			IN[B] += 1;
		}
		for(int i =1;i<=N;i++) {
			if(IN[i] == 0) {
				IN[i] -= 1;
				queue.add(i);
			}
			
		}
		Topology();
		System.out.println(sb.toString());
		
	}
	static void Topology() {
		while(!queue.isEmpty()) {
			int num = queue.poll();
			sb.append(num).append(" ");
			for(int a : list[num]) {
				IN[a] -= 1;
				if(IN[a] == 0) {
					queue.add(a);
				}
			}
		}
	}
	

}

//시간 : 84 ms
//메모리 : 13736KB
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17471_ssm {
	//조합 배열
	static boolean[] isSelected;
	static int N, result;
	// 간선 저쟝용 리스트
	static List<Integer>[] conn;
	//각 도시의 인구수
	static int[] baekjoonsi;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		baekjoonsi = new int[N + 1];
		isSelected = new boolean[N + 1];
		conn = new ArrayList[N + 1];
		result = -1;
		
		//인구 입력받기
		for (int i = 1; i <= N; i++) {
			baekjoonsi[i] = Integer.parseInt(st.nextToken());
			conn[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			//항상 for문을 확인해(i j 혼동조심).......................
			for (int j = 0; j < M; j++) {
				int B = Integer.parseInt(st.nextToken());
				conn[i].add(B);
				conn[B].add(i);

			}
		}
		area(0, 0);
		System.out.println(result);

	}

	// 선거구 나누기
	static void area(int idx, int sel) {
		if (idx == N) {
			//만약 모든 도시가 한 구역으로 몰리면 
			if (sel == N || sel == 0)
				return;
//			for(int i =1;i<=N;i++) {
//				System.out.print(isSelected[i] +" ");
//			}
//			System.out.println();
//			System.out.println(connected(true));
//			System.out.println(connected(false));
			
			//만약 두 구역 모두 연결되어있다면
			if (connected(true) + connected(false) == N) {

				int a = peoplenum();
				//Math.min을 썼을때 초기값이 가장 작은 값이라 조건
				if (result == -1) {
					result = a;
				} else {
					result = Math.min(result, a);
				}
			}
			return;
		}
		//백트래킹(부분집합)
		isSelected[idx + 1] = true;
		area(idx + 1, sel +1);
		isSelected[idx + 1] = false;
		area(idx + 1, sel);

	}

	// 두 지역구가 연결되어있는지 확인
	static int connected(boolean flag) {
		boolean[] check = new boolean[N + 1];
		Queue<Integer> queue = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			if (isSelected[i] == flag) {
				queue.add(i);
				//항상 로직도 확인해................................
				check[i] = true;
				break;
			}
		}
		
		//BFS
		while (!queue.isEmpty()) {
			int num = queue.poll();
			for (int A : conn[num]) {
				if (isSelected[A] == flag && !check[A]) {
					queue.add(A);
					check[A] = true;
				}

			}
		}
		int num = 0;
		for (int i = 1; i <= N; i++) {
			if (check[i] && isSelected[i] == flag) {
				num += 1;
			}
		}
		return num;
	}

	// 연결되어 있다면 둘의 인구차이 확인
	static int peoplenum() {
		int T = 0;
		int F = 0;
		for (int i = 1; i <= N; i++) {
			if (isSelected[i]) {
				T += baekjoonsi[i];
			} else {
				F += baekjoonsi[i];
			}
		}
		return Math.abs(T - F);
	}

}

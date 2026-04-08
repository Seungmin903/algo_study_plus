
import java.util.*;
import java.io.*;

public class BJ_2805_ssm_2 {
	static long[] tree;
	static int N;
	static long M;
	static long result = 0;
	static boolean isFound;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		
		
		st = new StringTokenizer(br.readLine());
		tree = new long[N];
		long maxN = 0;
		for(int i =0;i<N;i++) {
			//나무 높이 저장
			tree[i] = Integer.parseInt(st.nextToken());
			// 나무높이 중 가장 높은 값 저장
			maxN = Long.max(maxN, tree[i]);
		}
		//시작 : 0 / 끝 : 가장 높은 나무높이
		bin(0,maxN);
		System.out.println(result);
		
		
	}
	//이분 탐색 코드
	static void bin(long s, long e) {
		//만약 이미 찾았으면 return;
		if(isFound) {
			return;
		}
		// 만약 시작이 끝보다 크면 return
		if( s > e) {
			return;
		}
		// 현재 중간 값
		long m = (e-s)/2 + s;
		// 자른 나무 총합
		long t_num =0;
		
		for(int i =0;i<N;i++) {
			// 만약 자르려는 높이가 나무높이보다 크다면 continue
			if(tree[i] - m < 0) {
				continue;
			}
			// 아니라면 값 저장
			t_num += tree[i] - m;
		}
		// 만약 찾는 값이라면 
		if(t_num == M) {
			//is Found 활성화
			isFound = true;
			// 최대 높이 저장
			result = m;
			return;
		}
		// 만약 자른 나무의 합이 원하는 길이보다 크다면
		else if(t_num > M) {
			//우선 최대 길이 저장
			result = m;
			//다시 이분탐색
			bin(m+1,e);
		}
		//만약 원하는 길이보다 작으면
		else if(t_num < M) {
			//(저장 안해주는 이유는 원하는 길이에 못 미쳤으므로 최대높이가 되서는 안되기 때문에)
			// 이분탐색
			bin(s,m-1);
		}
	}
}

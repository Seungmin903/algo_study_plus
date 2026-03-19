package p0319;

/*
 * 시간 : 456ms
 * 메모리 : 167,852kb
 * 전략 : 이분탐색
 * 1. 나무 높이 저장 및 최대 높이 구하기
 * 2. 0과 maxH의 중간을 H로 설정하여 H보다 큰 나무들과의 차이의 합을 계산
 * 3. 2번의 결과가 M보다 크거나 같다면, 결과에 저장해두고 high 인덱스를 H+1로,
 * 	아니라면, low를 H-1로 설정하여 탐색 범위를 절반으로 줄이기
 * 4. low가 high이상이 될 때 탐색 종료 및 결과 출력
 */

import java.util.*;
import java.io.*;

public class BOJ2805 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int maxT = Integer.MIN_VALUE;
		int[] tree = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			maxT = Math.max(tree[i], maxT); 
		}
		
		int low = 0;
		int high = maxT;
		int result = maxT;
		while(low<=high) {
			long sumTree = 0;
			int H = (low + high)/2;  // 이분탐색
			for(int t : tree) {
				if(t >= H) {
					sumTree += t - H;					
				}
			}
			
			if(sumTree < M) {
				high = H - 1;
			}
			else if(sumTree >=M) {
				low = H + 1;
				result = H;
			}
			
		}

		System.out.println(result);
	}

}

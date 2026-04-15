package p0407;

/*
 * 시간 : 456ms
 * 메모리 : 16,7780kb
 * 전략 : 이진탐색
 * 1. 최대 나무 높이 구하기
 * 2. 0 부터 최대 나무 높이로 절단기의 높이를 이진 탐색으로 구하기
 * 2-1. 현재 절단기 높이로 구할 수 있는 나무의 높이가 M보다 높다면 이진 탐색 범위 높이기
 * 2-2. 현재 절단기 높이로 구할 수 있는 나무의 높이가 M보다 낮다면 이진 탐색 범위 낮추기
 * 3. left<=right를 만족하지 않을 때 H를 출력
 * 주의) sum은 long 형이어야 함
 */

import java.util.*;
import java.io.*;

public class BOJ2805 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] tree = new int[N];
		int maxH = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			maxH = Math.max(maxH, tree[i]);
		}
		
		int down = 0;
		int up = maxH;
		int res = -1;
		
		while(down<=up) {
			int H = (down+up)/2;
			long sum = 0;
			for(int t : tree) {
				if(t>H) {
					sum += t-H;
				}
			}

			if(sum>=M) {
				res = H;
				down = H + 1;
			}
			else if(sum<M) {
				up = H - 1;
			}
		}
		
		System.out.println(res);
	}

}

package p0406;

/*
 * 시간 : 101ms
 * 메모리 : 26,112kb
 * 전략 : 구현
 * 1. 최장 나무 높이와 나머지들의 차이를 계산해서 배열 저장
 * 2. 해당 배열 요소들을 2로 나눈 몫을 two 변수에, 나머지를 one 변수에 넣어서 필요한 2일, 1일의 수를 계산
 * 3. 2일을 줄여주어야 최소 일 수 계산 가능 -> one이 two-1 보다 작을 때는 재분배 진행
 * 	-> two가 1감소, one이 2 증가
 * 4. 최종 one, two에 따라 day 계산
 * 	-> one이 더 크면 one*2-1 / 그 외는 two*2
 * 
 * 추가) 이 코드는 diff 배열을 만들었는데, 만들지 않고 바로 차이 계산 + 차이에서 필요한 two와 one 구하기를 진행해주면
 *		메모리 절약 가능
 */

import java.util.*;
import java.io.*;

public class SWEA14510 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=TC; t++) {
			sb.append("#").append(t).append(" ");
			int N = Integer.parseInt(br.readLine());
			
			int[] tree = new int[N];
			int maxH = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				maxH = Math.max(maxH, tree[i]);
			}
			
			int[] diff = new int[N];
			for(int i=0; i<N; i++) {
				diff[i] = Math.abs(maxH-tree[i]);
			}
			
			int two = 0;
			int one = 0;
			for(int i=0; i<N; i++) {
				two += diff[i]/2;
				one += diff[i]%2;
			}
			
			while(one+1 < two) {
				two--;
				one+=2;
			}
			
			if(one>two) sb.append(one*2-1);
			else sb.append(two*2);
			sb.append("\n");
		}
		
		
		System.out.println(sb);
	}

}

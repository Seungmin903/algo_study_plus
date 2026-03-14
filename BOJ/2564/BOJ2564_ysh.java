package prepareSubjectTest;

/*
 * 시간 : 64ms
 * 메모리 : 11,608kb
 * 전략 : 구현
 * 1. 상점의 방향, 위치와 동근의 방향, 위치를 하나의 배열에 저장
 * 2. 사각형을 한 줄로 펼쳤다고 생각하고, 각 방향과 위치에 따른 거리 계산
 * 2-1. 이때, 2와 3의 기준점을 유의
 * 3. 첫번째 경로 : 동근이와 상점 사이의 거리 계산 - 절댓값을 이용
 * 4. 두번째 경로 : 3번에 구한 것을 전체(2*w + 2*y)에서 빼주기
 * 5. 3과 4에서 더 작은 값을 결과 배열에 저장
 * 6. 모든 결과 배열을 순회하며 합하기
 */

import java.io.*;
import java.util.*;

public class BOJ2564 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());

		int n = Integer.parseInt(br.readLine());

		int[] dir = new int[n+1];
		int[] dist = new int[n+1];
		for (int i = 0; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			// 1: 북, 2: 남, 3: 서, 4: 동
			dir[i] = Integer.parseInt(st.nextToken());
			dist[i] = Integer.parseInt(st.nextToken());
		}

		int[] result = new int[n];
		
		int one=0, two=0, dongDist = 0;
		for (int i = n; i >=0; i--) {
			int tmp = 0;
			
			// 핵심 : 한 줄로 펼쳐졌다고 생각!
			if (dir[i] == 1) {
				tmp = dist[i];
			}
			else if(dir[i] == 4) {
				tmp = w + dist[i];
			}
			else if(dir[i] == 2) {
				tmp = w + h + (w - dist[i]);  // 기준점 유의
			}
			else {
				tmp = w + h + w + (h - dist[i]);  // 기준점 유의
			}
			
			if(i==n) dongDist = tmp;
			
			// 동근이가 아닐 때는 거리 계산
			if(i<n) {
				one = Math.abs(dongDist-tmp);  // 첫번째 방향
				two = 2*w + 2*h - one;  // 두번째 방향
				
				result[i] = one < two ? one : two;  // 더 작은 값으로 저장
			}
		}
		
		int sum = 0;
		for(int r : result) {
			sum += r;
		}
		sb.append(sum).append("\n");
		System.out.println(sb);
	}

}

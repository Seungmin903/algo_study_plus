package prepareSubjectTest;

/*
 * 시간 : 2,062ms
 * 메모리 : 115,188kb
 * 전략 : 부분집합
 * 1. 점수, 칼로리를 하나의 클래스로 묶어 저장
 * 2. 0~n 집합의 부분 집합 생성 - (주의 : 공집합을 무시하는 것이 필요!)
 * 3. 생성한 부분집합을 인덱스로 하여 칼로리와 스코어 점수 저장
 * 4. 칼로리 합이 L을 넘기지 않을 때의 최대 점수 합을 출력
 */

import java.io.*;
import java.util.*;

public class SWEA5215 {
	public static class List{
		int score, cal;
		public List(int score, int cal) {
			this.score = score;
			this.cal = cal;
		}
	}
	
	static int N, L;
	static boolean[] selected;
	static List[] hambug;
	static ArrayList<Integer> choice;
	static int maxSum;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=TC; t++) {
			sb.append("#").append(t).append(" ");
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			hambug = new List[N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());				
				int score = Integer.parseInt(st.nextToken());
				int cal = Integer.parseInt(st.nextToken());
				
				hambug[i] = new List(score, cal);
			}
			
			selected = new boolean[N];
			choice = new ArrayList<>();
			maxSum = Integer.MIN_VALUE;
			combi(0);
			
			
			sb.append(maxSum).append("\n");
		}
		System.out.println(sb);
	}
	private static void combi(int cnt) {
		if(cnt == N) {
			for(int i=0; i<N; i++) {
				if(selected[i]) choice.add(i);
			}
			if(choice.size()!=0) calScore(choice);
			choice = new ArrayList<>();  // 초기화 필수!!!
			return;
		}
		
		selected[cnt] = true;
		combi(cnt + 1);
		
		selected[cnt] = false;
		combi(cnt + 1);
	}
	
	private static void calScore(ArrayList<Integer> arr) {
		int calSum = 0;
		int scSum = 0;
		
		for(int i=0 ; i<arr.size(); i++) {
			int idx = arr.get(i);
			
			calSum += hambug[idx].cal;
			scSum += hambug[idx].score;
			
			if(calSum>L) return;  // 제한 칼로리 넘으면 무시
		}
		
		// 조합의 칼로리가 L을 넘기지 않으면 갱신
		maxSum = Math.max(scSum, maxSum);
	}
}

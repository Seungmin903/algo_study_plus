//시간 : 109ms
//전략 : 완전 탐색
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CDFC_F_ssm {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			//총 숫자 개수
			int N = Integer.parseInt(st.nextToken());
			//수열 길이
			int Len = Integer.parseInt(st.nextToken());
			
			//숫자 저장 배열
			int[] arr = new int[N];
			//수열 길이만큼 미리 더한 배열
			long[] plus = new long[N-Len+1];
			st = new StringTokenizer(br.readLine());
			// 모든 숫자 입력받기
			for(int i =0; i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			// 수열길이만큼의 합을 저장하기
			for(int i =0;i<N-Len+1;i++) {
				for(int j=i; j< i+ Len;j++) {
					plus[i] += arr[j];
				}
			}
			//값 초기화
			long result = -30000000;
			
			for(int i =0;i<N-Len+1;i++) {
				//겹치지 않는 부분수열끼리 더한 값 중 최대값 저장
				for(int j = i+Len; j<N-Len+1;j++) {
					if(result < plus[i] + plus[j]) {
						result = plus[i] + plus[j];
					}
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

}

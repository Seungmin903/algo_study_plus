//시간 : 92ms
//메모리 :26,240 KB
// 설명 생략
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_14510_ssm {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc =1; tc<=T;tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			int[] tree = new int[N];
			int[] OT = new int[2];
			st = new StringTokenizer(br.readLine());
			int maxT = 0;
			for(int i =0;i<N;i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				maxT = Math.max(maxT, tree[i]);
			}
			for(int i =0;i<N;i++) {
				int height = maxT - tree[i];
				if(height == 0) continue;
				OT[1] += height /2 ;
				OT[0] += height % 2;
			}
//			System.out.println(OT[0] +" " + OT[1]);
			
			if(OT[0]+1 < OT[1]) {
				while(OT[0]+1 < OT[1]) {
					OT[1] -= 1;
					OT[0] += 2;
				}
			}
			int days =0;
			if(OT[0] < OT[1]) {
				days = OT[0] *2;
				days += (OT[1] - OT[0]) *2;
			}
			else if(OT[0] > OT[1]) {
				days = OT[1] * 2;
				days += (OT[0] - OT[1]) *2 -1;
			}
			else {
				days = OT[1] *2;
			}
			sb.append(days).append("\n");
		}
		System.out.println(sb.toString());
	}

}

package sm0309;

import java.io.*;
import java.util.*;

public class BOJ1717 {
	static int nums[];
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		nums = new int[N+1];
		for(int i=0; i<=N; i++) {
			nums[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(cmd == 0) union(a, b);
			else {
				if(find(a)==find(b)) sb.append("YES").append("\n");
				else sb.append("NO").append("\n");
			}
		}
		
		System.out.println(sb);
	}

	private static int find(int x) {
		if(nums[x] == x) return x;  // 본인이 루트라면 반환
		int rootN = find(nums[x]);
		nums[x] = rootN;
		return rootN;
	}

	private static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		
		nums[rootX] = rootY;
	}

}

//시간: 464ms
//메모리 : 171672 KB
//전략 : 이분 탐색

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_2805_ssm {
	static long[] tree;
	static long Tmax =0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		tree = new long[N];
		Tmax =0;
		st = new StringTokenizer(br.readLine());
		for(int i =0;i<N;i++) {
			tree[i] = Long.parseLong(st.nextToken());
			Tmax = Long.max(Tmax, tree[i]);
		}
		long result =0;
		long s =0, e = Tmax;
		long mid =0;
		
		while(s <= e) {
			mid = (s + e) /2;
			long temp =0;
			for(int i =0; i<N;i++	) {
				if(tree[i] - mid < 0) {
					continue;
				}
				temp += tree[i] - mid;					
			}
			if(temp >= M) {
				result = mid;
				s = mid+1;
			}
			else if(temp < M) {
				e = mid-1;
			}
		}
		System.out.println(result);
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1717_ssm {
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		for(int i =1; i<=N;i++ ) {
			arr[i] = i;
		}
		for(int i =0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(num == 0) {
				Union(a,b);
			}
			else {
				if(Find(a) == Find(b)) {
					sb.append("YES").append("\n");
				}
				else {
					sb.append("NO").append("\n");
				}
			}
		
		}
		System.out.println(sb.toString());

	}
	static void Union(int a, int b) {
		int Pa = Find(a);
		int Pb = Find(b);
		
		if(Pa == Pb) {
			return;
		}
		arr[Pb] = Pa;
	}
	
	static int Find(int x) {
		if(x == arr[x]) {
			return x;
		}
		return arr[x] = Find(arr[x]);
	}

}

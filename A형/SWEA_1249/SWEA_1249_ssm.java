//179 ms
//34,800 kb
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SWEA_1249_ssm {
	static PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[2], b[2]));
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<= T;tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			for(int i =0;i<N;i++) {
				String li = br.readLine();
				for(int j =0;j<N;j++) {
					map[i][j] = li.charAt(j) - '0';
				}
			}
			pq.add(new int[] {0,0,0});
			visited[0][0] = true;
			Dijk();
			pq.clear();
		}
		System.out.println(sb.toString());
	}
	static void Dijk() {
		while(!pq.isEmpty()) {
			int[] temp = pq.poll();
			int x = temp[0];
			int y = temp[1];
			int cost = temp[2];
			if(x == N-1 && y == N-1) {
				sb.append(cost).append("\n");
				break;
			}
			for(int i =0;i<4;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny <N && visited[nx][ny] == false) {
					pq.add(new int[] {nx,ny,cost + map[nx][ny]});
					visited[nx][ny] = true;
				}
			}
		}
	}

}
//#1 2
//#2 2
//#3 8
//#4 57
//#5 151
//#6 257
//#7 18
//#8 160
//#9 414
//#10 395

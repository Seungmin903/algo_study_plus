import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_5105_ssm {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static Queue<int[]> queue = new LinkedList<>();
	
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1}; 
	static int ex,ey;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i =0; i<N;i++) {
				String line = br.readLine();
				for(int j =0; j<N;j++) {
					map[i][j] = line.charAt(j) - '0';
					if(map[i][j] == 2) {
						queue.add(new int[] {i,j, 0});
						visited[i][j] = true;
					}
					if(map[i][j] == 3) {
						ex = i;
						ey = j;
					}
				}
			}
			
		int result = BFS();
		sb.append(result).append("\n");
		queue.clear();
		}
		System.out.println(sb.toString());
		
	}
	static int BFS() {
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x = temp[0];
			int y = temp[1];
			int len = temp[2];
			if(x == ex && y == ey) {
				return len -1 ;
			}
			for(int i = 0;i<4;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx >= 0 && nx < N && ny >=0 && ny < N && map[nx][ny] != 1 && !visited[nx][ny]) {
					queue.add(new int[] {nx,ny,len+1});
					visited[nx][ny] = true;
				}
			}
		}
		return 0;
	}

}

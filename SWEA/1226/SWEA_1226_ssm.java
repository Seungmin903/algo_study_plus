//시간 : 90ms
//메모리 : 25,984 kb

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1226_ssm {
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static boolean[][] visited;
	static int[][] map;
	static int ex,ey ;
	static Queue<int[]> queue = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc<=10; tc++	) {
			int T = Integer.parseInt(br.readLine());
			sb.append("#").append(T).append(" ");
			visited = new boolean[16][16];
			map = new int[16][16];
			queue = new LinkedList<>();
			
			for(int i =0;i<16;i++) {
				String line = br.readLine()	;
				for(int j =0;j<16;j++) {
					map[i][j] = line.charAt(j) - '0';
					if(map[i][j] == 2) {
						queue.add(new int[] {i,j});
						visited[i][j] = true;
					}
					if(map[i][j] == 3) {
						ex = i;
						ey = j;
					}
				}
			}
			sb.append(BFS()).append("\n");
			
		}
		System.out.println(sb.toString());
	}
	static int BFS() {
		while(!queue.isEmpty()) {
			int[] xy = queue.poll();
			int x = xy[0];
			int y = xy[1];
			if(x == ex && y == ey) {
				return 1;
			}
			for(int i =0; i<4;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx >= 0 && nx < 16 && ny >= 0 && ny < 16 && map[nx][ny] != 1 && !visited[nx][ny]) {
					queue.add(new int[] {nx,ny});
					visited[nx][ny] = true;
				}
			}
		}
		return 0;
	}

}


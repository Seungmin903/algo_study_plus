//**시간**: 84ms
//**메모리** :12728KB
//**전략** : BFS
//
//1. 그림내에 빨강과 초록이 이어진 경우, 적록색약은 한구역으로 보게 하고, 아닌사람은 각각 다른 구역으로 보게 한다
//2. 색약 아닌경우, 색약인 경우 각각 돌려 구역의 수 출력
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_10026_ssm {
	//그림
	static char[][] pic;
	//방문배열
	static boolean[][] Visited;
	
	static int N;
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	static Queue<int[]> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		pic = new char[N][N];
		Visited = new boolean[N][N];
		
		
		for(int i =0;i<N;i++) {
			String li = br.readLine();
			for(int j=0;j<N;j++) {
				pic[i][j] = li.charAt(j);
			}
		}
		
		int num =0;
		//적록색약 X
		for(int i =0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!Visited[i][j]) {
					num += 1;
					
					Visited[i][j] = true;
					queue.offer(new int[]{i,j});
					BFS(pic[i][j]);
				}
			}
		}
		sb.append(num).append(" ");
		num =0;
		Visited = new boolean[N][N];
		//적록색약 O
		for(int i =0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(!Visited[i][j]) {
					num += 1;
					
					Visited[i][j] = true;
					queue.offer(new int[]{i,j});
					CB_BFS(pic[i][j]);
				}
			}
		}
		sb.append(num);
		System.out.println(sb);
		
		
	}
	//적록색약 판단용 BFS
	static void CB_BFS(char C) {
		while(!queue.isEmpty()) {
			int[] xy = queue.poll();
			for(int i =0;i<4;i++) {
				int nx = xy[0] + dx[i];
				int ny = xy[1] + dy[i];
				if(nx >= 0 && nx <N && ny >= 0 && ny <N && !Visited[nx][ny]) {
					//만약 빨강 혹은 초록이라면
					if(C == 'R' || C == 'G') {
						//파랑 아닌 이어진 부분은 한 덩이로 판단
						if(pic[nx][ny] != 'B') {
							Visited[nx][ny] = true;
							queue.offer(new int[]{nx,ny});
						}
						
					}
					else {
						//파란색인 경우
						if(pic[nx][ny] == C) {
							Visited[nx][ny] = true;
							queue.offer(new int[]{nx,ny});
						}
					}				
				}
			}
		}
	}
	
	//적록색약 아닌 사람용 BFS
	static void BFS(char C) {
		while(!queue.isEmpty()) {
			int[] xy = queue.poll();
			for(int i =0;i<4;i++) {
				int nx = xy[0] + dx[i];
				int ny = xy[1] + dy[i];
				if(nx >= 0 && nx <N && ny >= 0 && ny <N && !Visited[nx][ny] && pic[nx][ny] == C) {
					Visited[nx][ny] = true;
					queue.offer(new int[]{nx,ny});
				}
			}
		}
	}

}

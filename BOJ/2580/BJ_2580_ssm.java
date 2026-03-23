//**시간**: 392ms
//**메모리** : 15876KB
//**전략** : 백트래킹
//
//1. 스도쿠 문제를 입력받으며 0의 위치를 list에 저장한다
//2. 0의 위치를 하나하나 방문하며 숫자 대입
//3. 대입하는 과정에서 만약 가로, 세로, 9칸 이내에 같은 수가 있다면 다른수로 넘어가기
//4. 만약 모든 0에 대해서 값을 찾았으면 결과 저장 및 출력
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_2580_ssm {
	//문제 입력받을 스도쿠 배열
	static int[][] sudoku = new int[9][9];

	//만약 답을 찾았다면  true;
	static boolean isFound;
	//자기 포함 8방탐색 및 8방탐색 시작할 중앙값 정의
	static int[] dx = {-1,-1,-1,0,0,0,1,1,1}, dy= {-1,0,1,-1,0,1,-1,0,1}, dnum = {1,4,7};
	static StringBuilder sb = new StringBuilder();
	//0 위치 저장할 list
	static List<grid> zero = new ArrayList<>();
	//좌표값 저장할 class
	static class grid{
		int x, y;

		public grid(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i =0;i<9;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0;j<9;j++) {
				sudoku[i][j] = Integer.parseInt(st.nextToken());
				if(sudoku[i][j] == 0) {
					zero.add(new grid(i,j));
				}
			}
		}
//		for(int i =0;i<zero.size();i++) {
//			System.out.println(zero.get(i).x +" " +zero.get(i).y);
//		}
		fill(0);
		System.out.println(sb.toString());
		
		
	}
	
	static void fill(int idx) {
		//만약 이미 찾았다면 계속 돌아가기
		if(isFound) return;
		//만약 0개수만큼 모두 채웠다면
		if(idx == zero.size()) {
			for(int i =0; i<9;i++) {
				for(int j =0; j<9;j++	) {
					//스도쿠 결과 입력
					sb.append(sudoku[i][j]).append(" ");
				}
				sb.append("\n");
			}
			isFound = true;
			return;
		}
		// 좌표값 입력받기
		grid temp = zero.get(idx);
		int x = temp.x;
		int y = temp.y;
		
		for(int i =1 ;i<=9;i++) {
			//가로 - 세로 - 9칸 내에 같은 숫자 있는지 확인
			if(hori(x,i) && vert(y,i) && sqar(dnum[x/3],dnum[y/3],i)) {
				// 없으면 스도쿠 채워넣고
				sudoku[x][y] = i;
				//다음 0으로 넘어가기
				fill(idx+1);
				//돌아오면 0으로 초기화
				sudoku[x][y] = 0;
			}
		}
	}
	static boolean hori(int x, int num) {
		for( int i =0;i<9;i++) {
			//같은 숫자가 이미 있다면 false
			if(sudoku[x][i] == num) return false;
		}
		return true;
	}
	
	static boolean vert(int y, int num) {
		
		for( int i =0;i<9;i++) {
			//같은 숫자가 이미 있다면 false
			if(sudoku[i][y] == num) return false;
		}
		return true;
	}
	
	static boolean sqar(int x, int y, int num) {
		for(int i =0; i<9;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			//같은 숫자가 이미 있다면 false
			if(sudoku[nx][ny] == num) {
				return false;
			}
		}
		return true;
	}
}

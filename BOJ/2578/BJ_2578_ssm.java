//시간 : 64ms
// 메모리 : 11548KB
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_2578_ssm {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] bingo = new int[5][5];
		boolean isBingo = false;
		boolean[][] isCalled = new boolean[5][5];
		
		int result = 0;
		
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				bingo[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int idx = 0;
		
		st = new StringTokenizer(br.readLine());
		
		while (!isBingo) {
			int bNum = 0;
			idx += 1;
			
			int num = Integer.parseInt(st.nextToken());
			
			find: for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (bingo[i][j] == num) {
						isCalled[i][j] = true;
						break find;
					}
				}
			}
			
			if (idx >= 10) {
				// 가로
				for (int i = 0; i < 5; i++) {
					int n = 0;
					for (int j = 0; j < 5; j++) {
						if (isCalled[i][j] == true) {
							n += 1;
						}
					}
					if (n == 5) {
						bNum += 1;
					}
				}
				// 세로
				for (int i = 0; i < 5; i++) {
					int n = 0;
					for (int j = 0; j < 5; j++) {
						if (isCalled[j][i] == true) {
							n += 1;
						}
					}
					if (n == 5) {
						bNum += 1;
					}
				}
				// 대각 1
				int n = 0;
				for (int i = 0; i < 5; i++) {
					if (isCalled[i][i] == true) {
						n += 1;
					}
					if (n == 5) {
						bNum += 1;
					}
				}
				// 대각 2
				n = 0;
				for (int i = 0; i < 5; i++) {
					if (isCalled[i][4 - i] == true) {
						n += 1;
					}
					if (n == 5) {
						bNum += 1;
					}
				}
			}
			if (bNum >= 3) {
				isBingo = true;
				result = idx;
			}

			if (idx % 5 == 0 && idx < 25) {
				st = new StringTokenizer(br.readLine());
			}
		}
		
		System.out.println(result);

	}

}

package ken.codejam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;
import ken.codejam.utils.FirstLineNumOfTCProblem;

/**
 * Problem

Minesweeper is a computer game that became popular in the 1980s, and is still included in some versions of the Microsoft Windows operating system. This problem has a similar idea, but it does not assume you have played Minesweeper.

In this problem, you are playing a game on a grid of identical cells. The content of each cell is initially hidden. There are M mines hidden in M different cells of the grid. No other cells contain mines. You may click on any cell to reveal it. If the revealed cell contains a mine, then the game is over, and you lose. Otherwise, the revealed cell will contain a digit between 0 and 8, inclusive, which corresponds to the number of neighboring cells that contain mines. Two cells are neighbors if they share a corner or an edge. Additionally, if the revealed cell contains a 0, then all of the neighbors of the revealed cell are automatically revealed as well, recursively. When all the cells that don't contain mines have been revealed, the game ends, and you win.

For example, an initial configuration of the board may look like this ('*' denotes a mine, and 'c' is the first clicked cell):

*..*...**.
....*.....
..c..*....
........*.
..........
There are no mines adjacent to the clicked cell, so when it is revealed, it becomes a 0, and its 8 adjacent cells are revealed as well. This process continues, resulting in the following board:
*..*...**.
1112*.....
00012*....
00001111*.
00000001..
At this point, there are still un-revealed cells that do not contain mines (denoted by '.' characters), so the player has to click again in order to continue the game.
You want to win the game as quickly as possible. There is nothing quicker than winning in one click. Given the size of the board (R x C) and the number of hidden mines M, is it possible (however unlikely) to win in one click? You may choose where you click. If it is possible, then print any valid mine configuration and the coordinates of your click, following the specifications in the Output section. Otherwise, print "Impossible".

Input

The first line of the input gives the number of test cases, T. T lines follow. Each line contains three space-separated integers: R, C, and M.

Output

For each test case, output a line containing "Case #x:", where x is the test case number (starting from 1). On the following R lines, output the board configuration with C characters per line, using '.' to represent an empty cell, '*' to represent a cell that contains a mine, and 'c' to represent the clicked cell.

If there is no possible configuration, then instead of the grid, output a line with "Impossible" instead. If there are multiple possible configurations, output any one of them.

Limits

0 ≤ M < R * C.
Small dataset

1 ≤ T ≤ 230.
1 ≤ R, C ≤ 5.
Large dataset

1 ≤ T ≤ 140.
1 ≤ R, C ≤ 50.
Sample
5
5 5 23
3 1 1
2 2 1
4 7 3
10 10 82
 * @author khanhnq2
 *
 */
public class Minesweeper extends AutoParseInputProblem{

	public Minesweeper() {
		super(new MineTestCase());
	}

	public static void main(String[] args) {
		new Minesweeper().start();
	}
	
	public static class MineTestCase extends AutoParseTestCase {
		public Integer R, C, M;
		public MineTestCase() {
			super("R C M");
		}
		@Override
		public void process(int order, BufferedWriter output) {
			
			int[][] result = new int[R][C];
			for (int i = 0; i < R; i ++){
				for (int j = 0; j < C; j ++){
					result[i][j] = 1;//no bomb 
				}
			}
			result[R-1][C-1] = 0;//start
			
			if (M == 0){
				printResult(order, output, result);
				return;
			}
			
			int S = R > C ? C : R;
//			int L = R > C ? R : C;
			if (S == 1 || R * C - M == 1){
				for (int i = 0; i < R; i ++){
					for (int j = 0; j < C; j ++){
						result[i][j] = 2;//bomb
						M--;
						if (M == 0){
							printResult(order, output, result);
							return;
						}
					}
				}
				printResult(order, output, result);
				return;
			}
			
			for (int i = 0; i < R - 2; i ++){
				for (int j = 0; j < C - 2; j ++){
					if (M == 0) break;
					result[i][j] = 2;//bomb
					M--;
				}
			}
			int i = 0;
			int j = 0;
			for (int k = 0; k < 2; k ++){
				for (; i < R - 3 + k; i ++){
					if (M < 2) break;
					result[i][C - 2] = 2;
					result[i][C - 1] = 2;
					M -= 2;
				}
				for (; j < C - 3 + k; j ++){
					if (M < 2) break;
					result[R-2][j] = 2;
					result[R-1][j] = 2;
					M -= 2;
				}
			}
			if (M == 1){
				if (i < R - 3 && j < C - 2){
					result[i][C - 2] = 2;
					result[i][C - 1] = 2;
					result[R-3][C-3] = 1;
					M --;
				} else if (i < R - 2 && j < C - 3){
					result[R-2][j] = 2;
					result[R-1][j] = 2;
					result[R-3][C-3] = 1;
					M --;
				}
			} 
			
			if (M == 0){
				printResult(order, output, result);
			} else {
				printImpossible(order, output);
//				printResult(order, output, result);
			}
			
		}
		
		boolean markBombTo(int[][] result, int i, int j){
			if (M == 0){
				return true;
			}
			if (result[i][j] == 2) return true;
			
			
			
			result[i][j] = 2;//bomb
			M --;
			if (markBombTo(result, i + 1, j) && markBombTo(result, i, j + 1)){
				if (M == 0){
					return true;
				}
			}
			return false;
		}
		
		void printImpossible(int order, BufferedWriter output){
			print(order, output, "\nImpossible");
		}
		
		void printResult(int order, BufferedWriter output, int[][] result){
			StringBuilder builder = new StringBuilder();
//			builder.append(M);
			for (int i = 0; i < R; i ++){
				builder.append("\n");
				for (int j = 0; j < C; j ++){
					switch (result[i][j]) {
					case 0:
						builder.append('c');
						break;
					case 1:
						builder.append('.');
						break;
					case 2:
						builder.append('*');
						break;
					default:
						break;
					}
				}
			}
			print(order, output, builder.toString());
		}
		@Override
		public void clear() {
			
		}
		
		public void print(int order, BufferedWriter output, String mes){
			try {
				output.write("Case #" + order + ":" + mes + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}

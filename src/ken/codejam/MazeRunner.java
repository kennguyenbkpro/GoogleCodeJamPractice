package ken.codejam;

import java.util.ArrayList;
import java.util.Random;


public class MazeRunner {
	public static void main(String[] args) {
		int[][] maze= createMatrix(15, 27);
		generateByPrim(maze);
		print(maze);
	}
	
	private static void print(int[][] maze){
		for (int i = 0; i < maze.length; i ++){
			for (int j = 0; j < maze[i].length; j ++){
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	private static void generateByPrim(int[][] maze){
		ArrayList<Wall> listWall = new ArrayList<>();
		Random random = new Random();
		int rStart = random.nextInt(maze.length/3) + maze.length/3;
		maze[rStart][0] = MAZE_START;
		maze[rStart][1] = MAZE_ROAD;
		ArrayList<Wall> listStartWall = getListWall(maze, new Cell(rStart, 1));
		setWallsVisited(listStartWall, maze);
		listWall.addAll(listStartWall);
		
		while (!listWall.isEmpty()){
			Wall wall = listWall.get(random.nextInt(listWall.size()));
			Cell nextCell = wall.getNext();
			try {
				if (maze[nextCell.x][nextCell.y] == MAZE_WALL){
					maze[nextCell.x][nextCell.y] = MAZE_ROAD;
					maze[wall.x][wall.y] = MAZE_ROAD;
					ArrayList<Wall> listTempWall = getListWall(maze, nextCell);
					setWallsVisited(listTempWall, maze);
					listWall.addAll(listTempWall);
				}
			} catch (IndexOutOfBoundsException e){} 
			listWall.remove(wall);
		}
		for (int i = 1; i < maze.length - 1; i ++){
			int j = maze[i].length - 2;
			if (maze[i][j] == MAZE_ROAD){
				maze[i][j + 1] = MAZE_FINISH;
				return;
			}
			
		}
		for (int i = maze.length - 2; i > 0; i --){
			int j = maze[i].length - 3;
			if (maze[i][j] == MAZE_ROAD){
				maze[i][j + 1] = MAZE_ROAD;
				maze[i][j + 2] = MAZE_FINISH;
				return;
			}
		}
		
	}
	
	private static ArrayList<Cell> getAdjacentPartOfMaze(int[][] maze, Cell cell){
		ArrayList<Cell> listMazeAdj = new ArrayList<>();
		for (int i = 0; i < 4; i ++){
			Cell adjCell = cell.getAdjacent(i);
			try {
				if (maze[adjCell.x][adjCell.y] == MAZE_ROAD){
					listMazeAdj.add(cell);
				}
			} catch (IndexOutOfBoundsException | NullPointerException e){}
		}
		return listMazeAdj;
	}
	
	private static ArrayList<Wall> getListWall(int[][] maze, Cell cell){
		ArrayList<Wall> listWall = new ArrayList<>();
		for (int i = 0; i < 4; i ++){
			Cell adjCell = cell.getAdjacent(i);
			try {
				if (maze[adjCell.x][adjCell.y] == MAZE_WALL){
					listWall.add(new Wall(adjCell.x, adjCell.y, i));
				}
			} catch (IndexOutOfBoundsException | NullPointerException e){}
		}
		return listWall;
	}
	
	private static void setWallsVisited(ArrayList<Wall> listWalls, int[][] maze){
		for (Wall wall : listWalls){
			try {
				maze[wall.x][wall.y] = MAZE_WALL_VISITED;
			}catch (IndexOutOfBoundsException e){}
		}
	}
	
	
	
	public static class Cell {
		public int x, y;

		public Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Cell getAdjacent(int direction){
			if (direction == 0){
				return new Cell(x, y - 1);
			} else if (direction == 1){
				return new Cell(x + 1, y);
			} else if (direction == 2){
				return new Cell(x, y + 1);
			} else if (direction == 3){
				return new Cell(x - 1, y);
			}
			return null;
		}
	}

	
	public static class Wall extends Cell{
		public Wall(int x, int y, int sourceDirection) {
			super(x, y);
			this.sourceDirection = sourceDirection;
		}

		public int sourceDirection;
		
		public Cell getNext(){
			return getAdjacent(sourceDirection);
		}
	}
	
	private static final int MAZE_BOUND = 0;
	private static final int MAZE_WALL = 1;
	private static final int MAZE_WALL_VISITED = 9;
	private static final int MAZE_START = 2;
	private static final int MAZE_ROAD = 3;
	private static final int MAZE_ESCAPE = 4;
	private static final int MAZE_FINISH = 5;
	
	private static int[][] createMatrix(int w, int h){
		int [][] maze = new int[w][h];
		Random r = new Random();
		for (int i = 0; i < w; i ++){
			for (int j = 0; j < h; j ++){
				if (i == 0 || j == 0 || i == w -1 || j == h -1){
					maze[i][j] = MAZE_BOUND;
				} else {
					maze[i][j] = MAZE_WALL;
				}
			}
		}
		return maze;
	}
}

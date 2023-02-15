package graph.maze;

/**
 * 미로 그래프 작성 클래스
 * @author UserK
 *
 */
public class Maze
{
	protected int mazeSize; // 미로의 크기
	protected int min; // 경로의 최소값 설정 (최초 설정 값은 미로의 최대 탐색 경로로 설정)
	protected int goalX, goalY; // 목표 정점의 x, y 좌표
	protected int searchCnt; // 미로 탐색 회차
	protected String[][] maze; // 미로를 생성할 2차원 배열

	public Maze(int mazeSize, int x, int y)
	{
		this.mazeSize = mazeSize;
		this.min = mazeSize * mazeSize;
		this.goalX = x;
		this.goalY = y;
		this.searchCnt = 0;
		this.maze = new String[mazeSize][mazeSize];
	}

	public void printMaze()
	{

		for (int i = 0; i < maze.length; i++)
		{
			for (int j = 0; j < maze[i].length; j++)
			{
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void dfsMazeSearch(int y, int x, int n)
	{
		// 목표 지점에 도달한 경우 
		if(goalX == x && goalY == y) {		
			searchCnt++;
			System.out.println(searchCnt + "차 미로 탐색 종료");
			
			maze[y][x] = "*";
			
			printMaze();
			
			if(min > n) {
				min = n;
			}
			
			// 되돌아갈 경우
            // 방문했던 좌표를 방문가능한 상태로 되돌리기 위해 '□'을 값으로 설정
            maze[y][x] = "□";
            System.out.print("back ");
            return;
		}
		
		maze[y][x] = "*";
		
		// 위로 이동
        // y축 좌표가 0보다 크고, 이동할 좌표의 값이 '*'과 '■'이 아닌 경우
        if (y > 0 && !maze[y-1][x].equals("*") && !maze[y-1][x].equals("■")) {
            System.out.print("↑ up ");
            dfsMazeSearch(y-1, x, n+1);
        }
        // 아래로 이동
        // y축 좌표가 미로의 세로 길이보다 작고, 이동할 좌표의 값이 '*'과 '■'이 아닌 경우
        if (y < mazeSize-1 && !maze[y+1][x].equals("*") && !maze[y+1][x].equals("■")) {
            System.out.print("↓ down ");
            dfsMazeSearch(y+1, x, n+1);
        }
        // 왼쪽으로 이동
        // x축 좌표가 0보다 크고, 이동할 좌표의 값이 '*'과 '■'이 아닌 경우
        if (x > 0 && !maze[y][x-1].equals("*") && !maze[y][x-1].equals("■")) {
            System.out.print("← left ");
            dfsMazeSearch(y, x-1, n+1);
        }
        // 오른쪽으로 이동
        // x축 좌표가 미로의 가로 길이보다 작고, 이동할 좌표의 값이 '*'과 '■'이 아닌 경우
        if (x < mazeSize-1 && !maze[y][x+1].equals("*") && !maze[y][x+1].equals("■")) {
            System.out.print("→ right ");
            dfsMazeSearch(y, x+1, n+1);
        }
        
        // 되돌아갈 경우
        // 방문했던 좌표를 방문가능한 상태로 되돌리기 위해 '□'을 값으로 설정
        maze[y][x] = "□";
        System.out.print("back ");
        return ;
	}
}

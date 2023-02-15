package graph.maze;

public class MazeMain
{

	public static void main(String[] args)
	{
		int mazeSize = 7;
		Maze dfsGraph = new Maze(mazeSize, 6, 5); // 맵 크기, 목표 정점 
		
		// 미로 생성 
		for (int i = 0; i < mazeSize; i++)
		{
			for (int j = 0; j < mazeSize; j++)
			{
				dfsGraph.maze[i][j] = (i == 0 || i == dfsGraph.mazeSize - 1 || j == 0 || j == dfsGraph.mazeSize - 1) ? "■" : "□";
			}
		}

		System.out.println("1) 미로 생성 : ");
		dfsGraph.printMaze();
		
		// 미로에서 이동이 불가능한 좌표 설정
        dfsGraph.maze[1][0] = "□";
        dfsGraph.maze[5][6] = "□";
        dfsGraph.maze[2][1] = "■";
        dfsGraph.maze[2][2] = "■";
        dfsGraph.maze[2][3] = "■";
        dfsGraph.maze[2][4] = "■";
        dfsGraph.maze[4][2] = "■";
        dfsGraph.maze[4][4] = "■";
        dfsGraph.maze[4][5] = "■";
		
        System.out.println("2) 초기 생성 미로 : ");
        dfsGraph.printMaze();
        
        dfsGraph.dfsMazeSearch(1, 0, 1);
        
        System.out.println("최단 경로 값 : " + dfsGraph.min);
	}

}

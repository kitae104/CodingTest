package utils.tree;

import java.util.Scanner;

public class DFS_Test
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
        int V = 8; 		//sc.nextInt();    // 정점의 수 
        int E = 10;		//sc.nextInt();    // 간선의 수

        DfsGraph dfsGraph = new DfsGraph(V);
        
        dfsGraph.put(1, 2);
        dfsGraph.put(1, 3);
        dfsGraph.put(2, 4);
        dfsGraph.put(2, 5);
        dfsGraph.put(3, 6);
        dfsGraph.put(3, 7);
        dfsGraph.put(4, 8);
        dfsGraph.put(5, 8);
        dfsGraph.put(6, 8);
        dfsGraph.put(7, 8);
        
        // 정점과 간선 수동 입력
        /*for(int i=0; i<nE; i++) {
            v = sc.nextInt();
            e = sc.nextInt();
            dfsGraph.put(v, e);
        }*/
        
        sc.close();
        
        // 입력한 정점과 간선으로 구성된 인접행렬 출력
        dfsGraph.printGraphToAdjArr();
        
        System.out.print("정점 1부터 탐색(재귀 사용) : ");
        dfsGraph.dfs(1);
        System.out.println();
                
        dfsGraph.clearVisitArr();					// 방문 정보 제거 
        System.out.print("정점 1부터 탐색(스택 사용) : ");
        dfsGraph.dfsStack(1);
	}

}

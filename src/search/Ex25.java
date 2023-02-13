package search;

import utils.tree.DfsGraphFriends;

/**
 * Day-25 ABCDE
 * @author kitae
 *
 */
public class Ex25
{
	static int ans = 0;
	public static void main(String[] args)
	{
		int N = 5;			// 사람 수 
		int M = 4;			// 관계 수 
		
		DfsGraphFriends dfsGraph = new DfsGraphFriends(N, M);
		dfsGraph.put(0, 1);
		dfsGraph.put(1, 2);
		dfsGraph.put(2, 3);
		dfsGraph.put(3, 4);
		
		// 입력한 정점과 간선으로 구성된 인접행렬 출력
        dfsGraph.printGraphToAdjArr();
		
		System.out.print("정점 0부터 탐색(재귀 사용) : ");
		dfsGraph.dfs(0);
		dfsGraph.setAns(0);
		
		for(int i=0; i<N; i++) {
    		if(dfsGraph.getFriends_num()[i] == 0) continue;
    		dfsGraph.DFS_F(i,N,1);
    		if(dfsGraph.getAns() == 1) break;
    	}
		System.out.println();
    	System.out.println(dfsGraph.getAns());				
	}

}

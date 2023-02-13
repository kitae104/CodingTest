package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import utils.tree.DfsGraphFriends;

/**
 * Day-25_1 ABCDE 
 * 파일로 처리하기  
 * @author kitae
 *
 */
public class Ex25_1 
{
	static int ans = 0;
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("src/search/data4.txt"));
		String line = null;
		String[] input = null;
		
		 
		line = sc.nextLine();
		input = line.split(" ");
		
		int N = Integer.parseInt(input[0]);		// 정점 갯수 
		int M = Integer.parseInt(input[1]);		// 관계 수  
						
		DfsGraphFriends dfsGraph = new DfsGraphFriends(N, M);
		
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			
			input = line.split(" ");
			int v1 = Integer.parseInt(input[0]);		
			int v2 = Integer.parseInt(input[1]);
		
			dfsGraph.put(v1, v2);
		}		
		
		// 입력한 정점과 간선으로 구성된 인접행렬 출력
        dfsGraph.printGraphToAdjArr();
		
		System.out.print("정점 0부터 탐색(재귀 사용) : ");
		dfsGraph.dfs(0);
		
		// 값 초기화 
		dfsGraph.setAns(0);
		
		for(int i=0; i<N; i++) {
    		if(dfsGraph.getFriends_num()[i] == 0) continue;
    		dfsGraph.DFS_F(i,N,1);
    		if(dfsGraph.getAns() == 1) break;
    	}
		System.out.println();
    	System.out.println(dfsGraph.getAns());		// 결과 확인 				
	}

}

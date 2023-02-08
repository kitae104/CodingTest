package sort;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Day-23 연결 요소의 개수
 * @author kitae
 *
 */
public class Ex23
{
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(new File("src/sort/data3.txt"));
		String line = null;
		String[] input = null;
		
		 
		line = sc.nextLine();
		input = line.split(" ");
		
		int N = Integer.parseInt(input[0]);		// 정점 갯수 
		int M = Integer.parseInt(input[1]);		// 간선 수 
		
		visited = new boolean[N + 1];
		
		for (int i = 0; i <= N; i++)				// 정정 설정 
		{
			graph.add(new ArrayList<>());
		}
		
		while(sc.hasNextLine()) {
			line = sc.nextLine();
			
			input = line.split(" ");
			int v1 = Integer.parseInt(input[0]);		
			int v2 = Integer.parseInt(input[1]);
			
			graph.get(v1).add(v2);					// 양방향 간선 설정 
			graph.get(v2).add(v1);
		}
		
		int count = 0;
		for (int i = 1; i <= N; i++) 
		{
			if(dfs(i)){				
				count++;
				System.out.println();
			}
		}
		System.out.println(count);		
	}

	private static boolean dfs(int x)
	{
		if(visited[x]) {
            return false;
        }else {
        	visited[x] = true;
            int size = graph.get(x).size();
            for (int i = 0; i < size; i++) {
                int value = graph.get(x).get(i);
                System.out.print(value + " ");
                if(!visited[value]){
                    dfs(value);
                }
            }
            return true;
        }
	}

}

package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Day-28 트리의 지름 
 * - 트리에서 가장 먼 정점 사이의 거리를 구하기 
 * - 깊이 우선 탐색으로 처리
 * 
 * @author kitae
 */
public class Ex28 {

	static ArrayList<Node>[] list;		// 리스트 
	static boolean[] visited;			// 방문 여부 
	static int max = 0;					// 최대값 
	static int node;					// 노드 

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc = new Scanner(new File("src/search/ex28_data.txt"));
		
		String line = null;
		String[] input = null;
		
		line = sc.nextLine();
		input = line.split(" ");
		
		int n = Integer.parseInt(input[0]); 		// 정점의 갯수

		list = new ArrayList[n + 1];

		for (int i = 1; i < n + 1; i++) {
			list[i] = new ArrayList<>();			// 리스트 생성 
		}

		int cnt = 0; 
		// 트리 만들기
		for (int i = 0; i < n; i++) {
			cnt = 0;
			line = sc.nextLine();
			input = line.split(" ");
			
			int s = Integer.parseInt(input[cnt++]);			// 현재 노드 
			
			while (true) {
				int e = Integer.parseInt(input[cnt++]);		// 연결된 정점 
				if (e == -1) {
					break;
				}
				int cost = Integer.parseInt(input[cnt++]);	// 정점과의 거리 
				list[s].add(new Node(e, cost));				// 리스트의 현재 위치에 노드를 추가
			}
		}

		// 임의의 노드(1)에서 부터 가장 먼 노드를 찾는다. 이때 찾은 노드를 node에 저장한다.
		visited = new boolean[n + 1];						
		dfs(1, 0);

		// node에서 부터 가장 먼 노트까지의 거리를 구한다.
		visited = new boolean[n + 1];
		dfs(node, 0);

		System.out.println(max);
	}

	/**
	 * 깊이 우선 탐색
	 * 
	 * @param x
	 * @param len
	 */
	private static void dfs(int x, int len) {
		if (len > max) {
			max = len;
			node = x;
		}
		
		visited[x] = true;								// x 정점을 방문으로 표시 
		
		for (int i = 0; i < list[x].size(); i++) {		// 해당 정점에 연결된 리스트 체크 
			Node n = list[x].get(i);
			if(visited[n.e] == false) {					// 방문한 적이 없다면 
				dfs(n.e, n.cost + len);					// 다시 깊이 우선 탐색으로 이전까지 길이에 현재 길이 추가 
				visited[n.e] = true;					// 연결된 정점을 방문함으로 표시 
			}
		}
	}
}

/**
 * 노드 클래스 
 * @author kitae
 *
 */
class Node {
	protected int e;		// 이웃 정점 
	protected int cost;		// 비용 

	public Node(int e, int cost) {
		this.e = e;
		this.cost = cost;
	}
}
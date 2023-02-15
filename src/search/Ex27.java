package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Day-27 미로 탐색
 */
public class Ex27 {

    static int[][] map;
    static boolean[][] visited;

    static int minVal;

    static int N = 4;
    static int M = 6;
    
    static int[] dx = { -1, 1, 0, 0 }; 	// x방향 배열 - 상하
	static int[] dy = { 0, 0, -1, 1 }; 	// y방향 배열 - 좌우
	
    public static void main(String[] args) throws FileNotFoundException {

        map = new int[N][M];        // 지도
        visited = new boolean[N][M];    // 방문 여부
        visited[0][0] = true;

        Scanner sc = new Scanner(new File("src/search/map.txt"));
        for (int i = 0; i < N; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
        printArray(map);

        minVal = Integer.MAX_VALUE;

//        dfs(0, 0, 1);
//        System.out.println(minVal);
        
        bfs(0, 0);
        System.out.println(map[N-1][M-1]);

        printArray(visited);
    }

    /**
     * 너비 우선 탐색으로 처리하기
     * @param x
     * @param y
     */
    private static void bfs(int x, int y) {
    	Queue<int[]> q = new LinkedList<>();		// 큐 생성 
    	q.add(new int[] {x, y});					// 받아온 x y 값으로 배열을 만들어 큐에 추가 
    	
    	while(!q.isEmpty()) {						// 큐가 빌 때까지 처리  
    		int[] now = q.poll();					// 처음에 있는 내용 가져옴
    		int nowX = now[0];
    		int nowY = now[1];
    		
    		for(int i=0; i<4; i++) {
    			int nextX = nowX + dx[i];
    			int nextY = nowY + dy[i];
    			
    			if(nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
    				continue;
    			}
    			if(visited[nextX][nextY] || map[nextX][nextY] == 0) {
    				continue;
    			}
    			
    			q.add(new int[] {nextX, nextY});
    			map[nextX][nextY] = map[nowX][nowY] + 1;
    			visited[nextX][nextY] = true;
    		}
    	}
    }

    /**
     * 깊이 우선 탐색으로 처리하기
     * @param x
     * @param y
     * @param count
     */
    private static void dfs(int x, int y, int count) {
        if(x == N-1 && y == M-1) {
            minVal = Math.min(minVal, count);
            return;
        }

        if(count > minVal) {
            return;
        }

        //방향배열 사용하지 않고 조건문으로 4가지를 나누기
        if(x > 0 && !visited[x-1][y] && map[x-1][y] == 1) { //상
            visited[x-1][y] = true;
            dfs(x-1, y, count + 1);
            visited[x-1][y] = false;
        }
        if(x < N-1 && !visited[x+1][y] && map[x+1][y] == 1) { //하
            visited[x+1][y] = true;
            dfs(x+1, y, count + 1);
            visited[x+1][y] = false;
        }
        if(y > 0 && !visited[x][y-1] && map[x][y-1] == 1) { //좌
            visited[x][y-1] = true;
            dfs(x, y-1, count + 1);
            visited[x][y-1] = false;
        }
        if(y < M-1 && !visited[x][y+1] && map[x][y+1] == 1) { //우
            visited[x][y+1] = true;
            dfs(x, y+1, count + 1);
            visited[x][y+1] = false;
        }
    }

    private static void printArray(int[][] map) {
        // 내용 확인
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private static void printArray(boolean[][] map) {
        // 내용 확인
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

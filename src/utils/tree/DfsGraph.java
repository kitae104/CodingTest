package utils.tree;

import java.util.Stack;

/**
 * 인접 행렬을 이용 
 * @author kitae
 *
 */
public class DfsGraph
{
	private int num;    			// 정점의 개수
    protected int[][] dfsGraph;    	// 그래프
    private boolean[] visitArr;    	// 정점 방문 여부 확인 배열

    /**
     *  생성자 - 그래프 초기화
     * @param num
     */
    public DfsGraph(int num) {
        this.num = num;
        
        // 그래프 초기화(2차원 행렬)        
        this.dfsGraph = new int[num+1][num+1];
        
        // 정점 방문 여부 확인 배열 초기화        
        this.visitArr = new boolean[num+1];
    }
    
    /**
     * 그래프 return
     * @return
     */
    public int[][] getGraph() {
        return dfsGraph;
    }
    
    /**
     * 그래프 추가 (양방향)
     * @param x
     * @param y
     */
    public void put(int x, int y) {
        // 정점 x와 y가 연결되어있음을 의미
        dfsGraph[x][y] = dfsGraph[y][x] = 1;
    }
    
    /**
     * 그래프 추가 (단방향)
     * @param x
     * @param y
     */
    public void putSingle(int x, int y) {
        this.dfsGraph[x][y] = 1;
    }
    
    /**
     *  그래프 출력 (인접행렬)
     */
    public void printGraphToAdjArr() {
        for(int i = 0; i < dfsGraph.length; i++) {
            for(int j = 0; j < dfsGraph[i].length; j++) {
                System.out.print(" " + dfsGraph[i][j]);
            }
            System.out.println();
        }
    }
    
    /**
     * 방문 상태 출력
     */
    public void printVisitArr() {
        for(int i=0; i < visitArr.length; i++) {
        	System.out.print(" " + visitArr[i]);
        }
        System.out.println();
    }
    
    /**
     *  정점 방문 여부 확인 배열 초기화
     */
    public void clearVisitArr() {
        for(int i=0; i < visitArr.length; i++) {
            visitArr[i] = false;
        }
    }
    
    /**
     *  그래프 탐색 (재귀호출)
     * @param index
     */
    public void dfs(int index) {        
        
    	// 방문배열의 해당 index값을 true로 바꿔주고 값을 출력함.
        visitArr[index] = true;
        System.out.print(index + " "); 
        //printVisitArr();
        // 인접 행렬로 구현된 그래프에서
        // 정점의 개수(num) 만큼 탐색
        for(int i=1; i<= num; i++) {
            
            // 방문 배열에서 방문하지 않은 상태(false)인 경우 - 조건 확인!!
            if(dfsGraph[index][i] == 1 && visitArr[i] == false) {
                dfs(i);    
            }
        }
    } 
    
    public void dfsStack(int index) {
    	visitArr[index] = true;
        System.out.print(index + " ");
        
        Stack<Integer> stack = new Stack<>();
        stack.push(index);
        //System.out.println(stack);
        
        while(!stack.isEmpty()) {
        	int now = stack.peek();        	
        	boolean hasNearNode = false;		// 방문하지 않은 인접 노드가 있는지 확인
        	        	
        	for(int i=1; i<= num; i++) {
        		if(dfsGraph[now][i] == 1 && visitArr[i] == false) {  // 인접하고 방문 안한 경우 
        			stack.push(i);
        			System.out.print(i + " ");
        			//System.out.println(stack);
        			visitArr[i] = true;
        			hasNearNode = true;					
					break;
        		}
        	}
        	if(!hasNearNode) {
        		stack.pop();
        		//System.out.println(stack);
        	}
        }
    }
}

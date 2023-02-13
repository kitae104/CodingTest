package utils.tree;

import java.util.Stack;

/**
 * 기존 DfsGraph를 상속 받아 관계를 표현하는 그래프 작성 
 * @author UserK
 *
 */
public class DfsGraphFriends extends DfsGraph
{	
    private int[] friends_checked;
    private int[] friends_num;
    private int ans;
    private int m;
    /**
     *  생성자 - 그래프 초기화
     * @param num
     * @param m 
     */
    public DfsGraphFriends(int num, int m) {
    	super(num);
        
        this.friends_checked = new int[num];
        this.friends_num = new int[num];
        this.ans = 0;
        this.m = m;
    }
      
    @Override
    public void put(int x, int y) {
        // 정점 x와 y가 연결되어있음을 의미
        dfsGraph[x][y] = dfsGraph[y][x] = 1;
        friends_num[x]++;
        friends_num[y]++;
    }
    
    public int[] getFriends_checked()
	{
		return friends_checked;
	}

	public int[] getFriends_num()
	{
		return friends_num;
	}
	
	public int getAns()
	{
		return ans;
	}

	public void setAns(int ans)
	{
		this.ans = ans;
	}
    
    public void DFS_F(int idx, int N, int path){
    	if(path == m) {ans = 1; return;}
    	
    	friends_checked[idx] = 1;
    	for(int i=0; i<N; i++) {
    		if(path > 1) {
    			if(friends_num[idx] == 1) break;
    		}
    		if((dfsGraph[idx][i] == 1) && (friends_checked[i] == 0)) {
    			DFS_F(i,N,path+1);
    		}
    	}
    	friends_checked[idx] = 0;
    }
}

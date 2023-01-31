package stackqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Day-10 최솟값 찾기
 * @author kitae
 *
 */
public class Ex10 {

	public static void main(String[] args) {
		int N = 12;		// 숫자개수
		int L= 3;		// 스라이딩 윈도우 크기 
		
		Integer[] A = {1, 5, 2, 3, 6, 2, 3, 7, 3, 5, 2, 6};
		
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < A.length; i++) {
			if(queue.size() < 3) {
				queue.add(A[i]);
				
				System.out.println(getMin(queue));
				continue;
			}
			queue.remove();
			queue.add(A[i]);
			System.out.println(getMin(queue));
						
		}
	}

	// 현재 큐에서 가장 작은 값 구하기
	public static Integer getMin(Queue<Integer> queue)
	{	    
	    Integer min = Integer.MAX_VALUE;
	 	 
	    for (Integer i: queue)
	    {	 
	        if (min > i) {
	            min = i;
	        }
	    }	 
	    return min;
	}
}

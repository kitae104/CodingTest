package sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Day-19 k번째 수 구하기
 * @author kitae
 *
 */
public class Ex19
{

	public static void main(String[] args)
	{
		int N = 5;
		int K = 2;
		
		//int[] A = {4, 1, 2, 3, 5};
		
		// 우선 순위 큐 사용(힙 정렬)
		PriorityQueue<Integer> pQ = new PriorityQueue<>(Arrays.asList(4, 1, 2, 3, 5));
		for (int i = 0; i < pQ.size(); i++)
		{
			if(i == K-1) {
				System.out.println(pQ.poll());
				break;
			}
			pQ.poll();
		}
	}
}

package greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Day-33 카드 정렬하기 
 * - 그리디 알고리즘이나 우선순위 큐를 사용 
 * @author kitae
 *
 */
public class Ex33 {

	public static void main(String[] args) {
		Integer N = 5; 					// 카드 묶음 갯수
		Integer[] arr = {10, 30, 20, 15, 40};	// 카드 묶음 배열
		Integer[] arr2 = new Integer[N];
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Arrays.asList(arr));
		System.out.println(pq);		
		
		Integer sum = 0; 
		for (int i = pq.size()-1; i>= 0; i--) {
			sum += pq.remove();
			arr2[i] = sum;
		}

		int result = arr2[0] + arr2[1];
		
		System.out.println(result);
	}

}

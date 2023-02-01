package stackqueue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Day-14 절댓값 힙 구현하기
 * @author kitae
 *
 */
public class Ex14 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = 18;	// sc.nextInt();
		
		// 우선 순위 큐 사용 - 절대값 비교를 위해 Comparator 사용 
		PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1) > Math.abs(o2)) {
					return 1;
				} else if(Math.abs(o1) == Math.abs(o2)) {
					return o1 - o2;
				} else {
					return -1;
				}
			}			
		});
		
		// 미리 18개의 데이터 입력 - 추후에 외부 입력으로 변경 
		int datas[] = {1, -1, 0, 0, 0, 1, 1, -1, -1, 2, -2, 0, 0, 0, 0, 0, 0, 0};
		
		for (int i = 0; i < N; i++) {
			if(datas[i] == 0) {
				if(pQueue.isEmpty()) {
					System.out.println(0);		// 큐가 빈 상태에서 0이 입력되면 0 출력  
					continue;
				}
				int d = pQueue.poll();			// 빈 상태가 아니면 값을 꺼내서 출력  
				System.out.println(d);
				continue;
			}
			pQueue.add(datas[i]);				// 0이 아닌경우엔 우선순위 큐에 데이터 입력 
			//System.out.println(pQueue);
		}
	}
}

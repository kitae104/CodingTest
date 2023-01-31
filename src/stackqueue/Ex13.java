package stackqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

/**
 *  Day-13 카드 게임
 * @author kitae
 *
 */
public class Ex13 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();				// 6 자료 입력 		
		
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < N; i++) {
			queue.offer(i+1);
		}		
		
		while(queue.size() > 1) {
			queue.poll();					// 제일 위에 있는 카드 제거 
			int num = queue.poll();			// 그 다음위에 있는 값 제거
			queue.offer(num);				// 큐의 맨 마지막에 추가 
			System.out.println(queue);		// 큐 내용 확인 
		}
		
		System.out.println(queue.poll());	// 큐에 들어 있는 값 출력	
	}

}

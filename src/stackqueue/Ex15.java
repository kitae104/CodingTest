package stackqueue;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Day-15 : 수 정렬하기
 * @author kitae
 *
 */
public class Ex15 {

	public static void main(String[] args) {
		
		int N = 5;
		
		// 초기화된 리스트 사용
		List<Integer> list = new LinkedList<Integer>(Arrays.asList(5, 2, 3, 4, 1));
		Collections.sort(list);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}

package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Day-34 수 묶기
 * - 양수를 묶을 경우 
 * - 음수를 묶을 경우 
 * - 0이나 1이 포함된 경우 
 * @author kitae
 *
 */
public class Ex34 {

	public static void main(String[] args) {
		int N = 6; 		// 수열의 크기 
		
		
//		Integer[] arr = {1, 1};
//		Integer[] arr = {0, -1, 2, -3, -5, 4, 6};
		Integer[] arr = {0, 1, 2, 3, 4, 5};
		List<Integer> pList = new ArrayList<>();		// 양수 - 내림 차순 
		List<Integer> nList = new ArrayList<>();		// 음수 - 오름 차순
		
		Arrays.sort(arr);								// 정렬 
		
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] > 0) {
				pList.add(arr[i]);				
			} else {
				nList.add(arr[i]);
			}
		}
		
//		System.out.println(pList.size());
//		System.out.println(nList.size());
		
		int sum = 0; 
		// 양수쪽 계산 
		for (int i = pList.size()-1; i >= 0; i-=2) {
			Integer num1 = pList.get(i);			
			if(i == 0) {
				sum += num1;
			} else {
				Integer num2 = pList.get(i-1);
				if(num1 != 1 && num2 != 1) {		// 1이 있는 경우엔 그냥 더 하는게 더 큰 수를 만들 수 있음  
					sum += (num1 * num2);
				} else {
					sum += (num1 + num2);
				}
			}			
		}
		
		// 음수쪽 계산
		for (int i = 0; i< nList.size(); i+=2) {
			Integer num1 = nList.get(i);			
			if(i == nList.size() - 1) {
				sum += num1;
			} else {
				Integer num2 = nList.get(i+1);
				if(num1 != 1 && num2 != 1) {		// 1이 있는 경우엔 그냥 더 하는게 더 큰 수를 만들 수 있음  
					sum += (num1 * num2);
				} else {
					sum += (num1 + num2);
				}
			}			
		}
		System.out.println(sum);
	}

}

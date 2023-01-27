package array_list;

import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;
/**
 * Day-7 주몽의 명령 
 * @author kitae
 *
 */
public class Ex7
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int n = 6; 			// 재료의 개수
		int m = 9;			// 갑옷이 완성되는 번호의 합
		//int[] arr = new int[] {2, 7, 4, 1, 5, 3};	// 재료들 
		
		// 1. 재료를 오름차순으로 정렬
		Vector<Integer> vec = new Vector<>(n);
		
		for (int i = 0; i < vec.capacity(); i++)
		{
			vec.add(sc.nextInt());
		}
		Collections.sort(vec);
		
		// 정렬 내용 확인 
//		for (Integer i : vec){
//			System.out.println(i);
//		}
		
		int count = 0; 
		int i = 0; 
		int j = vec.size()-1;		
		
		// 양끝에 위치해서 하나씩 처리하기 - 투 포인터 이동  
		while(i < j) {
			if(vec.get(i) + vec.get(j) < m) {
				i++;
			} else if(vec.get(i) + vec.get(j) > m) {
				j--;
			} else {
				i++;
				j--;
				count++;
			}
		}
		System.out.println(count);
	}
}

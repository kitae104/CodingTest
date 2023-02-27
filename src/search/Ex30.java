package search;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

/**
 * Day-30 기타 레슨 
 * - 이진 탐색 이용 
 * @author kitae
 *
 */
public class Ex30
{	
	
	private static Vector<Integer> vec;
	private static int N;
	private static int M;
	
	public static void main(String[] args)
	{
		N = 9;		// 총강의 수 
		M = 3;		// 블루레이 갯수 
		
		// 강토의 기타 강의의 길이가 강의 순서대로 분단위
		vec = new Vector<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
		
		int sum = 0; 
		int low = 0, high = 0;
		
		for (int i = 0; i < vec.size(); i++)
		{
			sum += vec.get(i);
			low = Math.max(low, vec.get(i));			// low 값 설정 
		}
		
		high = sum;										// high 값 설정
		
		System.out.println(low + ", " + high);
		
		int res = binarySearch(low, high);
		System.out.println(res);
	}

	private static int binarySearch(int low, int high)
	{
		int mid, sum, count;
		
		while(low <= high) {
			mid = (low + high) /2;
			sum = 0; 
			count = 0; 
			System.out.println("===============================");
			System.out.println("mid : " + mid);
			
			for (int i = 0; i < N; i++)
			{
				if(sum + vec.get(i) > mid) {
					sum = 0; 
					count++;
				}
				sum += vec.get(i);
			}
			System.out.println("sum : " + sum);
			
			
			if(sum > 0) count++;
			System.out.println("cnt : " + count);
			
			if(count <= M) {
				high = mid - 1;
				System.out.println("high : " + high);
			} else {
				low = mid + 1;
				System.out.println("low : " + low);
			}
		}
		
		return low;
	}

}

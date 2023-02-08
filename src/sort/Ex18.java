package sort;

import java.util.Arrays;

/**
 * Day-18 ATM
 * @author kitae
 *
 */
public class Ex18
{

	public static void main(String[] args)
	{
		int N = 5;
		Integer[] P = {3, 1, 4, 3, 2};
		
		// 정렬하기 
		Arrays.sort(P);
		System.out.println(Arrays.toString(P));
				
		// 시간 누적하기 
		int[] sum = new int[N];			// 부분합 값을 저장할 배열 
		sum[0] = P[0];					// 부분합 초기화 
		int sum2 = sum[0];        		// 누적합 초기화
		for (int i = 1; i < sum.length; i++)
		{
			sum[i] = sum[i-1] + P[i];	// 부분합
			sum2 += sum[i];				// 누적합 
		}		
		
		System.out.println(sum2);
	}

}

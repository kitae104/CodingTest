package greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Day-32 동전0
 * - 그리디 알고리즘 사용 
 * @author UserK
 *
 */
public class Ex32
{

	public static void main(String[] args)
	{
		int N = 10;		// 동전의 종류의 갯수 
		int K = 4200;	// 금액 
		
		// 동전의 종류
		Integer[] A = {1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000};
		
		List<Integer> list = Arrays.asList(A);
		Collections.reverse(list);
		A = list.toArray(A);
		
		int res = countCoin(A, K);
		System.out.println(res);
	}

	private static int countCoin(Integer[] A, int money)
	{
		int coinSum = 0; 
		for (int i = 0; i < A.length; i++)
		{
			if(money / A[i] > 0) {
				coinSum += money / A[i];
				money = money % A[i];
			}
		}
		
		return coinSum;
	}

}

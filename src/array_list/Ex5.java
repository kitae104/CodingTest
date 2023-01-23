package array_list;

import java.util.Iterator;

import utils.print.Print;

public class Ex5
{

	public static void main(String[] args)
	{
		int n = 5;				// 수열의 개수 
		int m = 3; 				// 나누어 떨어져야 하는 수

		int[] arr = new int[] {1,2,3,1,2};
		
		int[] sum = new int[arr.length];
		
		int[] mod = new int[arr.length];
		
		int[] cnt = new int[m];
		
		int result = 0;						// M으로 나누어 떨어지는 (i, j)의 쌍의 개수
		
		sum[0] = arr[0];
		
		// 누적합 구하기 
		for (int i = 1; i < arr.length; i++)
		{
			sum[i] = sum[i-1] + arr[i];
		}		
		Print.printArray("sum[] = ", sum);
		
		// 나머지를 처리한 배열 구하기 
		for (int i = 0; i < mod.length; i++)
		{
			mod[i] = sum[i] % m;
		}
		Print.printArray("mod[] = ", mod);
		
		// 나머지 갯수 배열 구하기 
		for (int i = 0; i < mod.length; i++)
		{
			if(mod[i] == 0) {
				result++;
			}
			cnt[mod[i]]++;
		}
		Print.printArray("cnt[] = ", cnt);
		
		// 나머지 값이 같은 인덱스 중 2개를 뽑는 모든 경우의 수 구하기 
		// 0이 3개 1이 2개 이므로 조합의 합으로 구한다. 
//		for (int i = 0; i < cnt.length; i++)
//		{
//			if(cnt[i] > 1) {
//				result += (cnt[i] * (cnt[i]-1) / 2);
//			}
//		}
	
		// 구간의 합 mod[j] - mod[i-1]을 나머지 값으로 나누었을 때 0이되는 쌍의 개수 계산 
		for (int i = 1; i < n; i++)
		{
			for (int j = i; j < n; j++)
			{
				if((mod[j] - mod[i-1]) % m == 0) {
					result++;
				}
			}
		}
		
		System.out.println(result);
	}
}

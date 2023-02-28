package search;

import java.util.Arrays;

/**
 * Day-31 K번째 수
 * - N X N 배열 A 
 * - 인덱스를 1부터 사용
 * @author kitae
 *
 */
public class Ex31
{

	public static void main(String[] args)
	{
		int N = 3;
		int k = 7;
		int[][] A = new int[N+1][N+1];		// 2차원 배열 - 인덱스를 1부터 사용하기 위해 
		int[] B = new int[(N * N)+1];		// 1차원 배열 - 인덱스를 1부터 사용하기 위해 
		int cnt = 1; 
		
		// 배열에 값 설정
		for (int i = 1; i < A.length; i++)
		{
			for (int j = 1; j < A[0].length; j++)
			{
				A[i][j] = i * j;
				B[cnt++] = i * j;			// 1번 부터 입력 
			}
		}
		
		// 배열 출력하기 
		for (int i = 1; i < A.length; i++)
		{
			for (int j = 1; j <  A[0].length; j++)
			{
				System.out.printf("%3d ", A[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
		// 배열 정렬 
		Arrays.sort(B);
		
		// 배열 내용 확인하기 
		for (int i : B)
		{
			System.out.print(i + " ");
		}
		System.out.println();
		
		// k번째 있는 내용 출력하기 - 배열의 0번째는 사용하지 않음
		System.out.println(B[k]);
	}

}

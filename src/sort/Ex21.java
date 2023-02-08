package sort;

/**
 * Day-21 버블소트
 * @author kitae
 *
 */
public class Ex21
{
	public static void main(String[] args)
	{
		int N = 3; 
		int[] A = new int[] {3,2,1};
		int cnt = 0; 
				
		
		for (int i = 0; i < A.length; i++)
		{		
			for (int j = 0; j < A.length - i - 1; j++)
			{
				if(A[j] > A[j+1]) {
					cnt++;
					swap(A, j);
				}
			}			
		}
		System.out.println(cnt);
	}

	private static void swap(int[] A, int i)
	{
		int temp;
		temp = A[i];
		A[i] = A[i+1];
		A[i+1] = temp;		
	}
}

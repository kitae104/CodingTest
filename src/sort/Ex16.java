package sort;

/**
 * Day-16 버블 소트
 * @author kitae
 *
 */
public class Ex16
{

	public static void main(String[] args)
	{
		int N = 5; 
		int[] A = new int[] {10, 1, 5, 2, 3};
		
		boolean changed = false;
		
		for (int i = 0; i < A.length; i++)
		{
			changed = false;
			for (int j = 0; j < A.length - i - 1; j++)
			{
				if(A[j] > A[j+1]) {
					changed = true;
					swap(A, j);
				}
			}
			if(changed == false) {
				System.out.println(i + 1);
				break;
			}
		}
	}

	private static void swap(int[] A, int i)
	{
		int temp;
		temp = A[i];
		A[i] = A[i+1];
		A[i+1] = temp;		
	}
}

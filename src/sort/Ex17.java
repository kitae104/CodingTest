package sort;

import java.util.Arrays;
import java.util.Collections;

/**
 * Day-17 소트인사이드
 * @author kitae
 *
 */
public class Ex17
{

	public static void main(String[] args)
	{
		int[] A = new int[] {2,1,4,3};
		
		Arrays.sort(A);
		
		System.out.println(Arrays.toString(A));
		
		Integer[] arr = {2,1,4,3};
		Arrays.sort(arr, Collections.reverseOrder());
		System.out.println(Arrays.toString(arr));
		
	}

}

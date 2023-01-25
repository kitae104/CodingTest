package utils.print;

import java.util.ArrayList;
import java.util.List;

public class Print
{
	/**
	 * 배열 내용 출력
	 * @param arrayName 
	 * @param arr
	 */
	public static void printArray(String arrayName, int[] arr)
	{
		System.out.print(arrayName +"[");
		for (int i = 0; i < arr.length-1; i++)
		{
			System.out.print(arr[i] + ", ");
		}
		System.out.print(arr[arr.length-1]);
		System.out.println("]");
		
	}

	public static void printList(List<Integer> list) {
		for (Integer item : list) {
			System.out.print(item + " ");
		}
		System.out.println();
	}
}

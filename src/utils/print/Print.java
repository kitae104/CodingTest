package utils.print;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
	
	/**
	 * 리스트 내용 출력 
	 * @param list
	 */
	public static void printList(List<Integer> list) {
		for (Integer item : list) {
			System.out.print(item + " ");
		}
		System.out.println();
	}

	/**
	 * 벡터 내용 출력 
	 * @param vec
	 */
	public static void printVector(Vector<Integer> vec)
	{
		for (Integer i : vec)
		{
			System.out.print(i + " ");
		}
		System.out.println();
		
	}
}

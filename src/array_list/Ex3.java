package array_list;

import java.util.Scanner;
import java.util.Vector;

/**
 * 구간 합 구하기
 * @author kitae
 *
 */
public class Ex3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();			// 데이터의 개수 
		int quiz = sc.nextInt();		// 질의의 개수 
		
		int[] datas = new int[num];		// 구간 합을 구할 대상 배열
		//int[] datas = {5,4,3,2,1};
		
		for (int i = 0; i < datas.length; i++) {
			datas[i] = sc.nextInt();
		}
		
		
		Vector<Integer[]> iv = new Vector<Integer[]>();
		for (int i = 0; i < quiz; i++) {
			Integer[] iarr = new Integer[2];
			for (int j = 0; j < iarr.length; j++) {
				iarr[j] = sc.nextInt();
			}
			iv.add(iarr);
		}
		
		for (int i = 0; i < quiz; i++) {
			Integer[] iarr = iv.get(i);
			int start = iarr[0];
			int end = iarr[1];
			int sum = 0; 
			for (int j = start-1; j < end; j++) {
				sum += datas[j];
			}
			System.out.println(sum);
		}
	}
}

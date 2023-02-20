package array_list;

import java.util.Scanner;

/**
 * 숫자의 합 구하기
 * @author kitae
 *
 */
public class Ex01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
				
		String sNum = sc.next();
		sc.close();
		
		char[] cArr = sNum.toCharArray();
		
		int sum = 0; 
		
		for (int i = 0; i < cArr.length; i++) {
			sum += cArr[i] - '0';
		}
		System.out.println("입력된 숫자의 갯수 : " + cArr.length);
		System.out.println("숫자의 합 : " + sum);
	}
}

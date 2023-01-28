package array_list;

import java.util.Scanner;

/**
 * 평균 구하기
 * @author kitae
 *
 */
public class Ex02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("데이터 입력 수 : ");
		int num = sc.nextInt();
		int[] score = new int[num];
		
		for (int i = 0; i < score.length; i++) {
			score[i] = sc.nextInt();
		}
		
		int sum = 0; 
		int max = 0; 
		
		for (int i = 0; i < score.length; i++) {
			if(max < score[i]) {
				max = score[i];
			}
			sum += score[i];
		}
		
		double avg = sum * 100.0 / max / num;
		System.out.println("평균 : " + avg);
	}
}

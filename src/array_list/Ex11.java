package array_list;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

import utils.print.Print;

/**
 * Day-11 오큰수
 * @author 김기태
 *
 */
public class Ex11
{
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int n = 4; 				// 수열의 크기 
		
		int[] A = new int[n];	// 수열 선언 

		// 수열값 입력 
		for (int i = 0; i < A.length; i++)
		{
			A[i] = sc.nextInt();
		}
		
		// 벡터를 생성할 때 초기값을 준상태로 초기화 하기
		Vector<Integer> vec = new Vector<>(Arrays.asList(0, 0, 0, 0));
		
		for (int i = 0; i < A.length; i++)
		{
			int num = A[i];							// 기준이 되는 수 
			for(int j = i; j < A.length; j++) {		
				if(num < A[j]) {					// 오른쪽에 다른 값들과 비교
					vec.set(i, A[j]);				// 처음 나타난 값을 벡터에 설정
					break;
				} else {					
					vec.set(i,-1);					// 아닌 경우라면 해당 위치에 -1 설정 
				}				
			}
		}
		
		Print.printVector(vec);
		
	}
}

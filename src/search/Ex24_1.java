package search;

import utils.number.MinMaxNumber;
import utils.number.PrimeNumber;

/**
 * Day-24_1 신기한 소수(사용자 정의 클래스 추가)
 * 
 * @author kitae
 *
 */
public class Ex24_1
{

	public static void main(String[] args)
	{
		int N = 4; // 자릿수
		int min = 0; 
		int max = 0;
		int num = 0; 
		
		MinMaxNumber mmn = new MinMaxNumber(N);
		min = mmn.getMin();
		max = mmn.getMax();
		num = mmn.getMin();		
		
		for (int i = min; i < max; i++)
		{
			boolean check = false;
			int n = num;
			int check_num = 0; 
			while(n != 0) {				
				check_num = i / n; 				// 첫번째 자리 부터 처리 
				if(PrimeNumber.is_prime(check_num)) {
					n = n / 10;					
					check = true;
					continue;					
				} else {
					check = false;
					break;
				}
			}
			
			if(check == true) {
				System.out.println(i);
			}
		}
	}	
}

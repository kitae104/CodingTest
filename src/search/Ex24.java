package search;

/**
 * Day-24 신기한 소수
 * 
 * @author kitae
 *
 */
public class Ex24
{

	public static void main(String[] args)
	{
		int N = 4; // 자릿수
		int min = 0; 
		int max = 0;
		int num = 0; 
		
		for (int i = 0; i < N; i++)
		{			
			min = min + (int)Math.pow(10, i) * 1;		// N자리의 최소수 
			max = max + (int)Math.pow(10, i) * 9;		// N자리의 최대수
			num = (int)Math.pow(10, i);
		}
		
		
		for (int i = min; i < max; i++)
		{
			boolean check = false;
			int n = num;
			int check_num = 0; 
			while(n != 0) {				
				check_num = i / n; 				// 첫번째 자리 부터 처리 
				if(is_prime(check_num)) {
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

	// 소수 판별 메소드
	public static boolean is_prime(int number)
	{		
		if (number < 2)
		{			
			return false;
		}
		
		if (number == 2)
		{			
			return true;
		}

		for (int i = 2; i < number; i++)
		{
			// 소수가 아닐경우
			if (number % i == 0)
			{
				return false;
			}
		}		
		return true;
	}
}

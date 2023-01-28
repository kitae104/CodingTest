package array_list;

/**
 * 연속된 자연수의 합 구하기
 * @author 김기태
 *
 */
public class Ex06
{

	public static void main(String[] args)
	{
		int n = 15;			// 입력된 수 
		int cnt = 0;		// 정답 가짓수 

		// 1. 일반적인 방법 
		for (int i = 1; i <= n; i++)
		{
			int sum = 0;
			for (int j = i; j <= n; j++)	// 시작점부터 순차적으로 더하기  
			{
				sum += j;
				if(sum > n) {
					break;
				} else if(sum == n) {
					cnt++;
					break;
				}
			}
		}
		System.out.println(cnt);
		
		// 투포인트 방식
		int start = 0;
		int end = 0;
		int sum = 0;
		cnt = 0;
		
		while(start <= n) {
			while(++end <= n) {		// end 증가
				sum += end;			// 부분합 증가
				if(sum >= n) {
					if(sum == n) {
						cnt++;
					}
					break;
				}
			}
			while(++start <= n) {	// start 증가
				sum -= start;		// 부분합을 감소
				if(sum <= n) {
					if(sum == n) {
						cnt++;
					}
					break;
				}
			}			
		}
		System.out.println(cnt);
	}

}

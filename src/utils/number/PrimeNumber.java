package utils.number;

public class PrimeNumber
{
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

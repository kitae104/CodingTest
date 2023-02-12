package utils.number;

public class MinMaxNumber
{
	private int min;
	private int max;
	private int one;
	private int size; 
	
	public MinMaxNumber(int size)
	{		
		this.size = size;		
	}

	public int getMin()
	{		
		min = (int)Math.pow(10, size-1);		// N자리의 최소수 			
		return min;
	}
	
	public int getOne()
	{
		for (int i = 0; i < size; i++)
		{			
			one = one + (int)Math.pow(10, i) * 1;		// N자리의 최소수 			
		}
		return one;
	}
	

	public int getMax()
	{
		for (int i = 0; i < size; i++)
		{		 
			max = max + (int)Math.pow(10, i) * 9;		// N자리의 최대수
		}
		return max;
	}	
}

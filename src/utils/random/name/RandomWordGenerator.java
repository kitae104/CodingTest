package utils.random.name;

import java.util.Random;

public class RandomWordGenerator
{
	public static void main(String[] args)
	{
		Random random = new Random();
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength;
		for (int i = 0; i < 10; i++)
		{
			targetStringLength = random.nextInt(10) + 1;
			StringBuilder buffer = new StringBuilder(targetStringLength);
			for (int j = 0; j < targetStringLength; j++)
			{
				int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
				buffer.append((char) randomLimitedInt);
			}
			System.out.println(buffer.toString());
		}
	}
}
package array_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 5명의 별명을 입력 받아 ArrayList에 저장하고 이들 중 별명의 길이가 제일 긴 별명을 출력하시오. (단, 각 별명의 길이는 모두
 * 다르게 입력한다.)
 * 
 * @author 김기태
 *
 */
public class ArrayListTest02
{

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);

		ArrayList<String> aliasList = new ArrayList<>(Arrays.asList("tu", "ymsdntxfp", "nbp", "rclcmemd", "japt"));

		// 제일 긴 별명이 저장된 index값이 저장될 변수를 선언하고 0으로 초기화 한다.
		int maxIndex = 0;

		for (int i = 1; i < aliasList.size(); i++)
		{

			if (aliasList.get(maxIndex).length() < aliasList.get(i).length())
			{
				maxIndex = i;
			}
		}

		System.out.println("제일 긴 별명 : " + aliasList.get(maxIndex));
	}
}
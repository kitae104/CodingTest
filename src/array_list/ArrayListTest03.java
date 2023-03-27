package array_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 5명의 별명을 입력 받아 ArrayList에 저장하고 이들 중 별명의 길이가 제일 긴 별명을 출력하시오. 
 * (위 문제에서 별명의 길이가 같은 것을 입력할 수 있는 경우를 처리하시오.)
 * 
 * @author 김기태
 *
 */
public class ArrayListTest03
{

	public static void main(String[] args)
	{		

		ArrayList<String> aliasList = new ArrayList<>(Arrays.asList("tu", "ymsdntxfp", "amsdntxfz", "rclcmemd", "japt"));

		//제일 긴 별명의 길이가 저장될 변수를 선언하고 이 변수는 List의 첫번째 데이터의 길이로 초기화한다.
		int maxLength = aliasList.get(0).length();

		for (int i = 1; i < aliasList.size(); i++)
		{

			if (maxLength < aliasList.get(i).length())
			{
				maxLength = aliasList.get(i).length();
			}
		}

		System.out.println("제일 긴 별명 : " );
		for (int i = 0; i < aliasList.size(); i++)
		{
			if(aliasList.get(i).length() == maxLength) {
				System.out.println(aliasList.get(i));
			}
		}
	}
}
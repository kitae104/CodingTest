package array_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 5명의 사람 이름을 입력 받아서 ArrayList에 저장한 후에 이들 중 '김'씨 성을 가진 사람을 모두 출력하시오.
 * 
 * @author 김기태
 *
 */
public class ArrayListTest01
{

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);

//		ArrayList<String> nameList = new ArrayList<>();
		ArrayList<String> nameList = new ArrayList<>(Arrays.asList(
				"김기태", "최현이", "박민강", "박서이",	"김지강", "이은조"));
		

//		for (int i = 1; i <= 5; i++)
//		{
//			System.out.println(i + "번째 사람의 이름을 입력하세요.");
//			String name = scan.nextLine();
//			nameList.add(name);
//		}

		System.out.println();
		System.out.println("김씨 성을 가진 사람들...");

		for (int i = 0; i < nameList.size(); i++)
		{
//			if (nameList.get(i).charAt(0) == '김')
//			{
//				// charAt은 반환값이 character이기 때문에 ' '
//				System.out.println(nameList.get(i));
//			}

//			if (nameList.get(i).substring(0, 1).equals("김"))
//			{
//				System.out.println(nameList.get(i));
//			}

//			if (nameList.get(i).indexOf("김") == 0)
//			{
//				System.out.println(nameList.get(i));			
//			}
			
			if (nameList.get(i).startsWith("김"))
			{
				// "김"으로 시작하면 true
				System.out.println(nameList.get(i));
			}
		}
	}
}
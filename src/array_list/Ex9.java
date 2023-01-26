package array_list;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import utils.print.Print;

public class Ex9
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int s = 9;		// 문자열의 길이 
		int p = 8;		// 비밀번호로 사용할 부분 문자열의 길이 
		
		char[] cArr = {'A', 'C', 'G', 'T'};
		
		String input = sc.nextLine();
		ArrayList<Character> cList = new ArrayList<>();
		for (int i = 0; i < input.length(); i++)
		{
			cList.add(input.charAt(i));
		}
		
		String input2 = sc.nextLine();
		
		StringTokenizer st = new StringTokenizer(input2);
		ArrayList<Integer> iList = new ArrayList<>();
		while(st.hasMoreTokens()) {
			iList.add(Integer.parseInt(st.nextToken()));
		}
		
		Print.printList(iList);
	}

}

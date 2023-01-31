package array_list;

import java.util.Scanner;
import java.util.Stack;

/**
 * Day-10 스택 수열
 * @author 김기태
 *
 */
public class Ex10
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		StringBuffer sb = new StringBuffer();
		
		int n = 8;		// 입력 갯수
		
//		int[] input = {4,2,3,1,5};			// 입력된 수열 
		int[] input = {4,3,6,8,7,5,2,1};	// 입력된 수열
		
		int temp = 1; 
		boolean error = false;
		
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < input.length; i++)
		{
			int find = input[i];
			
			while(temp <= find) {
				stack.push(temp++);
				sb.append("+\n");
			}
			
			if(stack.peek() == find) {
				stack.pop();
				sb.append("-\n");
			} else {
				error = true;
			}
		}
		
		if(error) {
			System.out.println("NO");
		} else {
			System.out.println(sb);
		}
	}

}

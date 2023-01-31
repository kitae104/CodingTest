package array_list;

<<<<<<< HEAD
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

=======
import java.util.LinkedList;
import java.util.Queue;

/**
 * 최솟값 찾기
 * @author kitae
 *
 */
public class Ex10 {

	public static void main(String[] args) {
		int N = 12;		// 숫자개수
		int L= 3;		// 스라이딩 윈도우 크기 
		
		Integer[] A = {1, 5, 2, 3, 6, 2, 3, 7, 3, 5, 2, 6};
		
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < A.length; i++) {
			if(queue.size() < 3) {
				queue.add(A[i]);
				
				System.out.println(getMin(queue));
				continue;
			}
			queue.remove();
			queue.add(A[i]);
			System.out.println(getMin(queue));
						
		}
	}

	// 현재 큐에서 가장 작은 값 구하기
	public static Integer getMin(Queue<Integer> queue)
	{	    
	    Integer min = Integer.MAX_VALUE;
	 	 
	    for (Integer i: queue)
	    {	 
	        if (min > i) {
	            min = i;
	        }
	    }	 
	    return min;
	}
>>>>>>> e2d9c444a2002de40e87ee4032bef08b2b9a761c
}

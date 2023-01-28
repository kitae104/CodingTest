package array_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

import utils.print.Print;
/**
 * Day-9 DNA 비밀번호
 * @author kitae
 *
 */
public class Ex09
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int s = sc.nextInt();		// 문자열의 길이 
		int p = sc.nextInt();		// 비밀번호로 사용할 부분 문자열의 길이 
		
		char[] cArr = {'A', 'C', 'G', 'T'};
		
		
		String input = sc.nextLine();
		
		// 입력 리스트 
		ArrayList<Character> cList = new ArrayList<>();
		for (int i = 0; i < input.length(); i++)
		{
			cList.add(input.charAt(i));
		}
		
		String input2 = sc.nextLine();
		
		StringTokenizer st = new StringTokenizer(input2);
		
		// 정답 체크 리스트 
		ArrayList<Integer> answerList = new ArrayList<>();
		while(st.hasMoreTokens()) {
			answerList.add(Integer.parseInt(st.nextToken()));
		}
		
		// 갯수 확인 리스트 
		ArrayList<Integer> countList;
		
		int count = 0; 
		
		for (int i = 0; i <= s - p; i++) {
			// 벡터를 생성할 때 초기값을 준상태로 초기화 하기
			countList = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
			for (int j = i; j < p + i; j++) {
				System.out.print(cList.get(j) + " ");
				char c = cList.get(j);
				int ans = 0;
				switch(c) {
				case 'A':
					ans = countList.get(0) +1;
					countList.set(0, ans);
					break;
				case 'C':
					ans = countList.get(1) +1;
					countList.set(1, ans);
					break;
				case 'G':
					ans = countList.get(2) +1;
					countList.set(2, ans);
					break;
				case 'T':
					ans = countList.get(3) +1;
					countList.set(3, ans);
					break;
				default:
					System.out.println("잘못된 문자가 입력되었습니다.");
					break;
				}
			}
			Print.printList(countList);
			
			System.out.println();
			if(answerList.containsAll(countList)) {	// 전체 내용물이 같은지 비교
				count++;
			}
			countList.clear();
		}
		
		System.out.println("\n결과 : " + count);
	}

	
}

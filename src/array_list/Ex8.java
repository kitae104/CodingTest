package array_list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import utils.print.Print;

public class Ex8 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = 10;		// 수의 개수 
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		// 1. 숫자를 n개 만큼 입력 받기 
		for (int i = 0; i < n; i++) {
			list.add(sc.nextInt());
		}
		
		Collections.sort(list);		// 정렬하기 
		
		Print.printList(list);		// 출력하기 
		
		int count = 0; 
		for (int k = 0; k < n; k++) {	// 원소 갯수 만큼 수행 
			
			int find = list.get(k); 	// 확인하고자 하는 수 
			int i = 0; 
			int j = list.size()-1;
			
			while(i < j) {				
				if(list.get(i) + list.get(j) == find) {	// 각각을 더해서 원하는 수를 찾은 경우
					if(i != find && j!= find) {			// 서로 다른 경우라면 
						count++;						// 찾은 경우라서 카운트 증가 
						break;
					} else if(i == k) {					// 찾았지만 조건에 맞지 않으면 이동 
						i++;
					} else {
						j++;
					}
				} else if(list.get(i) + list.get(j) < find) {
					i++;
				} else {					
					j--;					
				}
			}
		}
		System.out.println(count);
		sc.close();
	}
}

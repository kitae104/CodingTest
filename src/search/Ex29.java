package search;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Day-29 수 찾기
 * - 이분 탐색 사용(이진 탐색)
 * - 순차 탐색도 가능하지만 속도를 위해 이진 탐색이 바람직함
 * @author kitae
 *
 */
public class Ex29 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = 5; //in.nextInt();
		int[] arr = {4, 1, 5, 2, 3};	//new int[N];
		
//		for (int i = 0; i < arr.length; i++) {
//			arr[i] = sc.nextInt();
//		}
		
		Arrays.sort(arr); 	// 오름차순 정렬 
		
		int M = 5; //in.nextInt();
        int[] arr2 = {1, 3, 7, 9, 5};

        for (int i = 0; i < arr2.length; i++) {
            if(binarySearch(arr, arr2[i]) >= 0){
                System.out.println("1");            // 존재하는 경우
            } else {
                System.out.println("0");            // 존재하지 않는 경우
            }
        }
	}

	public static int binarySearch(int[] arr, int target) {
		int low = 0; 					// 탐색 범위의 첫번째 인덱스
        int high = arr.length - 1; 		// 탐색 범위의 마지막 인덱스
        
        while(low <= high) { 
            int mid = (low + high) / 2;	// 중간 값 찾기 
            
            // target값이 중간 위치의 값보다 작을 경우
            if(target < arr[mid]) {
                high = mid - 1;
            }
            // target값이 중간 위치의 값보다 클 경우
            else if(target > arr[mid]) {
                low = mid + 1;
            }
            // target값과 중간 위치의 값이 같을 경우
            else {
                return mid;
            }
        }
        
        return -1;		// 해당 값을 못찾은 경우 
	}
}

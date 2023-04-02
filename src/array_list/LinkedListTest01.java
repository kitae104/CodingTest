package array_list;

import java.util.LinkedList;

public class LinkedListTest01 {
    public static void main(String[] args) {

        // 임의의 10개의 임의의 데이터가 있는 리스트 생성
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add((int)(Math.random() * 100));
        }
        System.out.println(list);



    }
}

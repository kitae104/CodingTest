package utils.random.number;

import java.util.LinkedList;

/**
 * 임의의 10개의 임의의 데이터가 있는 리스트 생성
 */
public class MakeRandomNumberList {
    public static void main(String[] args) {

        // 임의의 10개의 임의의 데이터가 있는 리스트 생성
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add((int)(Math.random() * 100));
        }
        System.out.println(list);

    }
}

package utils.random.number;

import java.util.LinkedList;

/**
 * 임의의 10개의 임의의 문자열이 있는 리스트 생성
 */
public class MakeRandomStringList {
    public static void main(String[] args) {

        // 임의의 10개의 임의의 문자열이 있는 리스트 생성
        LinkedList<String> list = new LinkedList<>(); // 리스트 생성

        // 임의의 길이의 문자열 생성
        for (int i = 0; i < 10; i++) {
            int length = (int)(Math.random() * 10) + 1;         // 1 ~ 10
            String s = "";
            for (int j = 0; j < length; j++) {
                s += (char)((int)(Math.random() * 26) + 97);    // a ~ z
            }
            list.add(s);   // 리스트에 추가
        }
        System.out.println(list);
    }
}

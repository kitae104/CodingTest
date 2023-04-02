package utils.random.lotto;

import java.util.ArrayList;
import java.util.Collections;

public class MakeLottoNumber {
    public static void main(String[] args) {

        // 번호가 1부터 45사이의 번호 6개를 출력하는 lotto 번호 생성기
        ArrayList<Integer> lotto = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int num = (int)(Math.random() * 45) + 1;
            if (lotto.contains(num)) {
                i--;
                continue;
            }
            lotto.add(num);
        }

        System.out.println(lotto);
        Collections.sort(lotto);
        System.out.println(lotto);



    }
}

package utils.random.name;

import java.util.Random;

public class MakeHangulName {
    public static void main(String[] args) {
        String[] firstNames = {"김", "이", "박", "최", "정", "강", "조", "윤", "장", "임"};
        String[] middleNames = {"민", "서", "영", "지", "현", "진", "수", "은"};
        String[] lastNames = {"홍", "김", "이", "박", "최"};

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String middleName = middleNames[random.nextInt(middleNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];

            System.out.println(lastName + middleName + firstName);
        }
    }
}
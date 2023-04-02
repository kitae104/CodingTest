package utils.regexp;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 사칙 연산을 위한 정규식1
 */
public class RegExpOp2 {
    public static void main(String[] args) {

        ArrayList<String> arr = new ArrayList<>();
        String regex = "[0-9\\+\\-*/\\^\\(\\)=]+";
        String expression = "( 152 + 14 * ( 11 - 7345 ) / 1 )";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            arr.add(matcher.group());
        }

        System.out.println(arr);
    }
}

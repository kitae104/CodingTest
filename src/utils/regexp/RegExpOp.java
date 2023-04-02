package utils.regexp;

/**
 * 사칙 연산을 위한 정규식1
 */
public class RegExpOp {
    public static void main(String[] args) {
        String s = "(3 + 12X 9 - 4 / 2)";
        String regExp = "[^0-9]";
        s = s.trim();
        String[] operands = s.split(regExp);
        for (String operand : operands) {
            if(!operand.equals("")){
                System.out.println(operand);
            }
        }
    }
}

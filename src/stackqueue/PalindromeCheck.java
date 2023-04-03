package stackqueue;

import java.util.Stack;

/**
 * 스택을 이용한 회문 검사
 */
public class PalindromeCheck {
    public static void main(String[] args) {
        String s = "abccba";
        System.out.println(isPalindrome(s));
        System.out.println(isPalindrome2(s));
    }

    /**
     * 회문 검사 - 스택을 이용하지 않고 문자열의 길이를 반으로 나누어서 비교
     * @param s
     * @return
     */
    private static boolean isPalindrome2(String s) {
        int len = s.length() / 2;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 회문 검사 - 스택을 이용
     * @param s
     * @return
     */
    private static boolean isPalindrome(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
        }

        String reverse = "";

        while (!stack.isEmpty()) {
            reverse += stack.pop();
        }
        return s.equals(reverse);
    }
}

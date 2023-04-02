package stackqueue;

import java.util.Stack;
import java.util.Vector;

public class InfixToPostfix2 {
    public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public static Vector<String> infixToPostfix(String exp) {
        Vector<String> result = new Vector<>();
        //String result = "";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);
            if (Character.isLetterOrDigit(c))
                result.add(c + "");
            else if (c == '(')
                stack.push(c);
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result.add(stack.pop()+"");
                if (!stack.isEmpty() && stack.peek() != '(')
                    System.out.println();
                else
                    stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek()))
                    result.add(stack.pop()+"");
                stack.push(c);
            }
        }
        while (!stack.isEmpty())
            result.add(stack.pop() + "");
        return result;
    }

    public static void main(String[] args) {
        String exp = "(5+4*(1-7)/1)";
        System.out.println(infixToPostfix(exp));
    }
}
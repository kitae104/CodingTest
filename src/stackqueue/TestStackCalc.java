package stackqueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class TestStackCalc
{

	public static void main(String[] args)
	{
		
		String input = "(3+4)*(5-2)";
		String input2 = "(((((())(()))))((())))";
		
		String postInput = postfixToInfix(input);
		System.out.println(postInput);
		
		Stack<Integer> stack = new Stack<>();
		
		
		int num1 = 0; 
		int num2 = 0;
		int result = 0;
		
		for (int i = 0; i < postInput.length(); i++)
		{
			char c = postInput.charAt(i);
			
			switch(c) {
			case '+':
				num1 = stack.pop();
				num2 = stack.pop();
				result = num1 + num2;
				stack.push(result);
				break;
			case '-':
				num1 = stack.pop();
				num2 = stack.pop();
				result = num2 - num1;
				stack.push(result);
				break;
			case '*':
				num1 = stack.pop();
				num2 = stack.pop();
				result = num1 * num2;
				stack.push(result);
				break;
			default:
				stack.push((int)(c-'0'));
			}
		}
		int last = stack.pop();
		System.out.println("계산 결과 : " + last);
	}

	
	public static String postfixToInfix(String expression) {
        String answer = "";
        Stack<String> stack = new Stack<>();
        String[] op = {"+", "-", "*", "/", "(", ")"};

        for (char c : expression.toCharArray()) {
            if (Arrays.asList(op).contains(c + "")) {

                if (c == ')') {
                    while (!stack.isEmpty() && stack.peek().charAt(0) != '(') {
                        answer += stack.pop().charAt(0);
                    }
                    stack.pop();

                } else {

                    while (!stack.isEmpty() && compareToOperation(c, stack.peek().charAt(0))) {
                        answer += stack.pop();
                    }
                    stack.push(c + "");
                }
            } else {
                answer += c;
            }
        }

        while (!stack.isEmpty()) {
            answer += stack.pop();
        }
        return answer;
    }
    
    public static boolean compareToOperation(char op1, char op2) {
        switch (op1) {
            case '+':
                if (op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/') {
                    return true;
                }
            case '-':
                if (op2 == '-' || op2 == '+' || op2 == '*' || op2 == '/') {
                    return true;
                }
            case '*':
                if (op2 == '*' || op2 == '/') {
                    return true;
                }
            case '/':
                if (op2 == '*' || op2 == '/') {
                    return true;
                }
        }
        return false;
    }
}

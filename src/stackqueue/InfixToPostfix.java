package stackqueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 중위 표기법을 후위 표기법으로 변환하기
 */
public class InfixToPostfix {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        String infix = "( 52 + 4 * ( 11 - 7 ) / 1 )";
        StringTokenizer st = new StringTokenizer(infix);

        // 내용 확인
//        while (st.hasMoreTokens()) {
//            String token = st.nextToken();
//            System.out.println(token);
//        }

        // 정규식 처리
        ArrayList<String> tokenList = new ArrayList<>();
        String regex = "[0-9\\+\\-*/\\^\\(\\)=]+";
        String expression = "( 52 + 4 * ( 11 - 7 ) / 1 )";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            tokenList.add(matcher.group());
        }

        System.out.println(tokenList);

        // 연산자 우선 순위 설정(스택 안)
        HashMap<String, Integer> inPriority = new HashMap<>();
        inPriority.put("(", 0);
        inPriority.put("+", 1);
        inPriority.put("-", 1);
        inPriority.put("*", 2);
        inPriority.put("/", 2);

        // 연산자 우선 순위 설정(스택 밖)
        HashMap<String, Integer> outPriority = new HashMap<>();
        outPriority.put("+", 1);
        outPriority.put("-", 1);
        outPriority.put("*", 2);
        outPriority.put("/", 2);
        outPriority.put("(", 3);

        // 출력 배열
        ArrayList<String> arr = new ArrayList<>();
        while(st.hasMoreTokens()){
            String token = st.nextToken();

            // 토큰이 왼쪽 괄호 및 연산자 인경우
            if(token.equals("(") || token.equals("/") || token.equals("*") || token.equals("-") || token.equals("+")) {
                if (stack.isEmpty()) {
                    stack.push(token);
                } else {
                    while (true) {
                        if (inPriority.get(stack.peek()) < outPriority.get(token)) {
                            stack.push(token);
                            break;
                        }
                        arr.add(stack.pop());
                    }
                }

              // 토큰이 오른쪽 괄호
            } else if(token.equals(")")){
                while(true) {
                    if(stack.peek().equals("(")) {
                        stack.pop();
                        break;
                    }
                    arr.add(stack.pop());
                }
                // 토큰이 숫자
            }  else {
                arr.add(token);
            }
        }
        if(!st.hasMoreTokens()){
            while(true){
                if(stack.isEmpty()){
                    break;
                }
                arr.add(stack.pop());
            }
        }
        System.out.println(arr);

        stackOperation(stack, arr);
    }

    // 스택 연산 메소드 구현    
    public static void stackOperation(Stack<String> stack, ArrayList<String> arr) {
        for (int i = 0; i < arr.size(); i++) {
            String token = arr.get(i);
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result = 0;
                switch (token) {
                    case "+":
                        result = num2 + num1;
                        break;
                    case "-":
                        result = num2 - num1;
                        break;
                    case "*":
                        result = num2 * num1;
                        break;
                    case "/":
                        result = num2 / num1;
                        break;
                }
                stack.push(String.valueOf(result));
            } else {
                stack.push(token);
            }
        }
        System.out.println(stack.pop());
    }
}

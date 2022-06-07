package com.neocoder.newone;

import java.util.Scanner;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String execution = scanner.nextLine();
        execution = execution.replaceAll("\\s+", "");
        String[] array = execution.split("=", 2);

        String[] list = new String[2];
        Double[] list2 = new Double[2];
        for (int i = 0; i < array.length; i++) {
            list[i] = toPostFix(array[i]);
        }

        Double a = list2[0] = getAnswer(list[0]);
        Double b = list2[1] = getAnswer(list[1]);

        if (a.equals(b)) {
            System.out.println("YES");
        } else System.out.println("NO");

    }

    public static String toPostFix(String inFix) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        int precedence;
        for (int i = 0; i < inFix.length(); i++) {
            precedence = getPrecedence(inFix.charAt(i));

            if (precedence == 0) {
                result += inFix.charAt(i);
            }
            if (precedence == 1) {
                stack.push(inFix.charAt(i));
            }
            if (precedence > 1) {
                result += ' ';

                while (!stack.empty()) {
                    if (getPrecedence(stack.peek()) >= precedence) result += stack.pop();
                    else break;
                }
                stack.push(inFix.charAt(i));
            }

            if (precedence == -1) {
                result += ' ';
                while (getPrecedence(stack.peek()) != 1) result += stack.pop();
                stack.pop();
            }
        }
        while (!stack.empty()) result += stack.pop();
        return result;
    }

    private static int getPrecedence(char charAt) {
        if (charAt == '*' || charAt == '/') return 3;
        else if (charAt == '+' || charAt == '-') return 2;
        else if (charAt == '(') return 1;
        else if (charAt == ')') return -1;
        else return 0;
    }

    public static double getAnswer(String rpn) {
        String operation = new String();
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < rpn.length(); i++) {
            if (rpn.charAt(i) == ' ') continue;

            if (getPrecedence(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' && getPrecedence(rpn.charAt(i)) == 0) {
                    operation += rpn.charAt(i++);
                    if (i == rpn.length()) break;
                }
                stack.push(Double.parseDouble(operation));
                operation = "";
            }
            if (getPrecedence(rpn.charAt(i)) > 1) {
                double a = stack.pop(), b = stack.pop();
                if (rpn.charAt(i) == '+') stack.push(b + a);
                if (rpn.charAt(i) == '-') stack.push(b - a);
                if (rpn.charAt(i) == '*') stack.push(b * a);
                if (rpn.charAt(i) == '/') stack.push(b / a);
            }
        }
        return stack.pop();
    }

}

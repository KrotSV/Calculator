package com.tsystems.javaschool.tasks;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

/**
 * Created by KrotSV on 17.09.2016.
 */
class TranslateNotation {

    // Будем преобразовывать выражени в польскую нотацию методом стеков
    static ArrayList<String> polishNotation(String input) {
        Stack<String> stack = new Stack<String>();  // Создаем стек
        ArrayList<String> output = new ArrayList<String>(); // Создаем выходной стек
        boolean flag = false;   // Флаг, говорящий о том, что в строке было последним: цифра/точка или операция
        String num = "";    //Переменная в которой из цифр будет составляться число
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);       // Символ, считываемый из строки
            String symbol = String.valueOf(c);
            switch (symbol) {

                default: {                  // Если приходит цифра или точка
                    if (flag) {
                        num = num.concat(symbol);
                        flag = true;
                        break;
                    }
                    else {
                        num = symbol;
                        flag = true;
                        break;
                    }
                }


                case "(": {
                    if (!flag) {
                        stack.push(symbol);// Проверка на пустоту стека, чтобы можно было определить приоритет операций
                        flag = false;
                        break;
                    } else {                         // Костыль, на случай выражения, где перед скобкой нет знака умножения: a(b+c)
                        output.add(num);             // Я счел такую запись выражения очень распространенной, чтобы ее можно было игнорировать
                        num = "";
                        boolean t = true;   // Вспомогательный флаг
                        while (stack.size() > 0 && t) {
                            String s = stack.peek();
                            if ((Objects.equals(s, "/") || Objects.equals(s, "*")) && stack.size() > 0) {    // Проверка приоритета операции
                                output.add(stack.pop());
                            }
                            else {
                                t = false;
                            }
                        }
                        stack.push("*");
                        stack.push(symbol);
                        flag = false;
                        break;
                    }

                }





                case "/":
                case "*": {
                    if (flag) {
                        output.add(num);
                        num = "";
                    }
                    boolean t = true;
                    while (stack.size() > 0 && t) {
                        String s = stack.peek();
                        if ((Objects.equals(s, "/")) || (Objects.equals(s, "*"))) {
                            output.add(stack.pop());
                        }
                        else {
                            t = false;
                        }
                    }
                    stack.push(symbol);         // Если стек пуст, просто кидаем в него элемент
                    flag = false;
                    break;
                }


                case "-":
                case "+": {
                    if (flag) {
                        output.add(num);
                        num = "";
                    }
                    boolean t = true;
                    while (stack.size() > 0 && t) {
                        String s = stack.peek();
                        if ((Objects.equals(s, "/")) || (Objects.equals(s, "*")) || (Objects.equals(s, "-")) || (Objects.equals(s, "+"))) {
                            output.add(stack.pop());
                        }
                        else {
                            t = false;
                        }
                    }
                    stack.push(symbol);         // Если стек пуст, просто кидаем в него элемент
                    flag = false;
                    break;
                }



                case ")": {
                    if (flag) {
                        output.add(num);
                        num = "";
                    }
                    num = "";
                    flag = false;
                    int steps = stack.search("(");
                    for (int j = 0; j < steps - 1; j++) {
                        output.add(stack.pop());
                    }
                    stack.pop();
                    break;
                }

            }
        }
        if (flag) output.add(num);
        int k = stack.size();           //выгружаем оставшиеся операторы из стека
        for (int l = 0; l < k; l++){
            output.add(stack.pop());
        }
        return output;
    }
}

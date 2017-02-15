package com.tsystems.javaschool.tasks;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by KrotSV on 17.09.2016.
 */
class Evaluator {
    static String calculation(ArrayList<String> polishExpression){ // Метод-решатель выражения в польской нотации
        Stack<Double> solveStack = new Stack<>();
        for (String element : polishExpression) {
            switch (element) {
                default: {  // Если элемент есть число
                    solveStack.push(Double.valueOf(element));
                    break;
                }
                case "*": {
                    double a = solveStack.pop();
                    double b = solveStack.pop();
                    solveStack.push(b * a);
                    break;
                }
                case "/": {
                    double a = solveStack.pop();
                    double b = solveStack.pop();
                    solveStack.push(b / a);
                    break;
                }
                case "+": {
                    double a = solveStack.pop();
                    double b = solveStack.pop();
                    solveStack.push(b + a);
                    break;
                }
                case "-": {
                    double a = solveStack.pop();
                    double b = solveStack.pop();
                    solveStack.push(b - a);
                    break;
                }
            }
        }
        return String.format("%.4f", solveStack.pop());
    }
}



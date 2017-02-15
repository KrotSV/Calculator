package com.tsystems.javaschool.tasks;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

/**
 * Created by KrotSV on 17.09.2016.
 */
public class CalculatorImpl implements Calculator {


    @Override
    public String evaluate(String input) {
        // Убираем пробелы в расчете, что они не влияют на возможность вычислить выражение
        String clear = input.replaceAll(" ", "");
        if (clear.isEmpty())
            return null;
        if (!CheckExpression.check(clear))
            return null;
        return Evaluator.calculation(TranslateNotation.polishNotation(clear));
    }
    public CalculatorImpl(){}
}


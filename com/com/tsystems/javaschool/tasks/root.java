package com.tsystems.javaschool.tasks;

/**
 * Created by KrotSV on 19.09.2016.
 */
public class root {
    public static void main(String[] args) {
        String in = "5/0";
        CalculatorImpl c = new CalculatorImpl();
        System.out.printf("answer " + c.evaluate(in));
    }
}

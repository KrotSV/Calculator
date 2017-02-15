package com.tsystems.javaschool.tasks;

/**
 * Created by KrotSV on 17.09.2016.
 */
class CheckExpression {

    // Проверяем возможность вычислить выражение
    static boolean check(String in) {
        boolean a = checkSymbols(in);
        boolean b = checkFirst(in);
        boolean c = checkLast(in);
        boolean d = checkDeci(in);
        boolean e = checkBrackets(in);
        boolean f = checkOperators(in);
        boolean g = checkWrongBrackets(in);
        return a && b && c && d && e && f && g;
    }

    // Проверяем наличие символов, не дающих вычислить выражение
    private static boolean checkSymbols(String input) {
        String s = input.replaceAll("[0-9().+*-/]", ""); // Убираем все "законные" символы
        return s.isEmpty();
    }

    // Проверяем первый символ выражения. Он должен быть цифрой, минусом или скобкой
    private static boolean checkFirst(String input) {
        char c = input.charAt(0);
        String s = Character.toString(c);
        return s.matches("[0-9(-]");
    }

    // Проверяем последний символ. Он должен быть цифрой или скобкой
    private static boolean checkLast(String input) {
        int end = input.length();
        char c = input.charAt(end - 1);
        String s = Character.toString(c);
        return s.matches("[0-9)]");
    }

    // Проверка на несколько десятичных разделителей в одном числе
    private static boolean checkDeci(String input) {
        String s = input.replaceAll("[0-9]", ""); // Уюираем все цифры и ищем сочетание ".."
        return !s.contains("..");
    }

    // Проверка на неоткрытые/незакрытые скобки
    private static boolean checkBrackets(String input) {
        String o = input.replaceAll("[0-9).+*-/]", ""); // Оставляем только открывающиеся скобки
        int open = o.length();                          // Считаем их
        String c = input.replaceAll("[0-9(.+*-/]", ""); // Оставляем только закрывающиеся скобки
        int close = c.length();                         // Считаем их
        return open == close;
    }

    // Проверка на верный порядок скобок (закрытые после открытых)
    private static boolean checkWrongBrackets(String input) {
        String s = input.replaceAll("[0-9.+*-/]", ""); // Оставляем только скобки
        while (s.contains("()")) {
            s = s.replace("()", "");
        }
        return !s.contains(")(");
    }

    // Проверка на два оператора подряд
    private static boolean checkOperators(String input) {
        String[] combinations = new String[] {"++", "+-", "+*", "+/", "-+", "--", "-*", "-/", "*+", "*-", "**", "*/", "/+", "/-", "/*", "//","-)","+)","*)","/)","(+","(/","(*",".+",".-",".*","./",".(",".)","*.","+.","-.","/.","(.",")."};
        // масив сочетаний операторов
        for (String  i : combinations) { // При наличии одного из сочетанийвыкидываем исключение
            if (input.contains(i))
                return false;
        }
        return true;
    }
}

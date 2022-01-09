package com.epam.izh.rd.online.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserString {

    /**
     * Разбивает строку в массив по пробелам
     */
    public static List<String> getExpressionAsArray(String expression) {
        List<String> expressionArr = new ArrayList<>(Arrays.asList(expression.split(" +")));
        parseBrackets(expressionArr);
        return expressionArr;
    }

    /**
     * Разбиение выражение по элементам типа:
     * (-2) - ((-4) * 3.5)
     * на:
     * -2 - ( -4 * 3.5 )
     */
    public static void parseBrackets(List<String> expressionArr) {
        for (int i = 0; i < expressionArr.size(); i++) {
            String elem = expressionArr.get(i);
            if (elem.matches("\\(*-?[.\\d]+\\)*")) {
                if (elem.matches("\\(+-*[.\\d]+\\)+")) {
                    elem = elem.replaceFirst("\\(", "").replaceFirst("\\)", "");
                }
                if (elem.contains("(")) {
                    Pattern pattern = Pattern.compile("\\(");
                    Matcher matcher = pattern.matcher(elem);
                    while (matcher.find()) {
                        expressionArr.add(i, "(");
                        elem = elem.replaceFirst("\\(", "");
                        i++;
                    }
                }
                if (elem.contains(")")) {
                    Pattern pattern = Pattern.compile("\\)");
                    Matcher matcher = pattern.matcher(elem);
                    while (matcher.find()) {
                        expressionArr.add(i + 1, ")");
                        elem = elem.replaceFirst("\\)", "");
                    }
                }
                expressionArr.set(i, elem);
            }
        }
    }
}

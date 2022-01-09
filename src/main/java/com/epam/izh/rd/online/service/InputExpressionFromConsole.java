package com.epam.izh.rd.online.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class InputExpressionFromConsole {

    /**
     * Чтение с консоли, ожидание вверного ввода
     */
    public static String readInputString() {
        System.out.println("Введите математическое выражение: ");
        String expression = null;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            expression = bufferedReader.readLine();
            isUnValidInput(expression);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return expression;
    }

    /*
     * Проверка что введенная строка верна
     * */
    public static void isUnValidInput(String expression) {
        if (!Validation.isStringNull(expression) && !Validation.containsForeignElement(expression)) {
            Validation.isContainsDuplicateElements(ParserString.getExpressionAsArray(expression));
        }
    }

}

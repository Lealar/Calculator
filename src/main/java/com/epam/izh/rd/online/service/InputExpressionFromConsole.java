package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.exception.EmptyInputStringException;
import com.epam.izh.rd.online.exception.WrongInputStringExpression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class InputExpressionFromConsole {

    /**
     * Чтение с консоли, ожидание вверного ввода
     */
    public static String readInputString() {
        String expression = null;
        System.out.println("Введите математическое выражение");

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                try {
                    expression = bufferedReader.readLine();
                    if (expression == null) {
                        throw new EmptyInputStringException();
                    }
                    Validation.containsForeignElement(expression);
                    break;
                } catch (WrongInputStringExpression wrongInputStringExpression) {
                    wrongInputStringExpression.printStackTrace();
                    System.out.println("В введенной строке с выражением не должно встречаться ничего, " +
                            "кроме цифр и знаков математических операций. Дробные числа записываются через точку." +
                            "\nВведите корректное выражение:");
                } catch (EmptyInputStringException exception) {
                    exception.printStackTrace();
                    System.out.println("Введена пустая строка");
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return expression;
    }


}

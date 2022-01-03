package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.exception.MathematicSymbolException;
import com.epam.izh.rd.online.exception.WrongInputStringExpression;

import java.util.List;
import java.util.Set;


public class Validation {
    /**
     * Метод создает регулярку по используемым математическим операциям
     */
    public static void containsForeignElement(String expression) throws WrongInputStringExpression {
        String regex = "[0-9 .";
        Set<String> avaibleOperations = MathOperation.getAvaibleOperation();
        regex += avaibleOperations.stream().reduce((a, b) -> a + "\\" + b).orElse(null) + "]+";
        if (!expression.matches(regex)) {
            throw new WrongInputStringExpression("Некорректно введено выражение");
        }
    }

    /**
     * Проверка на повторение математического знака
     */
    public static void validationArray(List<String> expressionArr) {
        Set<String> availableMathOperation = MathOperation.getAvaibleOperation();
        for (String elem : expressionArr) {
            if (!availableMathOperation.contains(elem) && elem.matches("\\D+")) { //elem.matches("\\D{2,}")
                try {
                    throw new MathematicSymbolException("Неподдерживаемая операция " + elem);
                } catch (MathematicSymbolException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
    }

}

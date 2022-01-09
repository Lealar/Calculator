package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.exception.EmptyInputStringException;
import com.epam.izh.rd.online.exception.MathematicSymbolException;
import com.epam.izh.rd.online.exception.WrongInputStringExpression;

import java.util.List;
import java.util.Set;


public class Validation {
    /**
     * Метод создает регулярку по используемым математическим операциям
     */
    public static boolean containsForeignElement(String expression) {
        String regex = "[0-9 .";
        Set<String> avaibleOperations = MathOperation.getAvailableOperation();
        regex += avaibleOperations.stream().reduce((a, b) -> a + "\\" + b).orElse(null) + "]+";
        if (!expression.matches(regex)) {
            throw new WrongInputStringExpression("Введена неподдерживаемая операция");
        }
        return false;
    }

    public static boolean isStringNull(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new EmptyInputStringException("Введена пустая строка");
        }
        return false;
    }

    /**
     * Проверка на повторение математического знака
     */
    public static boolean isContainsDuplicateElements(List<String> expressionArr) {
        Set<String> availableMathOperation = MathOperation.getAvailableOperation();
        for (String elem : expressionArr) {
            if (!availableMathOperation.contains(elem) && elem.matches("\\D+")) {
                throw new MathematicSymbolException("Неподдерживаемая операция " + elem);
            }
        }
        return false;
    }

}

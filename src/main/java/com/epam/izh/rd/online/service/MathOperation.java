package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.exception.DivisionByZero;
import com.epam.izh.rd.online.exception.MathematicSymbolException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MathOperation {
    /**
     * Присваивание приоритета операции
     */
    private static final Map<String, Integer> operationPriority = new HashMap<>();

    static {
        operationPriority.put("(", 0);
        operationPriority.put(")", 0);
        operationPriority.put("+", 1);
        operationPriority.put("-", 1);
        operationPriority.put("*", 2);
        operationPriority.put("/", 2);
        operationPriority.put("^", 3);
    }

    public static Set<String> getAvaibleOperation() {
        return operationPriority.keySet();
    }

    public static void addOperationPriority(String sign, Integer priority) {
        operationPriority.put(sign, priority);
    }


    public static Integer getOperationPriority(String operation) {
        return operationPriority.get(operation);
    }

    public static boolean isAddElem(String newElem, String elemFromStack) {
        if (operationPriority.get(elemFromStack) == null && operationPriority.get(elemFromStack) == null) {
            String exceptionMessage = operationPriority.get(elemFromStack) == null ? elemFromStack : newElem;
            try {
                throw new MathematicSymbolException("Неподдерживаемая операция " + exceptionMessage);
            } catch (MathematicSymbolException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        return operationPriority.get(newElem) > operationPriority.get(elemFromStack);
    }

    /**
     * Проводит мат операцию
     *
     * @param operation     какую математическую операцию необходимо произвести
     * @param firstOperand  правая часть мат. операции
     * @param secondOperand левая часть мат. операции
     */
    public static Double doMathOperation(String operation, String firstOperand, String secondOperand) {

        switch (operation) {
            case "+":
                return Double.parseDouble(secondOperand) + Double.parseDouble(firstOperand);
            case "-":
                return Double.parseDouble(secondOperand) - Double.parseDouble(firstOperand);
            case "*":
                return Double.parseDouble(secondOperand) * Double.parseDouble(firstOperand);
            case "/":
                try {
                    if (firstOperand.equals("0")) {
                        throw new DivisionByZero("Деление на ноль запрещено");
                    }
                } catch (DivisionByZero divisionByZero) {
                    divisionByZero.printStackTrace();
                    System.exit(1);
                }

                return Double.parseDouble(secondOperand) / Double.parseDouble(firstOperand);
            case "^":
                return Math.pow(Double.parseDouble(secondOperand), Double.parseDouble(firstOperand));
        }
        return null;
    }
}

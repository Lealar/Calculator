package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.exception.WrongInputStringExpression;
import com.epam.izh.rd.online.service.InputExpressionFromConsole;
import com.epam.izh.rd.online.service.MathOperation;
import com.epam.izh.rd.online.service.ParserString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * Основной класс производящий выисления
 */
public class JavaCalculator {

    public Double solveExpressionFromConsole() throws IllegalArgumentException {
        return calculate(InputExpressionFromConsole.readInputString());
    }

    public Double solveExpression(String expression) throws IllegalArgumentException {
        InputExpressionFromConsole.isUnValidInput(expression);
        return calculate(expression);
    }


    private Double calculate(String expression) {
        List<String> mathematicalExpressionAsArr = ParserString.getExpressionAsArray(
                expression);

        Stack<String> number = new Stack<>();   // Стэк для чисел
        Stack<String> signs = new Stack<>();    // Стэк для мат. операций

        for (String elem : mathematicalExpressionAsArr) {
            if (elem.matches("-*[.\\d]+")) { //Если элемент - число, добавить в стэк с числами
                number.push(elem);
            } else if (elem.equals("(")) {    //Если элемент - открывающая скобка, добавить в стэк с знаками
                signs.push(elem);
            } else if (elem.equals(")")) {       //Если элемент - закрывающая скобка, вычислять значения пока не дойдем до закрывающей скобки
                while (!signs.peek().equals("(")) {
                    number.push(Objects.requireNonNull(
                            MathOperation.doMathOperation(signs.pop(), number.pop(), number.pop())).toString());
                }
                signs.pop();
            } else { //Если элемент мат. операция
                if (signs.empty()) { //Если стек пуст добавить операцию в стек
                    signs.push(elem);
                } else if (MathOperation.compareOperations(elem, signs.peek())) { //Если приоритет данной операции выше добавить в стек
                    signs.push(elem);
                } else {
                    while (!signs.empty() && !MathOperation.compareOperations(elem, signs.peek())) { //Проводить вычисления до тех пор, пока не встретится операция с более высоким приоритетом
                        checkStackSize(number, signs);
                        number.push(MathOperation.doMathOperation(signs.pop(), number.pop(), number.pop()).toString());
                    }
                    signs.push(elem);
                }

            }
        }
        while (!signs.empty()) {
            checkStackSize(number, signs);
            number.push(MathOperation.doMathOperation(signs.pop(), number.pop(), number.pop()).toString());
        }

        return BigDecimal.valueOf(Double.parseDouble(number.pop())).setScale(3, RoundingMode.HALF_UP).doubleValue();
    }


    private void checkStackSize(Stack<String> number, Stack<String> signs) {
        if (signs.size() == 0 || number.size() <= 1) {
            throw new WrongInputStringExpression("Некорректно введено выражение");
        }
    }

}

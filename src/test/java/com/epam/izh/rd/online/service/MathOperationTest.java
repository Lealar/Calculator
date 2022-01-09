package com.epam.izh.rd.online.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.jupiter.api.Assertions.*;

class MathOperationTest {

    static Stream<Arguments> mathOperationsCompare() {
        return Stream.of(
                Arguments.arguments("+", "-", false),
                Arguments.arguments("*", "/", false),
                Arguments.arguments("*", "+", true),
                Arguments.arguments("/", "-", true),
                Arguments.arguments("/", "-", true)
        );
    }

    @ParameterizedTest
    @MethodSource("mathOperationsCompare")
    void compareOperations_compareMultiplicationAndAddition(String arg1, String arg2, boolean expectRes) {
        boolean res = MathOperation.compareOperations(arg1, arg2);
        assertThat(expectRes, equalTo(res));
    }

    @Test
    void compareOperations_UnsupportedOperation() {
        String operation = "+";
        String operand1 = "%";
        assertThrows(IllegalArgumentException.class,
                () -> MathOperation.compareOperations(operation, operand1));
    }


    static Stream<Arguments> mathOperationsForAddition() {
        return Stream.of(
                Arguments.arguments("1", "2", 3.),
                Arguments.arguments("10", "5", 15.),
                Arguments.arguments("-5", "2", -3.)
        );
    }

    @ParameterizedTest
    @MethodSource("mathOperationsForAddition")
    void doMathOperation_addition(String operand1, String operand2, Double exp) {
        String operation = "+";
        Double result = MathOperation.doMathOperation(operation, operand1, operand2);
        assertThat(result, equalTo(exp));
    }

    static Stream<Arguments> mathOperationsForMinus() {
        return Stream.of(
                Arguments.arguments("10", "15", 5.),
                Arguments.arguments("0", "6", 6.),
                Arguments.arguments("-5", "-10", -5.)
        );
    }

    @ParameterizedTest
    @MethodSource("mathOperationsForMinus")
    void doMathOperation_minus(String operand1, String operand2, Double exp) {
        String operation = "-";
        Double result = MathOperation.doMathOperation(operation, operand1, operand2);
        assertThat(result, equalTo(exp));
    }

    static Stream<Arguments> mathOperationsForMultiplication() {
        return Stream.of(
                Arguments.arguments("3", "5", 15.),
                Arguments.arguments("0", "6", 0.),
                Arguments.arguments("6", "0", 0.),
                Arguments.arguments("-6", "-3", 18.)
        );
    }

    @ParameterizedTest
    @MethodSource("mathOperationsForMultiplication")
    void doMathOperation_multiplication(String operand1, String operand2, Double exp) {
        String operation = "*";
        Double result = MathOperation.doMathOperation(operation, operand1, operand2);
        assertThat(result, equalTo(exp));
    }

    static Stream<Arguments> mathOperationsForDivision() {
        return Stream.of(
                Arguments.arguments("5", "20", 4.),
                Arguments.arguments("6", "0", 0.),
                Arguments.arguments("-3", "-6", 2.),
                Arguments.arguments("-6", "-3", 0.5),
                Arguments.arguments("18", "74", 4.1)
        );
    }

    @ParameterizedTest
    @MethodSource("mathOperationsForDivision")
    void doMathOperation_division(String operand1, String operand2, Double exp) {
        String operation = "/";
        Double result = MathOperation.doMathOperation(operation, operand1, operand2);
        assertThat(result, closeTo(exp, 0.2));
    }

    @Test
    void doMathOperation_divisionByZero() {
        String operand1 = "0";
        String operand2 = "4";
        String operation = "/";
        assertThrows(IllegalArgumentException.class,
                () -> MathOperation.doMathOperation(operation, operand1, operand2));
    }

    static Stream<Arguments> mathOperationsForExponentiation() {
        return Stream.of(
                Arguments.arguments("2", "2", 4.),
                Arguments.arguments("0", "5", 1.),
                Arguments.arguments("5", "0", 0.),
                Arguments.arguments("2", "-6", 36.)
        );
    }

    @ParameterizedTest
    @MethodSource("mathOperationsForExponentiation")
    void doMathOperation_exponentiation(String operand1, String operand2, Double exp) {
        String operation = "^";
        Double result = MathOperation.doMathOperation(operation, operand1, operand2);
        assertThat(result, equalTo(exp));
    }

    @Test
    void doMathOperation_UnsupportedOperation() {
        String operation = "%";
        String operand1 = "1";
        String operand2 = "2";
        assertThrows(IllegalArgumentException.class,
                () -> MathOperation.doMathOperation(operation, operand1, operand2));
    }

    static Stream<Arguments> mathOperationsPriorityCheck() {
        return Stream.of(
                Arguments.arguments("(", 0),
                Arguments.arguments(")", 0),
                Arguments.arguments("+", 1),
                Arguments.arguments("-", 1),
                Arguments.arguments("*", 2),
                Arguments.arguments("/", 2),
                Arguments.arguments("^", 3)

        );
    }

    @ParameterizedTest
    @MethodSource("mathOperationsPriorityCheck")
    void operationPriorityTest(String operation, int priority) {
        assertThat(priority, equalTo(MathOperation.getOperationPriority(operation)));

    }


}
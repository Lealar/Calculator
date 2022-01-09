package com.epam.izh.rd.online.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JavaCalculatorTest {
    static JavaCalculator javaCalculator;

    @BeforeAll
    static void setUp() {
        javaCalculator = new JavaCalculator();
    }

    static Stream<Arguments> mathExpression() {
        return Stream.of(
                Arguments.arguments("4.2 + 2 * 3 / 3 - 6.1", 0.1),
                Arguments.arguments("2 + 5", 7.),
                Arguments.arguments("( 1 + 2 - 3 * 4 / 5 )", 0.6),
                Arguments.arguments("(-2) - ((-4) * 3.5)", 12.)
        );
    }

    @ParameterizedTest
    @MethodSource("mathExpression")
    void solveExpression(String expression, Double result) {
        Double expResult = javaCalculator.solveExpression(expression);
        assertThat(result, closeTo(expResult, 0.2));
    }

    static Stream<Arguments> mathExpression_WithException() {
        return Stream.of(
                Arguments.arguments(""),
                Arguments.arguments("2 ++ 5"),
                Arguments.arguments("2 / 0"),
                Arguments.arguments("2 % 7"),
                Arguments.arguments("( 2 + 7 ) +")
        );
    }

    @ParameterizedTest
    @MethodSource("mathExpression_WithException")
    void solveExpression_ThrowException(String expression) {

        assertThrows(IllegalArgumentException.class,
                () -> javaCalculator.solveExpression(expression));
    }
}
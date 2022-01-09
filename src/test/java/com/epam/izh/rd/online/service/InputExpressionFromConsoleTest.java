package com.epam.izh.rd.online.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;


class InputExpressionFromConsoleTest {

    static Stream<Arguments> mathOperationsCompare() {
        return Stream.of(
                Arguments.arguments(null, true),
                Arguments.arguments("2 & 8", true),
                Arguments.arguments("2 ++ 2", true),
                Arguments.arguments("2 ++ 2", true)
        );
    }

    @ParameterizedTest
    @MethodSource("mathOperationsCompare")
    void isUnValidInput(String expression) {
        assertThrows(IllegalArgumentException.class,
                () -> InputExpressionFromConsole.isUnValidInput(expression));
    }
}
package com.epam.izh.rd.online.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ParserStringTest {

    @Test
    void getExpressionAsArray() {
        String expressionAsStr = "( 4.2 + 2 * 3 / 3 - 6.1 )";
        List<String> expect = new ArrayList<>(Arrays.asList("(", "4.2", "+", "2", "*", "3", "/", "3", "-", "6.1", ")"));
        List<String> actual = ParserString.getExpressionAsArray(expressionAsStr);
        assertThat(expect, is(actual));
    }

    static Stream<Arguments> expressionWithBrackets() {
        return Stream.of(
                Arguments.arguments(new ArrayList<>(Arrays.asList("-2", "-", "(", "-4", "*", "3.5", ")")),
                        new ArrayList<>(Arrays.asList("(-2)", "-", "((-4)", "*", "3.5", ")"))),
                Arguments.arguments(new ArrayList<>(Arrays.asList("-2", "-", "(", "4", "*", "-3.5", ")")),
                        new ArrayList<>(Arrays.asList("(-2)", "-", "(", "4", "*", "(-3.5))")))
        );
    }

    @ParameterizedTest
    @MethodSource("expressionWithBrackets")
    void parseBrackets(List<String> expect, List<String> actual) {
        ParserString.parseBrackets(actual);
        assertThat(expect, is(actual));
    }
}

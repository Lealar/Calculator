package com.epam.izh.rd.online.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @Test
    void containsForeignElement_ValidExpression() {
        String expression = "( 1 + 2 - 3 * 4 / 5 )";
        assertFalse(Validation.containsForeignElement(expression));
    }

    @Test
    void containsForeignElement_UnValidExpression() {
        String expression = "1 % 2";
        assertThrows(IllegalArgumentException.class,
                () -> Validation.containsForeignElement(expression));
    }

    @Test
    void isStringNull_InputEmptyString() {
        String expression = "";
        assertThrows(IllegalArgumentException.class,
                () -> Validation.isStringNull(expression));
    }

    @Test
    void isStringNull_InputNull() {
        String expression = null;
        assertThrows(IllegalArgumentException.class,
                () -> Validation.isStringNull(expression));
    }

    @Test
    void isContainsDuplicateElements_CorrectInput() {
        String expression = "( 1 + 2 - 3 * 4 / 5 )";
        List<String> expressionAsArr = ParserString.getExpressionAsArray(expression);
        assertFalse(Validation.isContainsDuplicateElements(expressionAsArr));
    }

    @Test
    void isContainsDuplicateElements_DuplicatePlus() {
        String expression = "1 ++ 2";
        List<String> expressionAsArr = ParserString.getExpressionAsArray(expression);
        assertThrows(IllegalArgumentException.class,
                () -> Validation.isContainsDuplicateElements(expressionAsArr));
    }
}
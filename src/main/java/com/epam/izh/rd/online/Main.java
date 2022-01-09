package com.epam.izh.rd.online;

import com.epam.izh.rd.online.repository.JavaCalculator;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(new JavaCalculator().solveExpressionFromConsole());
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
        }
    }
}

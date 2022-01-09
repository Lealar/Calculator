package com.epam.izh.rd.online;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;


@SuiteDisplayName("Calculator Test Suite")
@Suite
@SelectPackages({"com.epam.izh.rd.online.service",
        "com.epam.izh.rd.online.repository"})
public class CalculatorTestSuit {
}

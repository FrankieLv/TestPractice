package com.frankie.basic;

import org.junit.Test;

public class Junit4StandardTest {
    @Test(expected = ArithmeticException.class)
    public void testDivideByZeroException() {
        int result = 1 / 0;
    }
}

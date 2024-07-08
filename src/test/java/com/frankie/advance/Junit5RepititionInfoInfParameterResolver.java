package com.frankie.advance;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

public class Junit5RepititionInfoInfParameterResolver {

    @RepeatedTest(1)
    void repeatedTest(RepetitionInfo repetitionInfo) {
        System.out.println(repetitionInfo.getCurrentRepetition());
    }
}

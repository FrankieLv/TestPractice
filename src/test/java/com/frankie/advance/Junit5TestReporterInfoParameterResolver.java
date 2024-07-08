package com.frankie.advance;

import org.junit.jupiter.api.*;

public class Junit5TestReporterInfoParameterResolver {

    @BeforeEach
    void setup(TestInfo testInfo, RepetitionInfo repetitionInfo, TestReporter testReporter){
        testReporter.publishEntry(testInfo.getDisplayName());
        testReporter.publishEntry(String.valueOf(repetitionInfo.getCurrentRepetition()));
    }

    @RepeatedTest(1)
    void SimpleTest(TestReporter testReporter){
        testReporter.publishEntry("SimpleTest");
    }
}

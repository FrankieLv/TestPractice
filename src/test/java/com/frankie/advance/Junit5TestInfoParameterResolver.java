package com.frankie.advance;

import org.junit.jupiter.api.*;

@DisplayName("Test Info Parameter Resolver")
public class Junit5TestInfoParameterResolver {

    @BeforeAll
    static void setUpBeforeClass(TestInfo testInfo) throws Exception {
        System.out.println("-----------------------------------------");
        System.out.println("DisplayName:" + testInfo.getDisplayName());
        System.out.println("TestClass:" + testInfo.getTestClass());
        System.out.println("TestMethod:" + testInfo.getTestMethod());
        System.out.println("TestTags:" + testInfo.getTags());
        System.out.println("-----------------------------------------");
    }

    @BeforeEach
    void setUp(TestInfo testInfo){
        System.out.println("-----------------------------------------");
        System.out.println("DisplayName:" + testInfo.getDisplayName());
        System.out.println("TestClass:" + testInfo.getTestClass());
        System.out.println("TestMethod:" + testInfo.getTestMethod());
        System.out.println("TestTags:" + testInfo.getTags());
        System.out.println("-----------------------------------------");
    }

    @Tag("prod")
    @DisplayName("simple test")
    @Test
    public void simpleTest(TestInfo testInfo) {
        System.out.println("-----------------------------------------");
        System.out.println("DisplayName:" + testInfo.getDisplayName());
        System.out.println("TestClass:" + testInfo.getTestClass());
        System.out.println("TestMethod:" + testInfo.getTestMethod());
        System.out.println("TestTags:" + testInfo.getTags());
        System.out.println("-----------------------------------------");
    }
}

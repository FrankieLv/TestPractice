package com.frankie.advance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@DisplayName("Junit  Dynamic Test")
public class Junit5DynamicTest {

    @TestFactory
    Iterator<DynamicTest> dynamicTestsWithIterator() {
        return Arrays.asList(
                dynamicTest("Dynamic test 1", () -> assertTrue(true)),
                dynamicTest("Dynamic test 2", () -> assertTrue(true))
        ).iterator();
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestsWithStream() {
        return Stream.of(
                    dynamicTest("Dynamic test 1", ()-> assertTrue(true)),
                    dynamicTest("Dynamic test 2", () -> assertTrue(true))
        );
    }

    @TestFactory
    Stream<DynamicTest> userShouldbeinUserLevel() {
        final var userLevelFromDB = queryData();
        return Stream.of("Java").map(
                s -> dynamicTest(String.format("[%s] in DB", s),()->assertThat(userLevelFromDB, hasItem(s)))
        );
    }


    private List<String> queryData(){
        return List.of("Java", "Angular");
    }
}

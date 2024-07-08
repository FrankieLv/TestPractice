package com.frankie.basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Junit5StandardHamcrestTest {

    private final List<String> LIST = List.of("Java","Typescript", "Angular");

    @DisplayName("The list size has 3")
    @Test
    void listShouldEqualSize3() {
       assertThat(LIST, hasSize(3));
    }

    @DisplayName("The list should contain Java String")
    @Test
    void listShouldContainJavaString(){
        //given
        //when
        //then

        assertThat(LIST, hasItem("Java"));
    }

}

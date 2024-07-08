package com.frankie.basic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class Junit5StandardConditionTest {
    @Test
    @DisabledOnJre(value= JRE.JAVA_21, disabledReason = "Do not test it in JRE 21")
    void disableWhenJRE8(){

    }

    @Test
    @DisabledForJreRange(max= JRE.JAVA_14, disabledReason = "Do not test it in JRE 14")
    void disableRangeforJRE14(){
        System.out.println(System.getProperty("java.version"));
    }

    @Test
    @EnabledOnOs(value= OS.MAC)
    void enableWhenMac(){

    }

    @Test
    @EnabledIf("customCondition")
    void enableIf(){

    }

    private boolean customCondition(){
        return Boolean.FALSE;
    }
}

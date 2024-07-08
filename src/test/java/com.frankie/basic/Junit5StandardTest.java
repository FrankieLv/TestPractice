package com.frankie.basic;


import org.junit.jupiter.api.*;


import java.time.Duration;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Immutable List Feature Test")
class Junit5StandardTest {
    private final List<String> LIST = List.of("Java","Typescript", "Angular");

    @Tag("dev")
    @DisplayName("Nested List Test")
    @Nested
    class NestedListTest{
        @DisplayName("The list size should equal 3")
        @ENV.prod
        void listShouldEqualSize3() {
            assertEquals(LIST.size(), 3);
        }

        @DisplayName("The list should contain Java String")
        @Test
        void listShouldContainJavaString(){
            //given
            //when
            boolean exists = LIST.stream().anyMatch(s -> s.equals("Java"));
            //then
            assertTrue(exists);
        }

        @DisplayName("The list could not be updated")
        @Test
        void listCouldNotBeUpdated(){
            //given
            //when
            //then
            assertThrows(UnsupportedOperationException.class, () -> LIST.remove(0));
        }

        @DisplayName("The list only support read operation")
        @Test
        void listOnlySupportReadOperation(){
            assertAll("Assert read and remove operations", Stream.of(
                    //() -> assertEquals("Java", LIST.remove(0)),
                    () -> assertEquals("Typescript", LIST.get(1)),
                    () -> assertEquals("Angular", LIST.get(2))
            ));
        }
    }

    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Nested Repeated Test")
    @Nested
    class NestRepeatedTest{
        @Order(1)
        @RepeatedTest(3)
        void repeatedTest(){
            assertTrue(LIST.contains("Java"));
        }

        @Order(2)
        @RepeatedTest(3)
        void repeatedWithIndex(RepetitionInfo repetitionInfo){
            final var element = switch(repetitionInfo.getCurrentRepetition()){
                case 1 : yield "Java";
                case 2 : yield "Typescript";
                case 3 : yield "Angular";
                default : yield "";
            };
            assertEquals(element, LIST.get(repetitionInfo.getCurrentRepetition() - 1));
        }

        @Order(3)
        @DisplayName("Repeat Immutable List Element Detailed Info ==>")
        @RepeatedTest(value= 3, name="{displayName} {CurrentRepetition}/{totalRepetitions}")
        void repeatedWithDetailedInfo(RepetitionInfo repetitionInfo){
            final var element = switch(repetitionInfo.getCurrentRepetition()){
                case 1 : yield "Java";
                case 2 : yield "Typescript";
                case 3 : yield "Angular";
                default : yield "";
            };
            assertEquals(element, LIST.get(repetitionInfo.getCurrentRepetition() - 1));
        }

        @Order(4)
        @DisplayName("Repeat Immutable Long Name ==>")
        @RepeatedTest(value= 3, name=RepeatedTest.LONG_DISPLAY_NAME)
        void repeatedWithLongName(TestInfo testInfo, RepetitionInfo repetitionInfo){
            assertEquals(testInfo.getDisplayName(), String.format("Repeat Immutable Long Name ==> :: repetition %d of %d", repetitionInfo.getCurrentRepetition(), repetitionInfo.getTotalRepetitions()));
        }

    }

    @DisplayName("Nested Timeout Test")
    @Nested
    class NestedTimeoutTest{
        @DisplayName("Simple test for timeout API")
        @Test
        @Timeout(value=3, unit = TimeUnit.SECONDS)
        void timeoutAssertion() throws InterruptedException {
            final var blockingArray = new  ArrayBlockingQueue<String>(5);
            assertTimeout(Duration.ofSeconds(2), ()->{
                blockingArray.take();
            });
        }

        @DisplayName("Simple test for timeout API Preemptively")
        @Test
        void timeoutAssertionPreemptively(){
            final var blockingArray = new  ArrayBlockingQueue<String>(5);
            assertTimeoutPreemptively(Duration.ofSeconds(2), ()->{
                blockingArray.take();
            });
        }

        @DisplayName("Simple test for timeout API - put element after timeout")
        @Test
        @Timeout(value=10, unit = TimeUnit.SECONDS)
        void timeoutAssertionWithPut(){
            final var blockingArray = new  ArrayBlockingQueue<String>(5);

            new Thread(() -> {
                try{
                    TimeUnit.SECONDS.sleep(3);
                    blockingArray.put("Hello");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }).start();

            assertTimeout(Duration.ofSeconds(2), ()->{
                assertEquals("Hello", blockingArray.take());
            });
        }
    }


    private static String ENV;
    @BeforeAll
    static void setup(){
        ENV = System.getenv().getOrDefault("ENV", "N/A");
    }

    @DisplayName("Nested Test Assumpition")
    @Nested
    class NestedAssumptionTest{
        @DisplayName("Only Test on CI Pipeline")
        @Test
        void onlyTestOnCIPipeline(){
            Assumptions.assumingThat(()->"CI".equals(ENV), ()->{
                assertEquals(LIST.size(), 3);
            });
       /* Assumptions.assumeTrue("CI".equals(ENV));
        assertEquals(LIST.size(), 3);*/
        }
    }


}
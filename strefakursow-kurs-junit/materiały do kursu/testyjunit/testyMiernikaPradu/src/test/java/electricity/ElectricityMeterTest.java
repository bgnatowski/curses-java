package electricity;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ElectricityMeterTest {
    static ElectricityMeter electricityMeter;
    static TariffProvider tp;

    @BeforeAll
    public static void setUp() {
        tp = Mockito.mock(TariffProvider.class);
        electricityMeter = new ElectricityMeter(tp);
        electricityMeter.setTariffOn(true);
    }

    @Test
    @Disabled("Testing disabling")
    public void givenTariffOnWhenAdditionThenKwhIncreased(){
        //Given
        Mockito.when(tp.isTariffNow()).thenReturn(false);
        electricityMeter = new ElectricityMeter(tp);
        electricityMeter.setTariffOn(true);
        //when
        electricityMeter.addKwh(50);
        //then
        Assumptions.assumeTrue(tp.isTariffNow());
        Assertions.assertEquals(50, electricityMeter.getKwhTariff());
    }

    @Test
    public void getHowMoreExpensiveNormalIs() {
        electricityMeter = new ElectricityMeter(tp);
        electricityMeter.setTariffOn(true);
        Throwable exception = assertThrows(ArithmeticException.class, () -> {
            ElectricityMeter electricityMeter = new ElectricityMeter();
            electricityMeter.setCentsForKwh(90);
            electricityMeter.getHowMoreExpensiveNormalIs();
        });

        assertEquals(exception.getMessage(), "/ by zero");
    }

    @RepeatedTest(value = 285, name=" {displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("Tesst wielokrotny")
    public void givenMuchAdditionsWhenAdditionThenNoTariffChange(RepetitionInfo repetitionInfo){
        //Given
        Mockito.when(tp.isTariffNow()).thenReturn(false);
        //when
        electricityMeter.addKwh(50);
        //then
        Assertions.assertEquals(repetitionInfo.getCurrentRepetition()* 50, electricityMeter.getKwh());
        Assertions.assertAll("Testing Tariff",
                ()-> assertEquals(electricityMeter.getKwhTariff(), 0),
                ()-> assertTrue(electricityMeter.getKwh()%50==0));
    }

}
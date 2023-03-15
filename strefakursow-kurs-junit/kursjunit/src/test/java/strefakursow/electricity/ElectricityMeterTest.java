package strefakursow.electricity;

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
    public void givenTariffOnWhenAdditionThenKwhIncreased(){
        //Given
        Mockito.when(tp.isTariffNow()).thenReturn(true);
        electricityMeter = new ElectricityMeter(tp);
        electricityMeter.setTariffOn(true);
        //When
        electricityMeter.addKwh(50);
        //Then
//        Assumptions.assumeTrue(tp.isTariffNow());
        assertEquals(50, electricityMeter.getKwhTariff());
    }

    @Disabled
    @Test
    public void getHowMoreExpensiveNormalIs() {
        electricityMeter = new ElectricityMeter(tp);
        electricityMeter.setTariffOn(true);
        Throwable exception = assertThrows(ArithmeticException.class, () -> {
            electricityMeter.setCentsForKwh(90);
            electricityMeter.getHowMoreExpensiveNormalIs();
        });

        assertEquals(exception.getMessage(),"/ by zero");
    }

    @RepeatedTest(value = 85, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("Test wielokrotny")
    public void givenMuchAdditionsWhenAdditionThenNoTariffChange(RepetitionInfo repetitionInfo){
        //Given
        Mockito.when(tp.isTariffNow()).thenReturn(false);
        //When
        electricityMeter.addKwh(50);
        //Then
        assertEquals(repetitionInfo.getCurrentRepetition()*50, electricityMeter.getKwh());
        assertAll("Testing Tariff",
                () -> assertEquals(electricityMeter.getKwhTariff(), 0),
                () -> assertTrue(electricityMeter.getKwh()%50==0));
    }


}
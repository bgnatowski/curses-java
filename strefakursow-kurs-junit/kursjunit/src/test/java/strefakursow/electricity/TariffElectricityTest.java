package strefakursow.electricity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class TariffElectricityTest {
    ElectricityMeter electricityMeter;
    TariffProvider tp;

    @Before
    public void setUp(){
        tp = Mockito.mock(TariffProvider.class);
        electricityMeter = new ElectricityMeter(tp);
        electricityMeter.setTariffOn(true);
        electricityMeter.setElectricityTariffStartHour(12);
        electricityMeter.setElectricityTariffEndHour(14);
    }

    @Test
    public void givenOnTariffWhenKwhAdditionThenCounterTariffIsIncreased(){
        //Given
        // TODO setTariff Time mock iskTariffNow() !
        Mockito.when(tp.isTariffNow()).thenReturn(true);
        //When
        electricityMeter.addKwh(100);
        //Then
        assertEquals(100, electricityMeter.getKwhTariff(), 0.01);
    }

    @Test
    public void givenNotOnTariffWhenKwhAdditionThenCounterIsIncreased(){
        //Given
        // TODO setTariff Time mock iskTariffNow() NOT TARIFF!
        Mockito.when(tp.isTariffNow()).thenReturn(false);
        //When
        electricityMeter.addKwh(100);
        //Then
        assertEquals(100, electricityMeter.getKwh(), 0.01);

    }
}

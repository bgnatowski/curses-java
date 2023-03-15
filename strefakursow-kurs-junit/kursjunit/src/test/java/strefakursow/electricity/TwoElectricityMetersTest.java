package strefakursow.electricity;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TwoElectricityMetersTest {

    @Test
    public void addKwh_newMeter_properAddition() {
        ElectricityMeter electricityMeter = new ElectricityMeter();
        ElectricityMeter electricityMeter1 = new ElectricityMeter();
        electricityMeter.addKwh(1);
        assertTrue(electricityMeter.getKwh() == 1);

    }

    @Ignore("Not implemented yet")
    @Test
    public void addKwh_newMeter2_properAddition() {
        ElectricityMeter electricityMeter = new ElectricityMeter();
        ElectricityMeter electricityMeter1 = new ElectricityMeter();
        electricityMeter.addKwh(1);
        electricityMeter.addKwh(3);
        assertTrue("Addition 1 and 4 should be 5",electricityMeter.getKwh() == 5);

    }

    @Test
    public void addKwh_newMeter5_properAddition() {
        ElectricityMeter electricityMeter = new ElectricityMeter();
        ElectricityMeter electricityMeter1 = new ElectricityMeter();
        electricityMeter.addKwh(1);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        assertTrue(electricityMeter.getKwh() == 17);
    }

    @Test
    public void givenNewMeterWhenFirstAdditionThenProperCounter() {
        //Given
        ElectricityMeter electricityMeter = new ElectricityMeter();
        ElectricityMeter electricityMeter1 = new ElectricityMeter();
        //When
        electricityMeter.addKwh(1);
        //Then
        assertTrue(electricityMeter.getKwh() == 1);

    }


}
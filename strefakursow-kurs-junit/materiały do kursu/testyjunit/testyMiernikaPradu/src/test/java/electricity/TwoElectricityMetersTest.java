package electricity;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class TwoElectricityMetersTest {

    @Test
    public void addKwh_newMeter_properAddition() {
        ElectricityMeter electricityMeter = new ElectricityMeter();
        ElectricityMeter electricityMeter1 = new ElectricityMeter();
        electricityMeter.addKwh(1);
        Assert.assertTrue(electricityMeter.getKwh() == 1);
    }

    @Test
    public void addKwh_newMeter2_properAddition() {
        ElectricityMeter electricityMeter = new ElectricityMeter();
        ElectricityMeter electricityMeter1 = new ElectricityMeter();
        electricityMeter.addKwh(1);
        electricityMeter.addKwh(3);
        Assert.assertTrue("Should fail!", electricityMeter.getKwh() == 5);
    }

    @Ignore("Not implemented yet")
    @Test
    public void addKwh_newMeter5_properAddition() {
        ElectricityMeter electricityMeter = new ElectricityMeter();
        ElectricityMeter electricityMeter1 = new ElectricityMeter();
        electricityMeter.addKwh(1);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        Assert.assertTrue(electricityMeter.getKwh() == 17);
    }

    //G-W-T preffered way to write tests
    @Test
    public void givenNewMeterWhenFirstAdditionThenProperCounter() {
        //Given/Arrange
        ElectricityMeter electricityMeter = new ElectricityMeter();
        ElectricityMeter electricityMeter1 = new ElectricityMeter();
        //When/Act
        electricityMeter.addKwh(1);
        //Then/Assert
        Assert.assertTrue(electricityMeter.getKwh() == 1);
    }

    @Test(expected = ArithmeticException.class)
    public void getHowMoreExpensiveNormalIs() {
        ElectricityMeter electricityMeter = new ElectricityMeter();
        ElectricityMeter electricityMeter1 = new ElectricityMeter();
        electricityMeter.setCentsForKwh(90);
        electricityMeter.getHowMoreExpensiveNormalIs();
    }
}
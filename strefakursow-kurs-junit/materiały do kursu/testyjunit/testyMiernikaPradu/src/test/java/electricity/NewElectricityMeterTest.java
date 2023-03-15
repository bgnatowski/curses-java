package electricity;

import org.junit.*;

public class NewElectricityMeterTest {

    private static ElectricityMeter electricityMeter;

    @BeforeClass
    public static void init() {
        //Computation expensive steps
        NewElectricityMeterTest.electricityMeter = new ElectricityMeter();
    }

    @Before
    public void setUp() {
        //assure deterministic start environment
        electricityMeter.reset();
    }

    @AfterClass
    public static void release(){
        //release connections/files etc.
    }

    @Test
    public void addKwh_newMeter_properAddition() {
        electricityMeter.addKwh(1);
        Assert.assertTrue(electricityMeter.getKwh() == 1);
    }

    @Test
    public void addKwh_newMeter2_properAddition() {
        electricityMeter.addKwh(1);
        electricityMeter.addKwh(3);
        Assert.assertTrue(electricityMeter.getKwh() == 4);
    }

    @Test
    public void addKwh_newMeter5_properAddition() {
        ElectricityMeter electricityMeter = new ElectricityMeter();
        electricityMeter.addKwh(1);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        Assert.assertTrue(electricityMeter.getKwh() == 17);
    }

    @Test
    public void kwhCounterIncreaseIfNew() {
        ElectricityMeter electricityMeter = new ElectricityMeter();
        electricityMeter.addKwh(1);
        Assert.assertTrue(electricityMeter.getKwh() == 1);
    }

    @Test
    public void kwhCounterIncreaseIfSecond() {
        ElectricityMeter electricityMeter = new ElectricityMeter();
        electricityMeter.addKwh(1);
        electricityMeter.addKwh(1);
        Assert.assertTrue(electricityMeter.getKwh() == 2);
    }

    //G-W-T preffered way to write tests
    @Test
    public void givenNewMeterWhenFirstAdditionThenProperCounter() {
        //Given/Arrange
        ElectricityMeter electricityMeter = new ElectricityMeter();
        //When/Act
        electricityMeter.addKwh(1);
        //Then/Assert
        Assert.assertTrue(electricityMeter.getKwh() == 1);
    }

    @Test(expected = ArithmeticException.class)
    public void getHowMoreExpensiveNormalIs() {
        ElectricityMeter electricityMeter = new ElectricityMeter();
        electricityMeter.setCentsForKwh(90);
        electricityMeter.getHowMoreExpensiveNormalIs();
    }
}
package electricity;

import org.junit.*;

public class ExistElectricityMeterTest {

    private static ElectricityMeter electricityMeter;

    @BeforeClass
    public static void init() {
        //Computation expensive steps
        ExistElectricityMeterTest.electricityMeter = new ElectricityMeter();
    }

    @Before
    public void setUp() {
        //assure deterministic start environment
        electricityMeter.addKwh(100);
    }

    @AfterClass
    public static void release(){
        //release connections/files etc.
    }

    @After
    public void tearDown(){
        electricityMeter.reset();
    }

    @Test
    public void addKwh_newMeter_properAddition() {
        electricityMeter.addKwh(1);
        Assert.assertTrue(electricityMeter.getKwh() == 101);
    }

    @Test
    public void addKwh_newMeter2_properAddition() {
        electricityMeter.addKwh(1);
        electricityMeter.addKwh(3);
        Assert.assertTrue(electricityMeter.getKwh() == 104);
    }

    @Test
    public void addKwh_newMeter5_properAddition() {
        electricityMeter.addKwh(1);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        Assert.assertTrue(electricityMeter.getKwh() == 117);
    }

    @Test
    public void kwhCounterIncreaseIfNew() {
        electricityMeter.addKwh(1);
        Assert.assertTrue(electricityMeter.getKwh() == 101);
    }

    @Test
    public void kwhCounterIncreaseIfSecond() {
        electricityMeter.addKwh(1);
        electricityMeter.addKwh(1);
        Assert.assertTrue(electricityMeter.getKwh() == 102);
    }

    //G-W-T preffered way to write tests
    @Test
    public void givenNewMeterWhenFirstAdditionThenProperCounter() {
        //Given/Arrange
        
        //When/Act
        electricityMeter.addKwh(1);
        //Then/Assert
        Assert.assertTrue(electricityMeter.getKwh() == 101);
    }

    @Test(expected = ArithmeticException.class)
    public void getHowMoreExpensiveNormalIs() {
        ElectricityMeter electricityMeter = new ElectricityMeter();
        electricityMeter.setCentsForKwh(90);
        electricityMeter.getHowMoreExpensiveNormalIs();
    }
}
package strefakursow.electricity;

import org.junit.*;

import static org.junit.Assert.assertTrue;

public class NewElectricityMeterTest {

    static private ElectricityMeter electricityMeter;

    @BeforeClass
    public static void init(){
        // computation expensive steps
        electricityMeter = new ElectricityMeter();
    }

    @Before
    public void setUp(){
        // assure deterministic start environment
        electricityMeter.reset();
    }

    @AfterClass
    public static void release(){
        //release connections, files etc.
    }

    @After
    public void tearDown(){

    }

    @Test
    public void addKwh_newMeter_properAddition() {
        electricityMeter.addKwh(1);
        assertTrue(electricityMeter.getKwh() == 1);

    }

    @Ignore("Not implemented yet")
    @Test
    public void addKwh_newMeter2_properAddition() {
        electricityMeter.addKwh(1);
        electricityMeter.addKwh(3);
        assertTrue("Addition 1 and 4 should be 5",electricityMeter.getKwh() == 5);

    }

    @Test
    public void addKwh_newMeter5_properAddition() {
        electricityMeter.addKwh(1);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        assertTrue(electricityMeter.getKwh() == 17);
    }

    @Test
    public void kwhCounterIncreseIfNew() {
        electricityMeter.addKwh(1);
        assertTrue(electricityMeter.getKwh() == 1);
    }

    @Test
    public void kwhCounterIncreseIfSecond() {
        electricityMeter.addKwh(1);
        electricityMeter.addKwh(4);
        assertTrue(electricityMeter.getKwh() == 5);
    }


    @Test
    public void givenNewMeterWhenFirstAdditionThenProperCounter() {
        //Given
        //When
        electricityMeter.addKwh(1);
        //Then
        assertTrue(electricityMeter.getKwh() == 1);

    }

    @Test(expected = ArithmeticException.class)
    public void getHowMoreExpensiveNormalIs() {
        electricityMeter.setCentsForKwh(90);
        electricityMeter.getHowMoreExpensiveNormalIs();
    }
}
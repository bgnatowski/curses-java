package strefakursow.electricity;

import org.junit.*;
import org.mockito.junit.MockitoJUnit;

import static org.junit.Assert.*;

public class ExistElectricityMeterTest {

    static private ElectricityMeter electricityMeter;

    @BeforeClass
    public static void init(){
        // computation expensive steps
        electricityMeter = new ElectricityMeter();
    }

    @Before
    public void setUp(){
        // assure deterministic start environment
        electricityMeter.addKwh(100);
    }

    @AfterClass
    public static void release(){
        //release connections, files etc.
    }

    @After
    public void tearDown(){
        electricityMeter.reset();
    }

    @Test
    public void addKwh_newMeter_properAddition() {
        electricityMeter.addKwh(1);
        assertTrue(electricityMeter.getKwh() == 101);
    }

    @Ignore("Not implemented yet")
    @Test
    public void addKwh_newMeter2_properAddition() {


        electricityMeter.addKwh(1);
        electricityMeter.addKwh(4);
        assertTrue("Addition 1 to 4 should be 105",electricityMeter.getKwh() == 105);

    }

    @Test
    public void addKwh_newMeter5_properAddition() {
        electricityMeter.addKwh(1);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        electricityMeter.addKwh(4);
        assertTrue(electricityMeter.getKwh() == 117);
    }

    @Test
    public void kwhCounterIncreseIfNew() {
        electricityMeter.addKwh(1);
        assertTrue(electricityMeter.getKwh() == 101);
    }

    @Test
    public void kwhCounterIncreseIfSecond() {
        electricityMeter.addKwh(1);
        electricityMeter.addKwh(4);
        assertTrue(electricityMeter.getKwh() == 105);
    }


    @Test
    public void givenNewMeterWhenFirstAdditionThenProperCounter() {
        //Given
        // given in setUp()
        //When
        electricityMeter.addKwh(1);
        //Then
        assertTrue(electricityMeter.getKwh() == 101);

    }

    @Test(expected = ArithmeticException.class)
    public void getHowMoreExpensiveNormalIs() {
        electricityMeter.setCentsForKwh(90);
        electricityMeter.getHowMoreExpensiveNormalIs();
    }
}
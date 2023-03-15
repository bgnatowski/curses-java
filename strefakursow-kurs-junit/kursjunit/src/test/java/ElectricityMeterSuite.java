import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import strefakursow.electricity.ExistElectricityMeterTest;
import strefakursow.electricity.TwoElectricityMetersTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ExistElectricityMeterTest.class, TwoElectricityMetersTest.class
})
public class ElectricityMeterSuite {


}

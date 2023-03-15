import electricity.ExistElectricityMeterTest;
import electricity.TwoElectricityMetersTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ExistElectricityMeterTest.class,
        ExistElectricityMeterTest.class,
        TwoElectricityMetersTest.class
})
public class ElectricityMeterSuite {
}

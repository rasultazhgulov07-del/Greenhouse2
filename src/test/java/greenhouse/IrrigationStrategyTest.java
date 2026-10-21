package greenhouse;
import greenhouse.model.GreenhouseData;
import greenhouse.irrigation.*;
import greenhouse.source.WeatherSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
class IrrigationStrategyTest {
    private final GreenhouseData dry = new GreenhouseData(20, 30, 50, true);
    @Test void fixedStrategy() { assertEquals(10, new FixedIrrigation().waterLiters(dry)); }
    @Test void moistureStrategy() { assertEquals(9, new MoistureIrrigation().waterLiters(dry)); }
    @Test void weatherStrategyWithFakeDataSource() {
        WeatherSource fakeSunny = () -> true;
        WeatherSource fakeCloudy = () -> false;
        assertEquals(13, new WeatherIrrigation(fakeSunny).waterLiters(dry));
        assertEquals(7, new WeatherIrrigation(fakeCloudy).waterLiters(dry));
    }
    static Stream<IrrigationStrategy> allStrategies() {
        return Stream.of(new FixedIrrigation(), new MoistureIrrigation(), new WeatherIrrigation(() -> false));
    }
    @ParameterizedTest @MethodSource("allStrategies")
    void neverProducesNegativeWater(IrrigationStrategy s) { assertTrue(s.waterLiters(new GreenhouseData(95,20,45,false)) >= 0); }
    @Test void contextCanSwapStrategy() {
        IrrigationContext c = new IrrigationContext(new FixedIrrigation());
        assertEquals(10, c.execute(dry));
        c.setStrategy(new MoistureIrrigation());
        assertEquals(9, c.execute(dry));
    }
}

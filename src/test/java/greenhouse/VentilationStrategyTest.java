package greenhouse;
import greenhouse.model.GreenhouseData;
import greenhouse.ventilation.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
class VentilationStrategyTest {
    @Test void temperatureStrategy() { assertEquals(100, new TemperatureVentilation().fanPercent(new GreenhouseData(30,36,40,true))); }
    @Test void humidityStrategy() { assertEquals(50, new HumidityVentilation().fanPercent(new GreenhouseData(30,20,75,true))); }
    @Test void ecoStrategy() {
        assertEquals(40, new EcoVentilation().fanPercent(new GreenhouseData(30,32,75,true)));
        assertEquals(0, new EcoVentilation().fanPercent(new GreenhouseData(30,20,75,true)));
    }
    static Stream<VentilationStrategy> allStrategies() { return Stream.of(new TemperatureVentilation(), new HumidityVentilation(), new EcoVentilation()); }
    @ParameterizedTest @MethodSource("allStrategies") void outputInPercentRange(VentilationStrategy strategy) {
        int result = strategy.fanPercent(new GreenhouseData(25,35,90,true));
        assertTrue(result >= 0 && result <= 100);
    }
    @Test void contextSwitch() {
        GreenhouseData d = new GreenhouseData(20,36,90,true);
        VentilationContext c = new VentilationContext(new EcoVentilation());
        assertEquals(40,c.execute(d));
        c.setStrategy(new HumidityVentilation());
        assertEquals(100,c.execute(d));
    }
}

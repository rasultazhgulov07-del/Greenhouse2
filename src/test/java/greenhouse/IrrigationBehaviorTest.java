package greenhouse;
import greenhouse.irrigation.*;
import greenhouse.model.GreenhouseData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class IrrigationBehaviorTest {
    // Same tests exist in BOTH commits; API stays stable after refactoring.
    private final IrrigationService service = new IrrigationService();
    @Test void fixedGetsTenLiters() { assertEquals(10, service.calculate(IrrigationMode.FIXED, new GreenhouseData(20,25,45,true))); }
    @Test void moistureStopsWhenWet() { assertEquals(0, service.calculate(IrrigationMode.MOISTURE, new GreenhouseData(70,25,45,true))); }
    @Test void drySoilReceivesMoreWater() {
        assertTrue(service.calculate(IrrigationMode.MOISTURE, new GreenhouseData(10,25,45,true)) >
                   service.calculate(IrrigationMode.MOISTURE, new GreenhouseData(50,25,45,true)));
    }
    @Test void sunnyWeatherRequiresMoreWater() {
        assertTrue(service.calculate(IrrigationMode.WEATHER, new GreenhouseData(25,25,45,true)) >
                   service.calculate(IrrigationMode.WEATHER, new GreenhouseData(25,25,45,false)));
    }
}

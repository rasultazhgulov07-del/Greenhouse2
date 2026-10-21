package greenhouse;
import greenhouse.model.GreenhouseData;
import greenhouse.irrigation.*;
import greenhouse.ventilation.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class SelectorTest {
    IrrigationSelector irrigation = new IrrigationSelector(() -> true);
    VentilationSelector ventilation = new VentilationSelector();
    @Test void wetSoilSelectsMoisture() { assertInstanceOf(MoistureIrrigation.class, irrigation.select(new GreenhouseData(70,20,30,true))); }
    @Test void veryDrySoilSelectsWeather() { assertInstanceOf(WeatherIrrigation.class, irrigation.select(new GreenhouseData(20,20,30,true))); }
    @Test void mediumSoilSelectsFixed() { assertInstanceOf(FixedIrrigation.class, irrigation.select(new GreenhouseData(45,20,30,true))); }
    @Test void hotAirSelectsTemperature() { assertInstanceOf(TemperatureVentilation.class, ventilation.select(new GreenhouseData(40,36,90,true))); }
    @Test void humidAirSelectsHumidity() { assertInstanceOf(HumidityVentilation.class, ventilation.select(new GreenhouseData(40,28,85,true))); }
    @Test void normalConditionsSelectEco() { assertInstanceOf(EcoVentilation.class, ventilation.select(new GreenhouseData(40,25,50,true))); }
}

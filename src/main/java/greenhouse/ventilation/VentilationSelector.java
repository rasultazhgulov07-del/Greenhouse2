package greenhouse.ventilation;
import greenhouse.model.GreenhouseData;
public class VentilationSelector {
    public VentilationStrategy select(GreenhouseData data) {
        if (data.temperature() >= 35) return new TemperatureVentilation(); // overheating
        if (data.airHumidity() >= 80) return new HumidityVentilation(); // very humid
        return new EcoVentilation(); // normal conditions
    }
}

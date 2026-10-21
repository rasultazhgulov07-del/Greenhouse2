package greenhouse.ventilation;
import greenhouse.model.GreenhouseData;
public class TemperatureVentilation implements VentilationStrategy {
    @Override public int fanPercent(GreenhouseData data) {
        if (data.temperature() >= 35) return 100;
        if (data.temperature() >= 28) return 60;
        return 0;
    }
}

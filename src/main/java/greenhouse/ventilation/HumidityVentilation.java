package greenhouse.ventilation;
import greenhouse.model.GreenhouseData;
public class HumidityVentilation implements VentilationStrategy {
    @Override public int fanPercent(GreenhouseData data) {
        if (data.airHumidity() >= 85) return 100;
        if (data.airHumidity() >= 65) return 50;
        return 0;
    }
}
